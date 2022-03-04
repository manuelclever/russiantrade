package eu.donttradewithrussia.database.query.PSQL;

public class PSQLQDataset extends PSQLQueries {

    //select
    public static final String DATASET_SELECT_DATASET = SELECT +
            MONTHLY_TRADE_ID + C +
            TABLE_MONTHLY_TRADE + P + PERIOD + C +
            MONTHLY_TRADE_REPORTER + C +
            MONTHLY_TRADE_PARTNER + C +
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

    //insert
    public static final String INSERT = INSERT_INTO + TABLE_MONTHLY_TRADE + "(" +
            TABLE_MONTHLY_TRADE +  P + PERIOD + C +
            MONTHLY_TRADE_REPORTER + C +
            MONTHLY_TRADE_PARTNER + C +
            MONTHLY_TRADE_COMMODITY_CODE + C +
            MONTHLY_TRADE_VALUE + C +
            MONTHLY_TRADE_SANCTION_GLOBAL + C +
            MONTHLY_TRADE_SANCTION_LOCAL + ") VALUES (?,?,?,?,?,?,?)"  + RETURNING + MONTHLY_TRADE_ID + END;

    //delete
    public static final String MONTHLY_TRADE_DELETE = DELETE + FROM + TABLE_MONTHLY_TRADE;


    //queries
    public static final String QUERY_DELETE = MONTHLY_TRADE_DELETE + WHERE +
            PARAMETER_REPORTER + AND +
            PARAMETER_PERIOD + END;
    public static final String QUERY_DELETE_BETWEEN = MONTHLY_TRADE_DELETE + WHERE +
            PARAMETER_REPORTER + AND +
            PARAMETER_PERIOD_BETWEEN + END;

    public static String queryWhereReporterAndPeriod(String select) {
        return CREATE_JSON + FROM_START + select + WHERE +
                PARAMETER_REPORTER + AND +
                PARAMETER_PERIOD + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
    public static String queryWhereReporterAndPeriodBetween(String select) {
        return CREATE_JSON + FROM_START + select + WHERE +
                PARAMETER_REPORTER + AND +
                PARAMETER_PERIOD_BETWEEN + ORDER_BY +
                TABLE_MONTHLY_TRADE + P + PERIOD + FROM_END + AS + ROW + END;
    }
}
