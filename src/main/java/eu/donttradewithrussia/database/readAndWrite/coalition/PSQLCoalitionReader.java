package eu.donttradewithrussia.database.readAndWrite.coalition;

import eu.donttradewithrussia.database.querydesignations.PSQL.PSQLQCoalition;
import eu.donttradewithrussia.database.querydesignations.Query;

import javax.sql.DataSource;

public class PSQLCoalitionReader implements CoalitionDataReader {
    DataSource datasource;

    public PSQLCoalitionReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public String getGroups() {
        return Query.queryAll(datasource,
                PSQLQCoalition.queryAll(PSQLQCoalition.SELECT_COALITION));
    }

    @Override
    public String getGroup(String name) {
        return Query.queryWhereString(datasource,
                PSQLQCoalition.queryWhereName(PSQLQCoalition.SELECT_COALITION),
                name);
    }
}
