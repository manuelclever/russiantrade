package russiantrade.database.querydesignations.PSQL;

public class PSQLQUnion extends PSQLQueries {

    //join
    public static final String JOIN_COUNTRY = INNER_JOIN + TABLE_COUNTRY +
            ON + TABLE_UNION + P + COUNTRY + " = " + COUNTRY_ID;
    public static final String JOIN_COALITION = INNER_JOIN + TABLE_COALITION +
            ON + TABLE_UNION + P + COALITION + " = " + COALITION_ID;

    //select
    public static final String SELECT_UNION = SELECT +
            UNION_ID + C +
            TABLE_UNION + P + COALITION + C +
            TABLE_UNION + P + COUNTRY + FROM + TABLE_UNION;

    //parameter
    public static final String PARAMETER_ID = UNION_ID + PARAMETER;
    public static final String PARAMETER_COALITION =  TABLE_UNION + P + COALITION + PARAMETER;
    public static final String PARAMETER_COUNTRY = TABLE_UNION + P + COUNTRY + PARAMETER;

    //delete
    public static final String UNION_DELETE = DELETE + FROM + TABLE_UNION;

    //queries
    public static final String QUERY_INSERT = INSERT_INTO + TABLE_UNION + "(" +
            TABLE_UNION + P + COALITION + C +
            TABLE_UNION + P + COUNTRY +  ") VALUES (?,?)"  + RETURNING + UNION_ID + END;
    public static final String QUERY_DELETE = UNION_DELETE + WHERE +
            TABLE_UNION + P + COALITION + AND +
            TABLE_UNION + P + COUNTRY + END;

    public static String queryWhereCoalitionName(String select) {
        return CREATE_JSON_MULTIPLE + FROM_START + select +
                JOIN_COALITION + WHERE +
                PARAMETER_COALITION + ORDER_BY +
                COUNTRY + FROM_END + AS + ROW + END;
    }
    public static String queryWhereCoalitionID(String select) {
        return CREATE_JSON_MULTIPLE + FROM_START + select + WHERE +
                PARAMETER_COALITION + ORDER_BY +
                COUNTRY + FROM_END + AS + ROW + END;
    }
}

