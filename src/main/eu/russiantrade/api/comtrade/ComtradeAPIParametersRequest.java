package eu.russiantrade.api.comtrade;

public class ComtradeAPIParametersRequest extends ComtradeAPIParameters {

    /**
     * Set parameters for API call.
     * <p>
     * Last updated: 2023/09
     * @param typeOfTrade C - Commodities, S - Services (default C)
     * @param frequency A - Annual, M - Monthly (default A)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param partner2 Partner2 code (Possible values are M49 code of the countries, 0 for All)
     * @param commodity Commodity code. Multi value input should be in the form of csv (Codes separated by comma (,))
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param modeOfTransport -1 - All
     * @param token subscription token
     */

    public ComtradeAPIParametersRequest(char typeOfTrade,
                                        char frequency,
                                        long period,
                                        short partner,
                                        short partner2,
                                        String[] commodity,
                                        String[] tradeFlow,
                                        int[] modeOfTransport,
                                        Boolean includeDesc,
                                        String token) {
        super(
                typeOfTrade,
                frequency,
                "HS",
                (short) 276,
                period,
                partner,
                partner2,
                commodity,
                tradeFlow,
                "C00",
                modeOfTransport,
                includeDesc,
                token);
    }

    /**
     * Set parameters for API call. Total of commodities are requested.
     * <p>
     * Last updated: 2023/09
     * @param frequency A - Annual, M - Monthly (default A)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param partner2 Partner code2 (Possible values are M49 code of the countries, 0 for All)
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param token subscription token
     */

    public static ComtradeAPIParametersRequest totalCommodities(
                                        char frequency,
                                        long period,
                                        short partner,
                                        short partner2,
                                        String[] tradeFlow,
                                        String token) {
        return new ComtradeAPIParametersRequest(
                'C',
                frequency,
                period,
                partner,
                partner2,
                new String[]{"TOTAL"},
                tradeFlow,
                new int[]{0},
                true,
                token);
    }

    /**
     * Set parameters for API call. All the commodities are requested.
     * <p>
     * Last updated: 2023/09
     * @param frequency A - Annual, M - Monthly (default A)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param partner2 Partner code2 (Possible values are M49 code of the countries, 0 for All)
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param token subscription token
     */

    public static ComtradeAPIParametersRequest allCommodities(
                                        char frequency,
                                        long period,
                                        short partner,
                                        short partner2,
                                        String[] tradeFlow,
                                        String token) {
        return new ComtradeAPIParametersRequest(
                'C',
                frequency,
                period,
                partner,
                partner2,
                new String[]{"ALL"},
                tradeFlow,
                new int[]{0},
                true,
                token);
    }

    /**
     * Set parameters for API call. Commodities can be chosen.
     * <p>
     * Last updated: 2023/09
     * @param frequency A - Annual, M - Monthly (default A)
     * @param period now - most recent (year or month), YYYY - if Annual or Monthly, YYYYMM - if Monthly (default now)
     * @param partner Partner code (Possible values are M49 code of the countries, 0 for All)
     * @param partner2 Partner code2 (Possible values are M49 code of the countries, 0 for All)
     * @param tradeFlow -1 - All, M - Import, X - Export
     * @param token subscription token
     */

    public static ComtradeAPIParametersRequest customCommodities(
                                        char frequency,
                                        long period,
                                        short partner,
                                        short partner2,
                                        String[] commodities,
                                        String[] tradeFlow,
                                        String token) {
        return new ComtradeAPIParametersRequest(
                'C',
                frequency,
                period,
                partner,
                partner2,
                commodities,
                tradeFlow,
                new int[]{0},
                true,
                token);
    }
}
