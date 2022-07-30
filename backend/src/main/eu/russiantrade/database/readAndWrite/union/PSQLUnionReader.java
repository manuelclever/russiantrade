package russiantrade.database.readAndWrite.union;

import russiantrade.database.querydesignations.PSQL.PSQLQUnion;
import russiantrade.database.querydesignations.Query;

import javax.sql.DataSource;

public class PSQLUnionReader implements UnionDataReader {
    DataSource datasource;

    public PSQLUnionReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public String getAll(String coalitionName) {
        return Query.queryWhereString(datasource,
                PSQLQUnion.queryWhereCoalitionName(PSQLQUnion.SELECT_UNION),
                coalitionName);
    }

    @Override
    public String getAll(int coalitionID) {
        return Query.queryWhereInt(datasource,
                PSQLQUnion.queryWhereCoalitionID(PSQLQUnion.SELECT_UNION),
                coalitionID);
    }
}
