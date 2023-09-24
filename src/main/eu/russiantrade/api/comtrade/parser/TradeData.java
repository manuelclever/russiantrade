package eu.russiantrade.api.comtrade.parser;

public class TradeData {
    private char typeCode;
    private char freqCode;


    private int refPeriodId;
    private short refYear;
    private short refMonth;
    private short period;


    private String flowCode;
    private String flowDesc;

    
    private int reporterCode;
    private String reporterDesc;
    private String reporterIso;
    
    
    private int partnerCode;
    private String partnerDesc;
    private String partnerIso;


    private int partner2Code;
    private String partner2Desc;
    private String partner2Iso;


    private String classificationCode;
    private String classificationSearchCode;
    private boolean isOriginalClassification;


    private String commodityCode;
    private String commodityDesc;


    private int aggregationLevel;
    private boolean isLeaf;


    private String customsCode;
    private String customsDesc;


    private int modeOfSupplyCode;


    private int modeOfTransportCode;
    private String modeOfTransportDesc;


    private int quantityUnitCode;
    private String quantityUnitAbbreviation;
    private int quantity;
    private boolean isQuantityEstimated;


    private int alternativeQuantityUnitCode;
    private String alternativeQuantityUnitAbbreviation;
    private int alternativeQuantity;
    private boolean alternativeIsQuantityEstimated;


    private int netWeight;
    private boolean isNetWeightEstimated;


    private int grossWeight;
    private boolean isGrossWeightEstimated;


    private int costInsuranceFreightValue;
    private int freeOnBoardValue;
    private long primaryValue;

    private int legacyEstimationFlag;
    private boolean isReported;
    private boolean isAggregated;


    public TradeData() {
    }

    // initialize only with minimal data
    public TradeData(int refPeriodId, 
                     String flowDesc, 
                     int reporterCode, 
                     int partnerCode, 
                     String commodityCode,
                     long primaryValue) {
        this.refPeriodId = refPeriodId;
        this.flowDesc = flowDesc;
        this.reporterCode = reporterCode;
        this.partnerCode = partnerCode;
        this.commodityCode = commodityCode;
        this.primaryValue = primaryValue;
    }

    public TradeData(int refPeriodId,
                     String flowDesc,
                     int reporterCode,
                     int partnerCode,
                     String commodityCode,
                     String commodityDesc,
                     long primaryValue) {
        this.refPeriodId = refPeriodId;
        this.flowDesc = flowDesc;
        this.reporterCode = reporterCode;
        this.partnerCode = partnerCode;
        this.commodityCode = commodityCode;
        this.commodityDesc = commodityDesc;
        this.primaryValue = primaryValue;
    }

    public TradeData(char typeCode,
                     char freqCode,

                     int refPeriodId,
                     short refYear,
                     short refMonth,
                     short period,

                     String flowCode,
                     String flowDesc,

                     int reporterCode,
                     String reporterDesc,
                     String reporterIso,

                     int partnerCode,
                     String partnerDesc,
                     String partnerIso,

                     int partner2Code,
                     String partner2Desc,
                     String partner2Iso,

                     String classificationCode,
                     String classificationSearchCode,
                     boolean isOriginalClassification,

                     String commodityCode,
                     String commodityDesc,

                     int aggregationLevel,
                     boolean isLeaf,

                     String customsCode,
                     String customsDesc,

                     int modeOfSupplyCode,

                     int modeOfTransportCode,
                     String modeOfTransportDesc,

                     int quantityUnitCode,
                     String quantityUnitAbbreviation,
                     int quantity,
                     boolean isQuantityEstimated,

                     int alternativeQuantityUnitCode,
                     String alternativeQuantityUnitAbbreviation,
                     int alternativeQuantity,
                     boolean alternativeIsQuantityEstimated,

                     int netWeight,
                     boolean isNetWeightEstimated,

                     int grossWeight,
                     boolean isGrossWeightEstimated,

                     int costInsuranceFreightValue,
                     int freeOnBoardValue,
                     long primaryValue,

                     int legacyEstimationFlag,
                     boolean isReported,
                     boolean isAggregated) {
        this.typeCode = typeCode;
        this.freqCode = freqCode;

        this.refPeriodId = refPeriodId;
        this.refYear = refYear;
        this.refMonth = refMonth;
        this.period = period;

        this.flowCode = flowCode;
        this.flowDesc = flowDesc;

        this.reporterCode = reporterCode;
        this.reporterDesc = reporterDesc;
        this.reporterIso = reporterIso;

        this.partnerCode = partnerCode;
        this.partnerDesc = partnerDesc;
        this.partnerIso = partnerIso;

        this.partner2Code = partner2Code;
        this.partner2Desc = partner2Desc;
        this.partner2Iso = partner2Iso;

        this.classificationCode = classificationCode;
        this.classificationSearchCode = classificationSearchCode;
        this.isOriginalClassification = isOriginalClassification;

        this.commodityCode = commodityCode;
        this.commodityDesc = commodityDesc;

        this.aggregationLevel = aggregationLevel;
        this.isLeaf = isLeaf;

        this.customsCode = customsCode;
        this.customsDesc = customsDesc;

        this.modeOfSupplyCode = modeOfSupplyCode;

        this.modeOfTransportCode = modeOfTransportCode;
        this.modeOfTransportDesc = modeOfTransportDesc;

        this.quantityUnitCode = quantityUnitCode;
        this.quantityUnitAbbreviation = quantityUnitAbbreviation;
        this.quantity = quantity;
        this.isQuantityEstimated = isQuantityEstimated;

        this.alternativeQuantityUnitCode = alternativeQuantityUnitCode;
        this.alternativeQuantityUnitAbbreviation = alternativeQuantityUnitAbbreviation;
        this.alternativeQuantity = alternativeQuantity;
        this.alternativeIsQuantityEstimated = alternativeIsQuantityEstimated;

        this.netWeight = netWeight;
        this.isNetWeightEstimated = isNetWeightEstimated;

        this.grossWeight = grossWeight;
        this.isGrossWeightEstimated = isGrossWeightEstimated;

        this.costInsuranceFreightValue = costInsuranceFreightValue;
        this.freeOnBoardValue = freeOnBoardValue;
        this.primaryValue = primaryValue;

        this.legacyEstimationFlag = legacyEstimationFlag;
        this.isReported = isReported;
        this.isAggregated = isAggregated;

    }

