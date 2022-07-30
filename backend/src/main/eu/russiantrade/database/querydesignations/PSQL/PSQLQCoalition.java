package eu.russiantrade.database.querydesignations.PSQL;

public class PSQLQCoalition extends PSQLQueries {

    //select
    public static final String SELECT_COALITION = SELECT +
            COALITION_ID + C +
            COALITION_NAME + FROM + TABLE_COALITION;

    //parameter
    public static final String PARAMETER_ID = SANCTION_ID + PARAMETER;
    public static final String PARAMETER_NAME =  COALITION_NAME + PARAMETER;

    //delete
    public static final String COALITION_DELETE = DELETE + FROM + TABLE_COALITION;

    //queries
    public static final String QUERY_INSERT = INSERT_INTO + TABLE_COALITION + "(" +
            COALITION_NAME + ") VALUES (?)"  + RETURNING + COALITION_ID + END;
    public static final String QUERY_DELETE_WHERE_NAME = COALITION_DELETE + WHERE +
            PARAMETER_NAME + END;
    public static final String QUERY_DELETE_WHERE_ID = COALITION_DELETE + WHERE +
            PARAMETER_ID + END;

    public static String queryAll(String select) {
        return CREATE_JSON_MULTIPLE + FROM_START + select + ORDER_BY +
                COALITION_NAME + FROM_END + AS + ROW + END;
    }
    public static String queryWhereName(String select) {
        return CREATE_JSON_MULTIPLE + FROM_START + select + WHERE +
                PARAMETER_NAME+ ORDER_BY +
                COALITION_NAME + FROM_END + AS + ROW + END;
    }
}
