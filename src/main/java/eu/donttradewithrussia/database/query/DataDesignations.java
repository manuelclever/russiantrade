package eu.donttradewithrussia.database.query;

public abstract class DataDesignations extends SQLQueries {

    //tables and columns
    public static final String SCHEMA = "trade_schema";

    //global
    public static final String ID = "_id";
    public static final String PERIOD = "period";
    public static final String GROUP = "collection";
    public static final String COUNTRY = "country";

    //local
    public static final String TABLE_GROUP = "coalition";
    public static final String GROUP_ID = TABLE_GROUP + ID;
    public static final String GROUP_NAME = "coalition";

    public static final String TABLE_COUNTRY = "country";
    public static final String COUNTRY_ID = TABLE_COUNTRY + ID;
    public static final String COUNTRY_NAME = "name";
    public static final String COUNTRY_ABBREV = "abbrev";
    public static final String COUNTRY_COMTRADE_CODE = "comtrade_code";

    public static final String TABLE_UNION = "coalition_country_union";
    public static final String UNION_ID = TABLE_UNION + ID;

    public static final String TABLE_SANCTION = "sanction";
    public static final String SANCTION_ID = TABLE_SANCTION + ID;
    public static final String SANCTION_PERIOD_START = "periodStart";
    public static final String SANCTION_PERIOD_END = "periodEnd";
    public static final String SANCTION_CONTENT = "content";

    public static final String TABLE_MONTHLY_TRADE = "monthly_trade";
    public static final String MONTHLY_TRADE_ID = TABLE_MONTHLY_TRADE + ID;
    public static final String MONTHLY_TRADE_FLOW = "trade_flow";
    public static final String MONTHLY_TRADE_REPORTER = "reporter";
    public static final String MONTHLY_TRADE_PARTNER = "partner";
    public static final String MONTHLY_TRADE_COMMODITY_CODE = "commodity_code";
    public static final String MONTHLY_TRADE_VALUE = "value";
    public static final String MONTHLY_TRADE_SANCTION_GLOBAL = "union_sanction";
    public static final String MONTHLY_TRADE_SANCTION_LOCAL = "custom_sanction";

    public static final String TABLE_CUSTOM_MONTHLY_TRADE = "custom_monthly_trade";
    public static final String CUSTOM_MONTHLY_TRADE_ID = TABLE_CUSTOM_MONTHLY_TRADE + ID;
}
