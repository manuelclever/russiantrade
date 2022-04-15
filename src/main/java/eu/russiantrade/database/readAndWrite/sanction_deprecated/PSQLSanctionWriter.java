package eu.russiantrade.database.readAndWrite.sanction_deprecated;

import javax.sql.DataSource;

public class PSQLSanctionWriter implements SanctionDataWriter {
    DataSource datasource;

    public PSQLSanctionWriter(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public int addSanctionGlobal(int groupID, int periodStart, String content) {
        return 0;
    }

    @Override
    public int addSanctionGlobal(int groupID, int periodStart, int periodEnd, String content) {
        return 0;
    }

    @Override
    public int addSanctionLocal(int countryID, int periodStart, String content) {
        return 0;
    }

    @Override
    public int addSanctionLocal(int countryID, int periodStart, int periodEnd, String content) {
        return 0;
    }

    @Override
    public boolean removeSanction(int sanctionID) {
        return false;
    }
}
