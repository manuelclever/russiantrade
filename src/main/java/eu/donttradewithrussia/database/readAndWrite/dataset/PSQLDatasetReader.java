package eu.donttradewithrussia.database.readAndWrite.dataset;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;
import eu.donttradewithrussia.database.parser.DatabaseDatasetParser;
import eu.donttradewithrussia.database.querydesignations.PSQL.PSQLQDataset;
import eu.donttradewithrussia.database.querydesignations.Query;

import javax.sql.DataSource;
import java.util.List;

public class PSQLDatasetReader implements DatasetDataReader {
    DataSource datasource;

    public PSQLDatasetReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereReporterAndPartner(PSQLQDataset.SELECT_DATASET),
                reporter, partner));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, int period) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                reporter, partner, period));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, int period, String commodityCode) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQDataset.queryWhereCommodityReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                commodityCode, reporter, partner, period));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, int periodStart, int periodEnd) {
        return parse(Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                reporter, partner, periodStart, periodEnd));
    }

    @Override
    public List<Dataset> getDatasets(int reporter, int partner, int periodStart, int periodEnd, String commodityCode) {
        return parse(Query.queryWhereStringAndInt(datasource,
                PSQLQDataset.queryWhereCommodityReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                commodityCode, reporter, partner, periodStart, periodEnd));
    }

    private List<Dataset> parse(String json) {
        return DatabaseDatasetParser.parseResponse(json);
    }
}
