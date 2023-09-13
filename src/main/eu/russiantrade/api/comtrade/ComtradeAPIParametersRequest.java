package eu.russiantrade.api.comtrade;

public class ComtradeAPIParametersRequest extends ComtradeAPIParameters {

    /**
     * Set parameters for API call.
     * <p>
     * Last updated: 2023/09
     * @param typeOfTrade C - Commodities, S - Services (default C)
     * @param frequency A - Annual, M - Monthly (default A)
     * @param reporter Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param commodity Commodity code. Multi value input should be in the form of csv (Codes separated by comma (,))
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param modeOfTransport -1 - All
     * @param token subscription token
     */

    public ComtradeAPIParametersRequest(char typeOfTrade,
                                        char frequency,
                                        short reporter,
                                        long period,
                                        short partner,
                                        String[] commodity,
                                        String[] tradeFlow,
                                        int[] modeOfTransport,
                                        Boolean includeDesc,
                                        String token) {
        super(
                typeOfTrade,
                frequency,
                "S4",
                reporter,
                period,
                partner,
                commodity,
                tradeFlow,
                "-1",
                modeOfTransport,
                includeDesc,
                token);
    }

    /**
     * Set parameters for API call.
     * <p>
     * Last updated: 2023/09
     * @param frequency A - Annual, M - Monthly (default A)
     * @param reporter Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param commodity Commodity code. Multi value input should be in the form of csv (Codes separated by comma (,))
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param includeDesc Boolean
     * @param token subscription token
     */
    public ComtradeAPIParametersRequest(char frequency,
                                        short reporter,
                                        long period,
                                        short partner,
                                        String[] commodity,
                                        String[] tradeFlow,
                                        Boolean includeDesc,
                                        String token) {
        super(
                'C',
                frequency,
                "S4",
                reporter,
                period,
                partner,
                commodity,
                tradeFlow,
                "-1",
                new int[]{-1},
                includeDesc,
                token);
    }
}
