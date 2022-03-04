package eu.donttradewithrussia.database.readAndWrite;

import javax.sql.DataSource;

public class PSQLDatasetReader implements DatasetDataReader {
    DataSource datasource;

    public PSQLDatasetReader(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public String getDataset(int reporter, int partner, int period) {
        return null;
    }

    @Override
    public String getDataset(int reporter, int partner, int period, char rg) {
        return null;
    }

    @Override
    public String getDataset(int reporter, int partner, int periodStart, int periodEnd) {
        return null;
    }

    @Override
    public String getDataset(int reporter, int partner, int periodStart, int periodEnd, char rg) {
        return null;
    }
}
