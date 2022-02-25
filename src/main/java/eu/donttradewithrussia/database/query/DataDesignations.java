package eu.donttradewithrussia.database.query;

public abstract class DataDesignations {
    //
    public static final String P = ".";
    public static final String C = ", ";

    //tables and columns
    public static final String SCHEME = "trade_scheme";

    //global
    public static final String ID = "_id";
    public static final String PERIOD = "period";

    //local
    public static final String GROUP = "group";
    public static final String TABLE_GROUP = SCHEME + P + GROUP;
    public static final String GROUP_ID = GROUP + ID;
    public static final String GROUP_NAME = "coalition";

    public static final String COUNTRY = "country";
    public static final String TABLE_COUNTRY = SCHEME + P + COUNTRY;
    public static final String COUNTRY_ID = COUNTRY + ID;
    public static final String COUNTRY_NAME = "name";
    public static final String COUNTRY_ABBREV = "abbrev.";
    public static final String COUNTRY_COMTRADE_CODE = "comtrade_code";

    public static final String UNION_GROUP_COUNTRY = "union";
    public static final String TABLE_UNION_GROUP_COUNTRY = SCHEME + P + UNION_GROUP_COUNTRY;
    public static final String UNION_GROUP_COUNTRY_ID = UNION_GROUP_COUNTRY + ID;

    public static final String SANCTION = "sanction";
    public static final String TABLE_SANCTION = SCHEME + P + SANCTION;
    public static final String SANCTION_ID = SANCTION + ID;
    public static final String SANCTION_DESIGNATION = "designation";
    public static final String SANCTION_CONTENT = "content";

    public static final String MONTHLY_TRADE = "monthly_trade";
    public static final String TABLE_MONTHLY_TRADE = SCHEME + P + MONTHLY_TRADE;
    public static final String MONTHLY_TRADE_ID = MONTHLY_TRADE + ID;
    public static final String MONTHLY_TRADE_FLOW = "trade_flow";
    public static final String MONTHLY_TRADE_REPORTER = "reporter";
    public static final String MONTHLY_TRADE_PARTNER = "partner";
    public static final String MONTHLY_TRADE_COMMODITY_CODE = "commodity_code";
    public static final String MONTHLY_TRADE_VALUE = "value";
    public static final String MONTHLY_TRADE_SANCTION_GLOBAL = "union_sanction";
    public static final String MONTHLY_TRADE_SANCTION_LOCAL = "custom_sanction";

    public static final String CUSTOM_MONTHLY_TRADE = "custom_monthly_trade";
    public static final String TABLE_CUSTOM_MONTHLY_TRADE = SCHEME + P + CUSTOM_MONTHLY_TRADE;
    public static final String CUSTOM_MONTHLY_TRADE_ID = CUSTOM_MONTHLY_TRADE + ID;
}
