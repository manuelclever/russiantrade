package eu.russiantrade.database.querydesignations.PSQL;

public class PSQLQCountry extends PSQLQueries {

    //select
    public static final String SELECT_COUNTRY = SELECT +
            COUNTRY_ID + C +
            COUNTRY_NAME + C +
            COUNTRY_ABBREV + FROM + TABLE_COUNTRY;

    //parameter
    public static final String PARAMETER_ID = COUNTRY_ID + PARAMETER;
    public static final String PARAMETER_NAME =  COUNTRY_NAME + PARAMETER;
    public static final String PARAMETER_ABBREV = COUNTRY_ABBREV + PARAMETER;

    //delete
    public static final String COUNTRY_DELETE = DELETE + FROM + TABLE_COUNTRY;

    //queries
    public static final String QUERY_INSERT = INSERT_INTO + TABLE_COUNTRY + "(" +
            COUNTRY_ID + C +
            COUNTRY_NAME + C +
            COUNTRY_ABBREV + ") VALUES (?,?,?)" + RETURNING + COUNTRY_ID + END;
    // UPDATE tradetest_schema.country SET abbrev='TRO'  WHERE country_id = 36;
    public static final String QUERY_UPDATE = UPDATE + TABLE_COUNTRY + SET +
            PARAMETER_ID + C +
            PARAMETER_NAME + C +
            PARAMETER_ABBREV + WHERE + PARAMETER_ID + RETURNING + COUNTRY_ID + END;
    public static final String QUERY_DELETE_WHERE_NAME = COUNTRY_DELETE + WHERE +
            PARAMETER_NAME + END;
    public static final String QUERY_DELETE_WHERE_COMTRADE_ID = COUNTRY_DELETE + WHERE +
            COUNTRY_COMTRADE_ID + END;

    public static String queryWhereName(String select) {
        return CREATE_JSON_MULTIPLE + FROM_START + select + WHERE +
                PARAMETER_NAME + ORDER_BY +
                COUNTRY_NAME + FROM_END + AS + ROW + END;
    }
    public static String queryWhereComtradeCode(String select) {
        return CREATE_JSON_SINGLETON + FROM_START + select + WHERE +
                PARAMETER_ID + ORDER_BY +
                COUNTRY_ID + FROM_END + AS + ROW + END;
    }
}
