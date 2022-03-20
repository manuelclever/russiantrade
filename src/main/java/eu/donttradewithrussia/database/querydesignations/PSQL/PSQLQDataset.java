package eu.donttradewithrussia.database.querydesignations.PSQL;

public class PSQLQDataset extends PSQLQueries {

    //join
    public static final String JOIN_COUNTRY_REPORTER = INNER_JOIN + TABLE_COUNTRY +
            ON + MONTHLY_TRADE_REPORTER + " = " + COUNTRY_ID;
    public static final String JOIN_COUNTRY_PARTNER = INNER_JOIN + TABLE_COUNTRY +
            ON + MONTHLY_TRADE_PARTNER + " = " + COUNTRY_ID;
    public static final String JOIN_SACTION_GLOBAL = LEFT_JOIN + TABLE_SANCTION +
            ON + MONTHLY_TRADE_SANCTION_GLOBAL + " = " + SANCTION_ID;
    public static final String JOIN_SACTION_LOCAL = LEFT_JOIN + TABLE_SANCTION +
            ON + MONTHLY_TRADE_SANCTION_LOCAL + " = " + SANCTION_ID;

    //select
    public static final String SELECT_DATASET = SELECT +
            MONTHLY_TRADE_ID + C +
            TABLE_MONTHLY_TRADE + P + PERIOD + C +
            MONTHLY_TRADE_REPORTER + C +
            TABLE_COUNTRY + P + COUNTRY_NAME + C +
            MONTHLY_TRADE_PARTNER + C +
            TABLE_COUNTRY + P + COUNTRY_NAME + C +
            MONTHLY_TRADE_COMMODITY_CODE + C +
            MONTHLY_TRADE_VALUE + C +
            MONTHLY_TRADE_SANCTION_GLOBAL + C +
            MONTHLY_TRADE_SANCTION_LOCAL + FROM + TABLE_MONTHLY_TRADE;

    //parameter
    public static final String PARAMETER_ID = MONTHLY_TRADE_ID + PARAMETER;
    public static final String PARAMETER_REPORTER = MONTHLY_TRADE_REPORTER + PARAMETER;
    public static final String PARAMETER_PARTNER = MONTHLY_TRADE_PARTNER + PARAMETER;
    public static final String PARAMETER_PERIOD = TABLE_MONTHLY_TRADE + P + PERIOD + PARAMETER;
    public static final String PARAMETER_PERIOD_BETWEEN =
            TABLE_MONTHLY_TRADE + P + PERIOD + BETWEEN + V + AND + V;
    public static final String PARAMETER_COMMODITY = MONTHLY_TRADE_COMMODITY_CODE + PARAMETER;

    //delete
    public static final String MONTHLY_TRADE_DELETE = DELETE + FROM + TABLE_MONTHLY_TRADE;

    //queries
    public static final String QUERY_INSERT = INSERT_INTO + TABLE_MONTHLY_TRADE + "(" +
            PERIOD + C +
            MONTHLY_TRADE_REPORTER + C +
            MONTHLY_TRADE_PARTNER + C +
            MONTHLY_TRADE_FLOW + C +
            MONTHLY_TRADE_COMMODITY_CODE + C +
            MONTHLY_TRADE_COMMODITY_DESC + C +
            MONTHLY_TRADE_VALUE + ") VALUES (?,?,?,?,?,?,?)"  + RETURNING + MONTHLY_TRADE_ID + END;
    public static final String QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY = MONTHLY_TRADE_DELETE + WHERE +
            PARAMETER_REPORTER + AND +
            PARAMETER_PERIOD + AND +
            PARAMETER_COMMODITY + END;
    public static final String QUERY_DELETE_WHERE_REPORTER_AND_PERIOD = MONTHLY_TRADE_DELETE + WHERE +
            PARAMETER_REPORTER + AND +
            PARAMETER_PERIOD + END;
    public static final String QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY_BETWEEN =
            MONTHLY_TRADE_DELETE + WHERE +
            PARAMETER_REPORTER + AND +
            PARAMETER_PERIOD_BETWEEN + AND +
            PARAMETER_COMMODITY + END;
    public static final String QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_BETWEEN = MONTHLY_TRADE_DELETE + WHERE +
            PARAMETER_REPORTER + AND +
            PARAMETER_PERIOD_BETWEEN + END;

    public static String queryWhereReporterAndPartner(String select) {
        return CREATE_JSON + FROM_START + select +
                JOIN_COUNTRY_REPORTER +
                JOIN_COUNTRY_REPORTER + WHERE +
                PARAMETER_REPORTER + AND +
                PARAMETER_PARTNER + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
    public static String queryWhereReporterPartnerAndPeriod(String select) {
        return CREATE_JSON + FROM_START + select +
                JOIN_COUNTRY_REPORTER +
                JOIN_COUNTRY_REPORTER + WHERE +
                PARAMETER_REPORTER + AND +
                PARAMETER_PARTNER + AND +
                PARAMETER_PERIOD + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
    public static String queryWhereCommodityReporterPartnerAndPeriod(String select) {
        return CREATE_JSON + FROM_START + select +
                JOIN_COUNTRY_REPORTER +
                JOIN_COUNTRY_REPORTER + WHERE +
                PARAMETER_COMMODITY + AND +
                PARAMETER_REPORTER + AND +
                PARAMETER_PARTNER + AND +
                PARAMETER_PERIOD + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
    public static String queryWhereReporterPartnerAndPeriodBetween(String select) {
        return CREATE_JSON + FROM_START + select +
                JOIN_COUNTRY_REPORTER +
                JOIN_COUNTRY_REPORTER + WHERE +
                PARAMETER_REPORTER + AND +
                PARAMETER_PARTNER + AND +
                PARAMETER_PERIOD_BETWEEN + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
    public static String queryWhereCommodityReporterPartnerAndPeriodBetween(String select) {
        return CREATE_JSON + FROM_START + select +
                JOIN_COUNTRY_REPORTER +
                JOIN_COUNTRY_REPORTER + WHERE +
                PARAMETER_COMMODITY + AND +
                PARAMETER_REPORTER + AND +
                PARAMETER_PARTNER + AND +
                PARAMETER_PERIOD_BETWEEN + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
}
