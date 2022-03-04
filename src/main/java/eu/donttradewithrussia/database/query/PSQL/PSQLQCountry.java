package eu.donttradewithrussia.database.query.PSQL;

public class PSQLQCountry extends PSQLQueries {

    //select
    public static final String SELECT_Country = SELECT +
            COUNTRY_ID + C +
            COUNTRY_NAME + C +
            COUNTRY_ABBREV + C +
            COUNTRY_COMTRADE_CODE + FROM + TABLE_COUNTRY;

    //parameter
    public static final String PARAMETER_ID = COUNTRY_ID + PARAMETER;
    public static final String PARAMETER_NAME =  COUNTRY_NAME + PARAMETER;
    public static final String PARAMETER_ABBREV = COUNTRY_ABBREV + PARAMETER;
    public static final String PARAMETER_COMTRADE_CODE = COUNTRY_COMTRADE_CODE + PARAMETER;

    //insert
    public static final String INSERT = INSERT_INTO + TABLE_SANCTION + "(" +
            COUNTRY_NAME + C +
            COUNTRY_ABBREV + C +
            COUNTRY_COMTRADE_CODE +  ") VALUES (?,?,?)"  + RETURNING + COUNTRY_ID + END;

    //delete
    public static final String COUNTRY_DELETE = DELETE + FROM + TABLE_COUNTRY;

    //queries
    public static final String QUERY_DELETE_WHERE_NAME = COUNTRY_DELETE + WHERE +
            PARAMETER_NAME + END;


    public static String queryWhereName(String select) {
        return CREATE_JSON + FROM_START + select + WHERE +
                PARAMETER_NAME + ORDER_BY +
                COUNTRY_NAME + FROM_END + AS + ROW + END;
    }
    public static String queryWhereComtradeCode(String select) {
        return CREATE_JSON + FROM_START + select + WHERE +
                PARAMETER_COMTRADE_CODE + ORDER_BY +
                COUNTRY_COMTRADE_CODE + FROM_END + AS + ROW + END;
    }
}
