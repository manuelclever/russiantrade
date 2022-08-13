package eu.russiantrade.database.readAndWrite.sanction_deprecated;

import javax.sql.DataSource;

public class PSQLSanctionReader implements  SanctionDataReader {
    DataSource datasource;

    public PSQLSanctionReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public String getSanction(int countryID, int period) {
        return null;
    }
}
