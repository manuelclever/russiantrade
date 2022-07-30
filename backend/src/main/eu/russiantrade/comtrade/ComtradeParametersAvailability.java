package russiantrade.comtrade;

public class ComtradeParametersAvailability extends ComtradeParameters {

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
     */
    public ComtradeParametersAvailability(char tradeDataType, char frequency, int reporter, long period,
                                          String classification, String token) {
        super(tradeDataType, frequency, reporter, period, classification, token,
                -1, null, null, null, -1, Character.MIN_VALUE, -1);
    }

    /**
     * Set parameters for API call.
     *
     * last updated: 2022/02
     * @param frequency A - Annual, M - Monthly (default A)
     * @param reporter 0 - All, country code (default is 0)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     */
    public ComtradeParametersAvailability(char frequency, int reporter, long period) {
        super(Character.MIN_VALUE, frequency, reporter, period, null, null,
                -1, null, null, null, -1, Character.MIN_VALUE, -1);
    }
}
