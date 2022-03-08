package eu.donttradewithrussia.database.querydesignations.PSQL;

import eu.donttradewithrussia.database.querydesignations.DataDesignations;

public abstract class PSQLQueries extends DataDesignations {

    public static final String CREATE_JSON = "SELECT array_to_json(array_agg(row_to_json(row)))";
    public static final String CREATE_JSON_ROW ="SELECT row_to_json(row)";
}
