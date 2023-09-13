package eu.russiantrade.api.comtrade;

public class ComtradeAPIParametersAvailability extends ComtradeAPIParameters {

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

    public ComtradeAPIParametersAvailability(char typeOfTrade,
                                             char frequency,
                                             String classification,
                                             short reporter,
                                             long period,
                                             String token) {
        super(
                typeOfTrade,
                frequency,
                classification,
                reporter,
                period,
                (short) -1,
                null,
                new String[]{"M","X"},
                "-1",
                -1,
                false,
                token);
    }

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

    public ComtradeAPIParametersAvailability(char frequency,
                                             short reporter,
                                             long period,
                                             String token) {
        super(
                'C',
                frequency,
                "S4",
                reporter,
                period,
                (short) -1,
                null,
                new String[]{"M","X"},
                "-1",
                -1,
                false,
                token);
    }
}
