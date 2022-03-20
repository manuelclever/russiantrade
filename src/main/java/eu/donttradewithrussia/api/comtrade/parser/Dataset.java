package eu.donttradewithrussia.api.comtrade.parser;

public class Dataset {
    private String pfCode;
    private int year;
    private int period;
    private int periodDesc;

    private int aggrLevel;
    private int isLeaf;

    private int tradeFlowCode;
    private String tradeFlowType;

    private int reporterCode;
    private String reporterDesc;
    private String rt3iso;

    private int partnerCode;
    private String partnerDesc;
    private String pt3iso;

    private int partnerCode2;
    private String partnerDesc2;
    private String pt3iso2;

    private String commodityCode;
    private String commodityDesc;

    private int qtCode;
    private String qtDesc;

    private int qtAltCode;
    private String qtAltDesc;

    private int tradeQuantity;
    private int altQuantity;
    private long tradeValue;

    private int cifValue;
    private int fobValue;
    private int estCode;

    public Dataset() {
    }

    public String getPfCode() {
        return pfCode;
    }

    public void setPfCode(String pfCode) {
        this.pfCode = pfCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriodDesc() {
        return periodDesc;
    }

    public void setPeriodDesc(int periodDesc) {
        this.periodDesc = periodDesc;
    }

    public int getAggrLevel() {
        return aggrLevel;
    }

    public void setAggrLevel(int aggrLevel) {
        this.aggrLevel = aggrLevel;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getTradeFlowCode() {
        return tradeFlowCode;
    }

    public void setTradeFlowCode(int tradeFlowCode) {
        this.tradeFlowCode = tradeFlowCode;
    }

    public String getTradeFlowType() {
        return tradeFlowType;
    }

    public void setTradeFlowType(String tradeFlowType) {
        this.tradeFlowType = tradeFlowType;
    }

    public int getReporterCode() {
        return reporterCode;
    }

    public void setReporterCode(int rtCode) {
        this.reporterCode = rtCode;
    }

    public String getReporterDesc() {
        return reporterDesc;
    }

    public void setReporterDesc(String rtTitle) {
        this.reporterDesc = rtTitle;
    }

    public String getRt3iso() {
        return rt3iso;
    }

    public void setRt3iso(String rt3iso) {
        this.rt3iso = rt3iso;
    }

    public int getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(int ptCode) {
        this.partnerCode = ptCode;
    }

    public String getPartnerDesc() {
        return partnerDesc;
    }

    public void setPartnerDesc(String ptTitle) {
        this.partnerDesc = ptTitle;
    }

    public String getPt3iso() {
        return pt3iso;
    }

    public void setPt3iso(String pt3iso) {
        this.pt3iso = pt3iso;
    }

    public int getPartnerCode2() {
        return partnerCode2;
    }

    public void setPartnerCode2(int ptCode2) {
        this.partnerCode2 = ptCode2;
    }

    public String getPartnerDesc2() {
        return partnerDesc2;
    }

    public void setPartnerDesc2(String ptTitle2) {
        this.partnerDesc2 = ptTitle2;
    }

    public String getPt3iso2() {
        return pt3iso2;
    }

    public void setPt3iso2(String pt3iso2) {
        this.pt3iso2 = pt3iso2;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityDesc() {
        return commodityDesc;
    }

    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc;
    }

    public int getQtCode() {
        return qtCode;
    }

    public void setQtCode(int qtCode) {
        this.qtCode = qtCode;
    }

    public String getQtDesc() {
        return qtDesc;
    }

    public void setQtDesc(String qtDesc) {
        this.qtDesc = qtDesc;
    }

    public int getQtAltCode() {
        return qtAltCode;
    }

    public void setQtAltCode(int qtAltCode) {
        this.qtAltCode = qtAltCode;
    }

    public String getQtAltDesc() {
        return qtAltDesc;
    }

    public void setQtAltDesc(String qtAltDesc) {
        this.qtAltDesc = qtAltDesc;
    }

    public int getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(int tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public int getAltQuantity() {
        return altQuantity;
    }

    public void setAltQuantity(int altQuantity) {
        this.altQuantity = altQuantity;
    }

    public long getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(long tradeValue) {
        this.tradeValue = tradeValue;
    }

    public int getCifValue() {
        return cifValue;
    }

    public void setCifValue(int cifValue) {
        this.cifValue = cifValue;
    }

    public int getFobValue() {
        return fobValue;
    }

    public void setFobValue(int fobValue) {
        this.fobValue = fobValue;
    }

    public int getEstCode() {
        return estCode;
    }

    public void setEstCode(int estCode) {
        this.estCode = estCode;
    }

    @Override
    public String toString() {
        return "[" + period + ", " + tradeFlowType + ", " + reporterDesc + ", " + partnerDesc + ", " + commodityCode +
                ", " + commodityDesc + ", " + tradeValue + "]";
    }
}
