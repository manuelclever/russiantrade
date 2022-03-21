package eu.donttradewithrussia.database.querydesignations.PSQL;

import eu.donttradewithrussia.database.querydesignations.DataDesignations;

public abstract class PSQLQueries extends DataDesignations {

    public static final String CREATE_JSON_MULTIPLE = "SELECT array_to_json(array_agg(row_to_json(row)))";
    public static final String CREATE_JSON_SINGLETON ="SELECT row_to_json(row)";
}
