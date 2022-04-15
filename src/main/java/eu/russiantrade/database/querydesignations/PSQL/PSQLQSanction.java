//package eu.donttradewithrussia.database.querydesignations.PSQL;
//
//public class PSQLQSanction extends PSQLQueries {
//
//    //select
//    public static final String SELECT_SANCTION = SELECT +
//            SANCTION_ID + C +
//            TABLE_SANCTION + P + COALITION + C +
//            TABLE_SANCTION + P + COUNTRY + C +
//            SANCTION_PERIOD_START + C +
//            SANCTION_PERIOD_END + C +
//            SANCTION_CONTENT + FROM + TABLE_MONTHLY_TRADE;
//
//    //parameter
//    public static final String PARAMETER_ID = SANCTION_ID + PARAMETER;
//    public static final String PARAMETER_GROUP =  TABLE_SANCTION + P + COALITION + PARAMETER;
//    public static final String PARAMETER_COUNTRY = TABLE_SANCTION + P + COUNTRY + PARAMETER;
//    public static final String PARAMETER_PERIOD_START = SANCTION_PERIOD_START + PARAMETER;
//    public static final String PARAMETER_PERIOD_END = SANCTION_PERIOD_END + PARAMETER;
//    public static final String PARAMETER_CONTENT = SANCTION_CONTENT + PARAMETER;
//    public static final String PARAMETER_PERIOD_BETWEEN =
//            SANCTION_PERIOD_START + BIGGER + V + AND +
//            SANCTION_PERIOD_END + SMALLER + V;
//
//    //delete
//    public static final String SANCTION_DELETE = DELETE + FROM + TABLE_SANCTION;
//
//    //queries
//    public static final String QUERY_INSERT_GROUP = INSERT_INTO + TABLE_SANCTION + "(" +
//            TABLE_SANCTION + P + COALITION + C +
//            SANCTION_PERIOD_START + C +
//            SANCTION_PERIOD_END + C +
//            SANCTION_CONTENT + ") VALUES (?,?,?,?)"  + RETURNING + SANCTION_ID + END;
//    public static final String QUERY_INSERT_COUNTRY = INSERT_INTO + TABLE_SANCTION + "(" +
//            TABLE_SANCTION + P + COUNTRY + C +
//            SANCTION_PERIOD_START + C +
//            SANCTION_PERIOD_END + C +
//            SANCTION_CONTENT + ") VALUES (?,?,?,?)"  + RETURNING + SANCTION_ID + END;
//    public static final String QUERY_DELETE_WHERE_GROUP = SANCTION_DELETE + WHERE +
//            PARAMETER_GROUP + AND +
//            PARAMETER_PERIOD_START + END;
//    public static final String QUERY_DELETE_WHERE_COUNTRY = SANCTION_DELETE + WHERE +
//            PARAMETER_COUNTRY + AND +
//            SANCTION_PERIOD_START + END;
//
//    public static String queryWhereGroupAndPeriod(String select) {
//        return CREATE_JSON_MULTIPLE + FROM_START + select + WHERE +
//                PARAMETER_GROUP + AND +
//                PARAMETER_PERIOD_BETWEEN + ORDER_BY +
//                SANCTION_PERIOD_START + FROM_END + AS + ROW + END;
//    }
//    public static String queryWhereCountryAndPeriod(String select) {
//        return CREATE_JSON_MULTIPLE + FROM_START + select + WHERE +
//                PARAMETER_COUNTRY + AND +
//                PARAMETER_PERIOD_BETWEEN + ORDER_BY +
//                SANCTION_PERIOD_START + FROM_END + AS + ROW + END;
//    }
//}
