package eu.donttradewithrussia.database.query.PSQL;

public class PSQLQUnion extends PSQLQueries {


    //select
    public static final String SELECT_UNION = SELECT +
            UNION_ID + C +
            TABLE_UNION + P + GROUP + C +
            TABLE_UNION + P + COUNTRY + FROM + TABLE_UNION;

    //parameter
    public static final String PARAMETER_ID = UNION_ID + PARAMETER;
    public static final String PARAMETER_GROUP =  TABLE_UNION + P + GROUP + PARAMETER;
    public static final String PARAMETER_COUNTRY = TABLE_UNION + P + COUNTRY + PARAMETER;

    //delete
    public static final String UNION_DELETE = DELETE + FROM + TABLE_UNION;

    //queries
    public static final String QUERY_INSERT = INSERT_INTO + TABLE_UNION + "(" +
            TABLE_UNION + P + GROUP + C +
            TABLE_UNION + P + COUNTRY +  ") VALUES (?,?)"  + RETURNING + UNION_ID + END;
    public static final String QUERY_DELETE = UNION_DELETE + WHERE +
            TABLE_UNION + P + GROUP + AND +
            TABLE_UNION + P + COUNTRY + END;

    public static String queryWhereGroupAndCountry(String select) {
        return CREATE_JSON + FROM_START + select + WHERE +
                PARAMETER_GROUP + AND +
                PARAMETER_COUNTRY + ORDER_BY +
                TABLE_SANCTION + P + GROUP + FROM_END + AS + ROW + END;
    }
}

