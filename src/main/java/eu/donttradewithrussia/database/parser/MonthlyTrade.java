package eu.donttradewithrussia.database.parser;

public class MonthlyTrade {
    // all variable names have to equal DataDesignations of column in database
    private int monthly_trade_id;
    private String trade_flow;
    private int reporter;
    private String reporter_name;
    private int partner;
    private String reporter_partner;
    private int commodity_code;
    private String commodity_desc;
    private long value;

    public MonthlyTrade(int monthly_trade_id, String trade_flow, int reporter, String reporter_name, int partner, String reporter_partner, int commodity_code, String commodity_desc, long value) {
        this.monthly_trade_id = monthly_trade_id;
        this.trade_flow = trade_flow;
        this.reporter = reporter;
        this.reporter_name = reporter_name;
        this.partner = partner;
        this.reporter_partner = reporter_partner;
        this.commodity_code = commodity_code;
        this.commodity_desc = commodity_desc;
        this.value = value;
    }

    public int getMonthly_trade_id() {
        return monthly_trade_id;
    }

    public void setMonthly_trade_id(int monthly_trade_id) {
        this.monthly_trade_id = monthly_trade_id;
    }

    public String getTrade_flow() {
        return trade_flow;
    }

    public void setTrade_flow(String trade_flow) {
        this.trade_flow = trade_flow;
    }

    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    public String getReporter_name() {
        return reporter_name;
    }

    public void setReporter_name(String reporter_name) {
        this.reporter_name = reporter_name;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public String getReporter_partner() {
        return reporter_partner;
    }

    public void setReporter_partner(String reporter_partner) {
        this.reporter_partner = reporter_partner;
    }

    public int getCommodity_code() {
        return commodity_code;
    }

    public void setCommodity_code(int commodity_code) {
        this.commodity_code = commodity_code;
    }

    public String getCommodity_desc() {
        return commodity_desc;
    }

    public void setCommodity_desc(String commodity_desc) {
        this.commodity_desc = commodity_desc;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