    public char getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(char typeCode) {
        this.typeCode = typeCode;
    }

    public char getFreqCode() {
        return freqCode;
    }

    public void setFreqCode(char freqCode) {
        this.freqCode = freqCode;
    }

    public int getRefPeriodId() {
        return refPeriodId;
    }

    public void setRefPeriodId(int refPeriodId) {
        this.refPeriodId = refPeriodId;
    }

    public short getRefYear() {
        return refYear;
    }

    public void setRefYear(short refYear) {
        this.refYear = refYear;
    }

    public short getRefMonth() {
        return refMonth;
    }

    public void setRefMonth(short refMonth) {
        this.refMonth = refMonth;
    }

    public short getPeriod() {
        return period;
    }

    public void setPeriod(short period) {
        this.period = period;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getFlowDesc() {
        return flowDesc;
    }

    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc;
    }

    public int getReporterCode() {
        return reporterCode;
    }

    public void setReporterCode(int reporterCode) {
        this.reporterCode = reporterCode;
    }

    public String getReporterDesc() {
        return reporterDesc;
    }

    public void setReporterDesc(String reporterDesc) {
        this.reporterDesc = reporterDesc;
    }

    public String getReporterIso() {
        return reporterIso;
    }

    public void setReporterIso(String reporterIso) {
        this.reporterIso = reporterIso;
    }

