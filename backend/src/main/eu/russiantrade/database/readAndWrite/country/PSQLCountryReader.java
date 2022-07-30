package russiantrade.database.readAndWrite.country;

import russiantrade.database.querydesignations.PSQL.PSQLQCountry;
import russiantrade.database.querydesignations.Query;

import javax.sql.DataSource;

public class PSQLCountryReader implements CountryDataReader {
    DataSource datasource;

    public PSQLCountryReader(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public String getCountry(String name) {
        return Query.queryWhereString(datasource,
                PSQLQCountry.queryWhereName(PSQLQCountry.SELECT_COUNTRY),
                name);
    }

    @Override
    public String getCountry(int comtradeId) {
        return Query.queryWhereInt(datasource,
                PSQLQCountry.queryWhereComtradeCode(PSQLQCountry.SELECT_COUNTRY),
                comtradeId);
    }
}
