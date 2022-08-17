package eu.russiantrade.database.querydesignations.PSQL;

import eu.russiantrade.database.querydesignations.DataDesignations;

public class PSQLQCountry extends PSQLQueries {

    //select
    public static final String SELECT_COUNTRY = SELECT +
            DataDesignations.COUNTRY_ID + C +
            DataDesignations.COUNTRY_NAME + C +
            DataDesignations.COUNTRY_ABBREV + FROM + DataDesignations.TABLE_COUNTRY;

    //parameter
    public static final String PARAMETER_ID = DataDesignations.COUNTRY_ID + PARAMETER;
    public static final String PARAMETER_NAME =  DataDesignations.COUNTRY_NAME + PARAMETER;
    public static final String PARAMETER_ABBREV = DataDesignations.COUNTRY_ABBREV + PARAMETER;

    //delete
    public static final String COUNTRY_DELETE = DELETE + FROM + DataDesignations.TABLE_COUNTRY;

    //queries
    public static final String QUERY_INSERT = INSERT_INTO + DataDesignations.TABLE_COUNTRY + "(" +
            DataDesignations.COUNTRY_ID + C +
            DataDesignations.COUNTRY_NAME + C +
            DataDesignations.COUNTRY_ABBREV + ") VALUES (?,?,?)" + RETURNING + DataDesignations.COUNTRY_ID + END;
    // UPDATE tradetest_schema.country SET abbrev='TRO'  WHERE country_id = 36;
    public static final String QUERY_UPDATE = UPDATE + DataDesignations.TABLE_COUNTRY + SET +
            PARAMETER_ID + C +
            PARAMETER_NAME + C +
            PARAMETER_ABBREV + WHERE + PARAMETER_ID + RETURNING + DataDesignations.COUNTRY_ID + END;
    public static final String QUERY_DELETE_WHERE_COUNTRY_ID = COUNTRY_DELETE + WHERE +
            PARAMETER_ID + END;
    public static final String QUERY_DELETE_WHERE_NAME = COUNTRY_DELETE + WHERE +
            PARAMETER_NAME + END;


    public static String queryWhereName(String select) {
        return CREATE_JSON_MULTIPLE + FROM_START + select + WHERE +
                PARAMETER_NAME + ORDER_BY +
                DataDesignations.COUNTRY_NAME + FROM_END + AS + ROW + END;
    }
    public static String queryWhereComtradeCode(String select) {
        return CREATE_JSON_SINGLETON + FROM_START + select + WHERE +
                PARAMETER_ID + ORDER_BY +
                DataDesignations.COUNTRY_ID + FROM_END + AS + ROW + END;
    }
}
