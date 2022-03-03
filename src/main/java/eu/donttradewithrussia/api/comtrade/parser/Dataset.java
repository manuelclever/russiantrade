package eu.donttradewithrussia.api.comtrade.parser;

public class Dataset {
    private String pfCode;
    private int year;
    private int period;

    private int aggrLevel;
    private int isLeaf;

    private int rgCode;
    private String rgDesc;
    private int rtCode;

    private String rtTitle;
    private String rt3iso;

    private int ptCode;
    private String ptTitle;
    private String pt3iso;

    private int ptCode2;
    private String ptTitle2;
    private String pt3iso2;

    private int cmdCode;
    private String cmdDesc;

    private int qtCode;
    private String qtDesc;

    private int qtAltCode;
    private String qtAltDesc;

    private int tradeQuantity;
    private int altQuantity;
    private int tradeValue;

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

    public int getRgCode() {
        return rgCode;
    }

    public void setRgCode(int rgCode) {
        this.rgCode = rgCode;
    }

    public String getRgDesc() {
        return rgDesc;
    }

    public void setRgDesc(String rgDesc) {
        this.rgDesc = rgDesc;
    }

    public int getRtCode() {
        return rtCode;
    }

    public void setRtCode(int rtCode) {
        this.rtCode = rtCode;
    }

    public String getRtTitle() {
        return rtTitle;
    }

    public void setRtTitle(String rtTitle) {
        this.rtTitle = rtTitle;
    }

    public String getRt3iso() {
        return rt3iso;
    }

    public void setRt3iso(String rt3iso) {
        this.rt3iso = rt3iso;
    }

    public int getPtCode() {
        return ptCode;
    }

    public void setPtCode(int ptCode) {
        this.ptCode = ptCode;
    }

    public String getPtTitle() {
        return ptTitle;
    }

    public void setPtTitle(String ptTitle) {
        this.ptTitle = ptTitle;
    }

    public String getPt3iso() {
        return pt3iso;
    }

    public void setPt3iso(String pt3iso) {
        this.pt3iso = pt3iso;
    }

    public int getPtCode2() {
        return ptCode2;
    }

    public void setPtCode2(int ptCode2) {
        this.ptCode2 = ptCode2;
    }

    public String getPtTitle2() {
        return ptTitle2;
    }

    public void setPtTitle2(String ptTitle2) {
        this.ptTitle2 = ptTitle2;
    }

    public String getPt3iso2() {
        return pt3iso2;
    }

    public void setPt3iso2(String pt3iso2) {
        this.pt3iso2 = pt3iso2;
    }

    public int getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(int cmdCode) {
        this.cmdCode = cmdCode;
    }

    public String getCmdDesc() {
        return cmdDesc;
    }

    public void setCmdDesc(String cmdDesc) {
        this.cmdDesc = cmdDesc;
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

    public int getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(int tradeValue) {
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
        return "[" + period + ", " + rgDesc + ", " + rtTitle + ", " + ptTitle + ", " + tradeValue + "]";
    }
}
