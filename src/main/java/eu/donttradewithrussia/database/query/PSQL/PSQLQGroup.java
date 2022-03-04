package eu.donttradewithrussia.database.query.PSQL;

public class PSQLQGroup extends PSQLQueries {

    //select
    public static final String SELECT_GROUP = SELECT +
            GROUP_ID + C +
            GROUP_NAME + FROM + TABLE_GROUP;

    //parameter
    public static final String PARAMETER_ID = SANCTION_ID + PARAMETER;
    public static final String PARAMETER_NAME =  GROUP_NAME + PARAMETER;

    //insert
    public static final String INSERT = INSERT_INTO + TABLE_GROUP + "(" +
            GROUP_NAME + ") VALUES (?)"  + RETURNING + GROUP_ID + END;

    //delete
    public static final String GROUP_DELETE = DELETE + FROM + TABLE_GROUP;


    //queries
    public static final String QUERY_DELETE_WHERE_NAME = GROUP_DELETE + WHERE +
            PARAMETER_NAME + END;

    public static String queryWhereName(String select) {
        return CREATE_JSON + FROM_START + select + WHERE +
                PARAMETER_NAME+ ORDER_BY +
                GROUP_NAME + FROM_END + AS + ROW + END;
    }
}
