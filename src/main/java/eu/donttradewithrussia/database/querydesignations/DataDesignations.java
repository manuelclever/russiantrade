package eu.donttradewithrussia.database.querydesignations;

public abstract class DataDesignations extends SQLQueries {

    //tables and columns
    public static final String SCHEMA = "tradetest_schema";

    //global
    public static final String ID = "_id";
    public static final String PERIOD = "period";

    //local
    public static final String COALITION = "coalition";
    public static final String TABLE_COALITION = SCHEMA + P + COALITION;
    public static final String COALITION_ID = COALITION + ID;
    public static final String COALITION_NAME = "coalition";

    public static final String COUNTRY = "country";
    public static final String TABLE_COUNTRY = SCHEMA + P + COUNTRY;
    public static final String COUNTRY_ID = COUNTRY + ID;
    public static final String COUNTRY_NAME = "name";
    public static final String COUNTRY_ABBREV = "abbrev";
    public static final String COUNTRY_COMTRADE_ID = "comtrade_code";

    public static final String UNION = "coalition_country_union";
    public static final String TABLE_UNION = SCHEMA + P + UNION;
    public static final String UNION_ID = UNION + ID;

    public static final String SANCTION = "sanction";
    public static final String TABLE_SANCTION = SCHEMA + P + SANCTION;
    public static final String SANCTION_ID = SANCTION + ID;
    public static final String SANCTION_PERIOD_START = "periodStart";
    public static final String SANCTION_PERIOD_END = "periodEnd";
    public static final String SANCTION_CONTENT = "content";

    public static final String MONTHLY_TRADE = "monthly_trade";
    public static final String TABLE_MONTHLY_TRADE = SCHEMA + P + MONTHLY_TRADE;
    public static final String MONTHLY_TRADE_ID = MONTHLY_TRADE + ID;
    public static final String MONTHLY_TRADE_FLOW = "trade_flow";
    public static final String MONTHLY_TRADE_REPORTER = "reporter";
    public static final String MONTHLY_TRADE_PARTNER = "partner";
    public static final String MONTHLY_TRADE_COMMODITY_CODE = "commodity_code";
    public static final String MONTHLY_TRADE_COMMODITY_DESC = "commodity_desc";
    public static final String MONTHLY_TRADE_VALUE = "value";
    public static final String MONTHLY_TRADE_SANCTION_GLOBAL = "union_sanction";
    public static final String MONTHLY_TRADE_SANCTION_LOCAL = "custom_sanction";

    public static final String CUSTOM_MONTHLY_TRADE = "custom_monthly_trade";
    public static final String TABLE_CUSTOM_MONTHLY_TRADE = SCHEMA + P + CUSTOM_MONTHLY_TRADE;
    public static final String CUSTOM_MONTHLY_TRADE_ID = CUSTOM_MONTHLY_TRADE + ID;
}
