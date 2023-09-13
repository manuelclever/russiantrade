package eu.russiantrade.api.comtrade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class ComtradeAPIParameters {
    private static final String TYPE_OF_TRADE = "typeCode="; // Commodities (C) or Services (S)
    private static final String FREQUENCY = "freqCode="; // Annual (A), Monthly (M)
    private static final String CLASSIFICATION = "clCode="; // Classification. SITC S4 is used (S4)
    private static final String REPORTER = "reporterCode=";
    private static final String PERIOD = "period="; // Years or Months, depends on chosen frequency
    private static final String PARTNER = "partnerCode=";
    private static final String COMMODITY = "cmdCode=";
    private static final String TRADE_FLOW = "flowCode="; // -1 for all, Imports (M), Exports (X)
    private static final String CUSTOMS = "customsCode="; // -1 for all
    private static final String MODE_OF_TRANSPORT = "motCode="; // -1 for all
    private static final String INCLUDE_DESC = "includeDesc="; // true, false

    private String typeOfTrade;
    private String frequency;
    private String classification;
    private String reporter;
    private String period;
    private String partner;
    private String commodity;
    private String tradeFlow;
    private String customs;
    private String modeOfTransport;
    private String includeDesc;
    private String token;


    /**
     * Set parameters for API call.
     * <p>
     * Last updated: 2023/09
     * @param typeOfTrade C - Commodities, S - Services (default C)
     * @param frequency A - Annual, M - Monthly (default A)
     * @param classification SITC S4 is used (S4)
     * @param reporter Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param commodity Commodity code. Multi value input should be in the form of csv (Codes separated by comma (,))
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param customs -1 All
     * @param modeOfTransport -1 - All
     * @param includeDesc Boolean
     * @param token subscription token
     */

    public ComtradeAPIParameters(char typeOfTrade,
                                 char frequency,
                                 String classification,
                                 short reporter,
                                 long period,
                                 short partner,
                                 String[] commodity,
                                 String[] tradeFlow,
                                 String customs,
                                 int[] modeOfTransport,
                                 Boolean includeDesc,
                                 String token) {
        setTypeOfTrade(typeOfTrade);
        setFrequency(frequency);
        setClassification(classification);
        setReporter(reporter);
        setPeriod(period);
        setPartner(partner);
        setCommodity(commodity);
        setTradeFlow(tradeFlow);
        setCustoms(customs);
        setModeOfTransport(modeOfTransport);
        setIncludeDesc(includeDesc);
        setToken(token);
    }

    public ComtradeAPIParameters(char frequency,
                                 short reporter,
                                 long period,
                                 short partner,
                                 String[] commodity,
                                 String[] tradeFlow,
                                 Boolean includeDesc,
                                 String token) {
        setFrequency(frequency);
        setReporter(reporter);
        setPeriod(period);
        setPartner(partner);
        setCommodity(commodity);
        setTradeFlow(tradeFlow);
        setIncludeDesc(includeDesc);
        setToken(token);
    }

    public void setTypeOfTrade(char typeOfTrade) {
        if (typeOfTrade == 'C' | typeOfTrade == 'S' ) {
            this.typeOfTrade = TYPE_OF_TRADE + typeOfTrade;
        } else {
            this.typeOfTrade = null;
        }
    }

    public void setFrequency(char frequency) {
        if (frequency == 'A' | frequency == 'M' ) {
            this.frequency = FREQUENCY + frequency;
        } else {
            this.typeOfTrade = null;
        }
    }

    public void setClassification(String classification) {
        if (classification != null) {
            this.classification = CLASSIFICATION + classification;
        } else {
            this.classification = null;
        }
    }

    public void setReporter(short reporter) {
        this.reporter = REPORTER + reporter;
    }

    public void setPeriod(long period) {
        this.period = PERIOD + period;
    }

    public void setPartner(short partner) {
        this.partner = PARTNER + partner;
    }

    public void setCommodity(String[] commodityCodes) {
        if (commodityCodes != null) {
            StringBuilder sb = new StringBuilder(COMMODITY);

            for(String commodityCode : commodityCodes) {
                sb.append(commodityCode).append("%2C");
            }
            sb.replace(sb.length()-3, sb.length(), "");
            this.commodity = sb.toString();
        } else {
            this.commodity = null;
        }
    }

    public void setTradeFlow(String[] tradeFlow) {
        if (tradeFlow != null) {
            StringBuilder sb = new StringBuilder(TRADE_FLOW);

            for(String flow : tradeFlow) {
                sb.append(flow).append("%2C");
            }
            sb.replace(sb.length()-3, sb.length(), ""); // remove last comma (%2C)
            this.tradeFlow = sb.toString();
        } else {
            this.tradeFlow = null;
        }
    }

    public void setCustoms(String customs) {
        this.customs = CUSTOMS + customs;
    }

    public void setModeOfTransport(int[] modeOfTransport) {
        if(modeOfTransport != null) {
            StringBuilder sb = new StringBuilder(MODE_OF_TRANSPORT);

            for(int mode : modeOfTransport) {
                sb.append(mode).append("%2C");
            }
            sb.replace(sb.length()-3, sb.length(), ""); // remove last comma (%2C)
            this.modeOfTransport = sb.toString();
        } else {
            this.modeOfTransport = null;
        }
    }

    public void setIncludeDesc(Boolean includeDesc) {
        this.includeDesc = INCLUDE_DESC + includeDesc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Stream<String> stream() {
        List<String> list = new ArrayList<>() {{
            add(typeOfTrade);
            add(frequency);
            add(classification);
            add(reporter);
            add(period);
            add(partner);
            add(commodity);
            add(tradeFlow);
            add(customs);
            add(modeOfTransport);
            add(includeDesc);
        }};

        return list.stream();
    }
}
