package eu.russiantrade.api.comtrade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class ComtradeAPIParameters {
    private static final String REPORTER = "reporterCode=";
    private static final String PERIOD = "period="; // Years or Months, depends on chosen frequency
    private static final String PARTNER = "partnerCode=";
    private static final String PARTNER2 = "partner2Code=";
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
    private String partner2;
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
     * @param classification Harmonized System is used (HS)
     * @param reporter Code of country reporting the data (Possible values are M49 code of the countries, 0 for All)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param partner2 Partner2 code (Possible values are M49 code of the countries, 0 for All)
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
                                 short partner2,
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
        setPartner2(partner2);
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
                                 short partner2,
                                 String[] commodity,
                                 String[] tradeFlow,
                                 Boolean includeDesc,
                                 String token) {
        setFrequency(frequency);
        setReporter(reporter);
        setPeriod(period);
        setPartner(partner);
        setPartner2(partner2);
        setCommodity(commodity);
        setTradeFlow(tradeFlow);
        setIncludeDesc(includeDesc);
        setToken(token);
    }

    public void setTypeOfTrade(char typeOfTrade) {
        if (typeOfTrade == 'C' | typeOfTrade == 'S' ) {
            this.typeOfTrade = String.valueOf(typeOfTrade);
        } else {
            this.typeOfTrade = null;
        }
    }

    public void setFrequency(char frequency) {
        if (frequency == 'A' | frequency == 'M' ) {
            this.frequency = String.valueOf(frequency);
        } else {
            this.typeOfTrade = null;
        }
    }

    public void setClassification(String classification) {
        this.classification = classification;
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

    public void setPartner2(short partner) {
        this.partner2 = PARTNER2 + partner;
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
        if(customs != null) {
            this.customs = CUSTOMS + customs;
        } else {
            this.customs = null;
        }
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

    public Stream<String> streamDataFormat() throws NullPointerException {
        if(typeOfTrade == null || frequency == null || classification == null)  {
            throw new NullPointerException("typeOfTrade, frequency and/or classification is null, but these are " +
                    "prerequisites for the data format.");
        }
        List<String> list = new ArrayList<>() {{
            add(typeOfTrade);
            add(frequency);
            add(classification);
        }};

        return list.stream();

    }

    public Stream<String> streamSearchParameters() throws NullPointerException {
        if(reporter == null || period == null || partner == null)  {
            throw new NullPointerException("reporter, period and/or partner is null, but these are " +
                    "prerequisite search criteria.");
        }
        List<String> list = new ArrayList<>() {{
            add(reporter);
            add(period);
            add(partner);
            add(partner2);
            add(commodity);
            add(tradeFlow);
            add(customs);
            add(modeOfTransport);
            add(includeDesc);
        }};

        return list.stream();
    }
}
