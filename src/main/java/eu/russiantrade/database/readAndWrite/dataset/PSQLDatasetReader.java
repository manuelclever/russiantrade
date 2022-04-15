package eu.russiantrade.database.readAndWrite.dataset;

import eu.russiantrade.api.comtrade.parser.Dataset;
import eu.russiantrade.database.parser.DatabaseDatasetParser;
import eu.russiantrade.database.querydesignations.PSQL.PSQLQDataset;
import eu.russiantrade.database.querydesignations.Query;

import javax.sql.DataSource;
import java.util.List;

public class PSQLDatasetReader implements DatasetDataReader {
    DataSource datasource;

    public PSQLDatasetReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, String tradeFlow) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereTradeReporterAndPartner(PSQLQDataset.SELECT_DATASET),
                reporter, partner));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, int period, String tradeFlow) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereTradeReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                reporter, partner, period));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, int period, String tradeFlow, String commodityCode) {
        if(commodityCode.substring(0,2) == "AG") {
            int commodityCodeLength = Integer.parseInt(commodityCode.substring(2,3));
            return parse(Query.queryWhereInt(datasource,
                    PSQLQDataset.queryWhereTradeCommodityLengthReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                    commodityCodeLength, reporter, partner, period));
        } else {
            return parse(Query.queryWhereStringAndInt(datasource,
                    PSQLQDataset.queryWhereCommodityTradeReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                    commodityCode, reporter, partner, period));
        }
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereTradeReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                reporter, partner, periodStart, periodEnd));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd, String commodityCode) {
        if(commodityCode.substring(0,2) == "AG") {
            int commodityCodeLength = Integer.parseInt(commodityCode.substring(2,3));
            return parse(Query.queryWhereInt(datasource,
                    PSQLQDataset.queryWhereTradeCommodityLengthReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                    commodityCodeLength, reporter, partner, periodStart, periodEnd));
        } else {
            return parse(Query.queryWhereStringAndInt(datasource,
                    PSQLQDataset.queryWhereCommodityTradeReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                    commodityCode, reporter, partner, periodStart, periodEnd));
        }
    }

    private List<Dataset> parse(String json) {
        return DatabaseDatasetParser.parseResponse(json);
    }
}
