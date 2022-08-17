package eu.russiantrade.database.readAndWrite.monthly_trade;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.parser.DatabaseTradeDataParser;
import eu.russiantrade.database.querydesignations.PSQL.PSQLQTradeData;
import eu.russiantrade.database.querydesignations.Query;
import eu.russiantrade.util.DigitCount;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class PSQLTradeReader implements tradeDataReader {
    private final DataSource datasource;
    private PrintWriter out;

    public PSQLTradeReader(DataSource datasource) {
        this.datasource = datasource;
    }

    public PSQLTradeReader(DataSource datasource, PrintWriter out) {
        this.datasource = datasource;
        this.out = out;
    }

    public List<TradeData> getTotalOfYear(int reporter, int partner, String tradeFlow, int period) {
        return getTotalOfYears(reporter, partner, tradeFlow, period, period);
    }

    @Override
    public List<TradeData> getTotalOfYears(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd) {
        if(DigitCount.count(periodStart) == 4 && DigitCount.count(periodEnd) == 4) {
            if(out == null) {
                return parse(
                        Query.queryWhereTwoStringAndInt(
                                datasource,
                                PSQLQTradeData.queryTotalOfYear(PSQLQTradeData.SELECT_DATASET),
                                tradeFlow,
                                "TOTAL",
                                reporter,
                                partner,
                                periodStart,
                                periodEnd
                        )
                );
            } else {
                return parse(
                        Query.queryWhereTwoStringAndIntServlet(
                                out,
                                datasource,
                                PSQLQTradeData.queryTotalOfYear(PSQLQTradeData.SELECT_DATASET),
                                tradeFlow,
                                "TOTAL",
                                reporter,
                                partner,
                                periodStart,
                                periodEnd
                        )
                );
            }
        }
        throw new NumberFormatException("Year in format 'YYYY' expected.");
    }

    public List<TradeData> getCommodityMonth(int reporter, int partner, String tradeFlow, int period, String commodityCode) {
        if(DigitCount.count(period) == 6) {
            if (commodityCode.startsWith("AG")) {
                int commodityCodeLength = Integer.parseInt(commodityCode.substring(2, 3));
                return parse(
                        Query.queryWhereStringAndInt(
                                datasource,
                                PSQLQTradeData.queryWhere_Tr_CoLe_Re_Pa_Pe(PSQLQTradeData.SELECT_DATASET),
                                tradeFlow,
                                commodityCodeLength,
                                reporter,
                                partner,
                                period
                        )
                );
            } else {
                return parse(
                        Query.queryWhereTwoStringAndInt(
                                datasource,
                                PSQLQTradeData.queryWhere_Tr_Co_Re_Pa_Pe(PSQLQTradeData.SELECT_DATASET),
                                tradeFlow,
                                commodityCode,
                                reporter,
                                partner,
                                period
                        )
                );
            }
        }
        throw new NumberFormatException("Year and month in format 'YYYYMM' expected.");
    }

    public List<TradeData> getCommodityYear(int reporter, int partner, String tradeFlow, int period, String commodityCode) {
        if(DigitCount.count(period) == 4) {
            if (commodityCode.startsWith("AG")) {
                int commodityCodeLength = Integer.parseInt(commodityCode.substring(2, 3));
                return parse(Query.queryWhereStringAndInt(datasource,
                        PSQLQTradeData.queryWhere_Tr_CoLe_Re_Pa_Pe(PSQLQTradeData.SELECT_DATASET),
                        tradeFlow, commodityCodeLength, reporter, partner, period));
            } else {
                return parse(Query.queryWhereTwoStringAndInt(datasource,
                        PSQLQTradeData.queryWhere_Tr_Co_Re_Pa_Pe(PSQLQTradeData.SELECT_DATASET),
                        commodityCode, tradeFlow, reporter, partner, period));
            }
        }
        throw new NumberFormatException("Year in format 'YYYY' expected.");
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, int period) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQTradeData.queryWhere_Tr_Re_Pa_Pe_NoFl(PSQLQTradeData.SELECT_DATASET),
                reporter, partner, period));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQTradeData.queryWhere_Tr_Re_Pa(PSQLQTradeData.SELECT_DATASET),
                tradeFlow, reporter, partner));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int period) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQTradeData.queryWhere_Tr_Re_Pa_Pe(PSQLQTradeData.SELECT_DATASET),
                tradeFlow, reporter, partner, period));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQTradeData.queryWhere_Tr_Re_Pa_PeBe(PSQLQTradeData.SELECT_DATASET),
                tradeFlow, reporter, partner, periodStart, periodEnd));
    }

    private List<TradeData> parse(String json) {
        if(out == null) {
            return DatabaseTradeDataParser.parseResponse(json);
        } else {
            out.println("<p>parseResponseServlet</p>");
            try {
                return DatabaseTradeDataParser.parseResponseServlet(json, out);
            } catch (Exception e) {
                out.println("<p>Error</p>");
                out.println("<p>" + e.getMessage() + "</p>");
                out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");
            }
        }
        return null;
    }
}
