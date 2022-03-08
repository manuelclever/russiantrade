package eu.donttradewithrussia.database.readAndWrite.dataset;

import eu.donttradewithrussia.database.querydesignations.PSQL.PSQLQDataset;
import eu.donttradewithrussia.database.querydesignations.Query;

import javax.sql.DataSource;

public class PSQLDatasetReader implements DatasetDataReader {
    DataSource datasource;

    public PSQLDatasetReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public String getDataset(int reporter, int partner) {
        return Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereReporterAndPartner(PSQLQDataset.SELECT_DATASET),
                reporter, partner);
    }

    @Override
    public String getDataset(int reporter, int partner, int period) {
        return Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                reporter, partner, period);
    }

    @Override
    public String getDataset(int reporter, int partner, int period, String commodityCode) {
        return Query.queryWhereStringAndInt(datasource,
                PSQLQDataset.queryWhereCommodityReporterPartnerAndPeriod(PSQLQDataset.SELECT_DATASET),
                commodityCode, reporter, partner, period);
    }

    @Override
    public String getDataset(int reporter, int partner, int periodStart, int periodEnd) {
        return Query.queryWhereInt(datasource,
                PSQLQDataset.queryWhereReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                reporter, partner, periodStart, periodEnd);
    }

    @Override
    public String getDataset(int reporter, int partner, int periodStart, int periodEnd, String commodityCode) {
        return Query.queryWhereStringAndInt(datasource,
                PSQLQDataset.queryWhereCommodityReporterPartnerAndPeriodBetween(PSQLQDataset.SELECT_DATASET),
                commodityCode, reporter, partner, periodStart, periodEnd);
    }
}
