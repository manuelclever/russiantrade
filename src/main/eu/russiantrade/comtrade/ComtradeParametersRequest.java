package eu.russiantrade.comtrade;

public class ComtradeParametersRequest extends ComtradeParameters {

    /**
     * Set parameters for API call.
     *
     * Last updated: 2022/02
     * @param tradeDataType C - Commodities, S - Services (default C)
     * @param frequency A - Annual, M - Monthly (default A)
     * @param reporter 0 - All, country code (default is 0)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param classification HS - harmonized System, for more info seek documentation for UN Comtrade API (default HS for goods and EB02 for services)
     * @param token individual token if abonnement
     *
     * @param partner 0 - All, country code (default is 0)
     * @param classificationCode TOTAL - total Trade, All - all Codes, AG[digit] - detailed code at specified digit (1-6, depends on classification) (default AG2)
     * @param outputFormat json, cvs (default json)
     * @param max maximum records returned (default 500, free tier caps 10 000)
     * @param head heading style (default H)
     * @param imts 2010 - data that complies with IMTS 2010, orig - data that complies with earlier versions (default 2010)
     *
     */
    public ComtradeParametersRequest(char tradeDataType, char frequency, int reporter, long period,
                                     String classification, String token, int partner,
                                     String[] classificationCode, String outputFormat, int max, char head, int imts) {
        super(tradeDataType, frequency, reporter, period, classification, token, partner, new int[]{1,2},
                classificationCode, outputFormat, max, head, imts);
    }

    /**
     * Set parameters for API call.
     *
     * Last updated: 2022/02
     * @param frequency A - Annual, M - Monthly (default A)
     * @param reporter 0 - All, country code (default is 0)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     *
     * @param partner 0 - All, country code (default is 0)
     * @param classificationCode TOTAL - total Trade, All - all Codes, AG[digit] - detailed code at specified digit (1-6, depends on classification) (default AG2)
     *
     */
    public ComtradeParametersRequest(char frequency, int reporter, long period, int partner,
                                     String[] classificationCode) {
        super(Character.MIN_VALUE, frequency, reporter, period, null, null, partner, new int[]{1,2},
                classificationCode, null, -1, Character.MIN_VALUE, -1);
    }
}