    public int getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(int partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerDesc() {
        return partnerDesc;
    }

    public void setPartnerDesc(String partnerDesc) {
        this.partnerDesc = partnerDesc;
    }

    public String getPartnerIso() {
        return partnerIso;
    }

    public void setPartnerIso(String partnerIso) {
        this.partnerIso = partnerIso;
    }

    public int getPartner2Code() {
        return partner2Code;
    }

    public void setPartner2Code(int partner2Code) {
        this.partner2Code = partner2Code;
    }

    public String getPartner2Desc() {
        return partner2Desc;
    }

    public void setPartner2Desc(String partner2Desc) {
        this.partner2Desc = partner2Desc;
    }

    public String getPartner2Iso() {
        return partner2Iso;
    }

    public void setPartner2Iso(String partner2Iso) {
        this.partner2Iso = partner2Iso;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public String getClassificationSearchCode() {
        return classificationSearchCode;
    }

    public void setClassificationSearchCode(String classificationSearchCode) {
        this.classificationSearchCode = classificationSearchCode;
    }

    public boolean isOriginalClassification() {
        return isOriginalClassification;
    }

    public void setOriginalClassification(boolean originalClassification) {
        isOriginalClassification = originalClassification;
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

    public int getAggregationLevel() {
        return aggregationLevel;
    }

    public void setAggregationLevel(int aggregationLevel) {
        this.aggregationLevel = aggregationLevel;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCustomsDesc() {
        return customsDesc;
    }

    public void setCustomsDesc(String customsDesc) {
        this.customsDesc = customsDesc;
    }

    public int getModeOfSupplyCode() {
        return modeOfSupplyCode;
    }

    public void setModeOfSupplyCode(int modeOfSupplyCode) {
        this.modeOfSupplyCode = modeOfSupplyCode;
    }

    public int getModeOfTransportCode() {
        return modeOfTransportCode;
    }

    public void setModeOfTransportCode(int modeOfTransportCode) {
        this.modeOfTransportCode = modeOfTransportCode;
    }

    public String getModeOfTransportDesc() {
        return modeOfTransportDesc;
    }

    public void setModeOfTransportDesc(String modeOfTransportDesc) {
        this.modeOfTransportDesc = modeOfTransportDesc;
    }

    public int getQuantityUnitCode() {
        return quantityUnitCode;
    }

    public void setQuantityUnitCode(int quantityUnitCode) {
        this.quantityUnitCode = quantityUnitCode;
    }

    public String getQuantityUnitAbbreviation() {
        return quantityUnitAbbreviation;
    }

    public void setQuantityUnitAbbreviation(String quantityUnitAbbrevation) {
        this.quantityUnitAbbreviation = quantityUnitAbbrevation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isQuantityEstimated() {
        return isQuantityEstimated;
    }

    public void setQuantityEstimated(boolean quantityEstimated) {
        isQuantityEstimated = quantityEstimated;
    }

    public int getAlternativeQuantityUnitCode() {
        return alternativeQuantityUnitCode;
    }

    public void setAlternativeQuantityUnitCode(int alternativeQuantityUnitCode) {
        this.alternativeQuantityUnitCode = alternativeQuantityUnitCode;
    }

    public String getAlternativeQuantityUnitAbbreviation() {
        return alternativeQuantityUnitAbbreviation;
    }

    public void setAlternativeQuantityUnitAbbreviation(String alternativeQuantityUnitAbbreviation) {
        this.alternativeQuantityUnitAbbreviation = alternativeQuantityUnitAbbreviation;
    }

    public int getAlternativeQuantity() {
        return alternativeQuantity;
    }

    public void setAlternativeQuantity(int alternativeQuantity) {
        this.alternativeQuantity = alternativeQuantity;
    }

    public boolean isAlternativeIsQuantityEstimated() {
        return alternativeIsQuantityEstimated;
    }

    public void setAlternativeIsQuantityEstimated(boolean alternativeIsQuantityEstimated) {
        this.alternativeIsQuantityEstimated = alternativeIsQuantityEstimated;
    }

    public int getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(int netWeight) {
        this.netWeight = netWeight;
    }

    public boolean isNetWeightEstimated() {
        return isNetWeightEstimated;
    }

    public void setNetWeightEstimated(boolean netWeightEstimated) {
        isNetWeightEstimated = netWeightEstimated;
    }

    public int getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(int grossWeight) {
        this.grossWeight = grossWeight;
    }

    public boolean isGrossWeightEstimated() {
        return isGrossWeightEstimated;
    }

    public void setGrossWeightEstimated(boolean grossWeightEstimated) {
        isGrossWeightEstimated = grossWeightEstimated;
    }

    public int getCostInsuranceFreightValue() {
        return costInsuranceFreightValue;
    }

    public void setCostInsuranceFreightValue(int costInsuranceFreightValue) {
        this.costInsuranceFreightValue = costInsuranceFreightValue;
    }

    public int getFreeOnBoardValue() {
        return freeOnBoardValue;
    }

    public void setFreeOnBoardValue(int freeOnBoardValue) {
        this.freeOnBoardValue = freeOnBoardValue;
    }

    public long getPrimaryValue() {
        return primaryValue;
    }

    public void setPrimaryValue(long primaryValue) {
        this.primaryValue = primaryValue;
    }

    public int getLegacyEstimationFlag() {
        return legacyEstimationFlag;
    }

    public void setLegacyEstimationFlag(int legacyEstimationFlag) {
        this.legacyEstimationFlag = legacyEstimationFlag;
    }

    public boolean isReported() {
        return isReported;
    }

    public void setReported(boolean reported) {
        isReported = reported;
    }

    public boolean isAggregated() {
        return isAggregated;
    }

    public void setAggregated(boolean aggregated) {
        isAggregated = aggregated;
    }

    @Override
    public String toString() {
        return "[" + refPeriodId + ", " + flowDesc + ", " + reporterDesc + ", " + partnerDesc + ", " + commodityCode +
                ", " + commodityDesc + ", " + primaryValue + "]";
    }

    // only data also present in database columns is compared for hash
    @Override
    public int hashCode() {
        return refPeriodId + (flowDesc != null ? flowDesc.hashCode() : 0) + reporterCode + partnerCode +
                (commodityCode != null ? commodityCode.hashCode() : 0) + (int) primaryValue;
    }

    public String hashCodeString() {
        return "[" + refPeriodId + ", " + flowDesc + ", " + reporterCode + ", " + partnerCode + ", " + commodityCode +
                ", " + primaryValue + "]";
    }
}
