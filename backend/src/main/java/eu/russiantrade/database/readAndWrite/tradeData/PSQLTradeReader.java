package eu.russiantrade.database.readAndWrite.tradeData;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.parser.DatabaseTradeDataParser;
import eu.russiantrade.database.querydesignations.PSQL.PSQLQTradeData;
import eu.russiantrade.database.querydesignations.Query;

import javax.sql.DataSource;
import java.util.List;

public class PSQLTradeReader implements tradeDataReader {
    DataSource datasource;

    public PSQLTradeReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, int period) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQTradeData.queryWhereTradeReporterAndPartnerNoFlow(PSQLQTradeData.SELECT_DATASET),
                reporter, partner));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQTradeData.queryWhereTradeReporterAndPartner(PSQLQTradeData.SELECT_DATASET),
                tradeFlow, reporter, partner));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int period) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQTradeData.queryWhereTradeReporterPartnerAndPeriod(PSQLQTradeData.SELECT_DATASET),
                tradeFlow, reporter, partner, period));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int period, String commodityCode) {
        if(commodityCode.startsWith("AG")) {
            int commodityCodeLength = Integer.parseInt(commodityCode.substring(2,3));
            return parse(Query.queryWhereStringAndInt(datasource,
                    PSQLQTradeData.queryWhereTradeCommodityLengthReporterPartnerAndPeriod(PSQLQTradeData.SELECT_DATASET),
                    tradeFlow, commodityCodeLength, reporter, partner, period));
        } else {
            return parse(Query.queryWhereTwoStringAndInt(datasource,
                    PSQLQTradeData.queryWhereCommodityTradeReporterPartnerAndPeriod(PSQLQTradeData.SELECT_DATASET),
                    commodityCode, tradeFlow, reporter, partner, period));
        }
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQTradeData.queryWhereTradeReporterPartnerAndPeriodBetween(PSQLQTradeData.SELECT_DATASET),
                tradeFlow, reporter, partner, periodStart, periodEnd));
    }

    @Override
    public List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd, String commodityCode) {
        if(commodityCode.startsWith("AG")) {
            int commodityCodeLength = Integer.parseInt(commodityCode.substring(2,3));
            return parse(Query.queryWhereStringAndInt(datasource,
                    PSQLQTradeData.queryWhereTradeCommodityLengthReporterPartnerAndPeriodBetween(PSQLQTradeData.SELECT_DATASET),
                    tradeFlow, commodityCodeLength, reporter, partner, periodStart, periodEnd));
        } else {
            return parse(Query.queryWhereTwoStringAndInt(datasource,
                    PSQLQTradeData.queryWhereCommodityTradeReporterPartnerAndPeriodBetween(PSQLQTradeData.SELECT_DATASET),
                    commodityCode, tradeFlow, reporter, partner, periodStart, periodEnd));
        }
    }

    private List<TradeData> parse(String json) {
        return DatabaseTradeDataParser.parseResponse(json);
    }
}
