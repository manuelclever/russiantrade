package eu.russiantrade.api.comtrade.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestComtradeParser {

    @ParameterizedTest
    @MethodSource("comtradeResponse")
    void deserialize(String input, ComtradeResponse expected) {
        ComtradeResponse response = ComtradeParser.parseResponse(input);
        Assertions.assertEquals(expected, response);
    }

    private static Stream<Arguments> comtradeResponse() {
        List<TradeData> argument2 = new ArrayList<>();
        argument2.add(new TradeData(
                'C',
               'A',

                20100101,
                (short) 2010,
                (short) 52,
                (short) 2010,

                "M",
                "Import",

                8,
                "Albania",
                "Alb",

                643,
                "Russian Federation",
                "RUS",

                0,
                "W00",
                "World",

                "S4",
                "S4",
                false,

                "TOTAL",
                "All Commodities",

                0,
                false,

                "C00",
                "TOTAL CPC",

                0,

                0,
                "TOTAL MOT",

                -1,
                "N/A",
                0,
                false,

                -1,
                "N/A",
                0,
                false,

                0,
                false,

                0,
                false,

                101647050,
                0,
                101647050,

                0,
                true,
                false));
        argument2.add(new TradeData(
                'C',
                'A',

                20100101,
                (short) 2010,
                (short) 52,
                (short) 2010,

                "X",
                "Export",

                8,
                "Albania",
                "Alb",

                643,
                "Russian Federation",
                "RUS",

                0,
                "W00",
                "World",

                "S4",
                "S4",
                false,

                "TOTAL",
                "All Commodities",

                0,
                false,

                "C00",
                "TOTAL CPC",

                0,

                0,
                "TOTAL MOT",

                -1,
                "N/A",
                0,
                false,

                -1,
                "N/A",
                0,
                false,

                0,
                false,

                0,
                false,

                34372,
                0,
                34372,

                0,
                true,
                false));

        return Stream.of(
                Arguments.of("{" +
                        "\"elapsedTime\": \"2.63 secs\"," +
                        "\"count\": 2," +
                        "\"data\": [" +
                        "    {" +
                        "        \"typeCode\": \"C\"," +
                        "        \"freqCode\": \"A\"," +
                        "        \"refPeriodId\": 20100101," +
                        "        \"refYear\": 2010," +
                        "        \"refMonth\": 52," +
                        "        \"period\": \"2010\"," +
                        "        \"reporterCode\": 8," +
                        "        \"reporterISO\": \"ALB\"," +
                        "        \"reporterDesc\": \"Albania\"," +
                        "        \"flowCode\": \"M\"," +
                        "        \"flowDesc\": \"Import\"," +
                        "        \"partnerCode\": 643," +
                        "        \"partnerISO\": \"RUS\"," +
                        "        \"partnerDesc\": \"Russian Federation\"," +
                        "        \"partner2Code\": 0," +
                        "        \"partner2ISO\": \"W00\"," +
                        "        \"partner2Desc\": \"World\"," +
                        "        \"classificationCode\": \"S4\"," +
                        "        \"classificationSearchCode\": \"S4\"," +
                        "        \"isOriginalClassification\": false," +
                        "        \"cmdCode\": \"TOTAL\"," +
                        "        \"cmdDesc\": \"All Commodities\"," +
                        "        \"aggrLevel\": 0," +
                        "        \"isLeaf\": false," +
                        "        \"customsCode\": \"C00\"," +
                        "        \"customsDesc\": \"TOTAL CPC\"," +
                        "        \"mosCode\": \"0\"," +
                        "        \"motCode\": 0," +
                        "        \"motDesc\": \"TOTAL MOT\"," +
                        "        \"qtyUnitCode\": -1," +
                        "        \"qtyUnitAbbr\": \"N/A\"," +
                        "        \"qty\": null," +
                        "        \"isQtyEstimated\": false," +
                        "        \"altQtyUnitCode\": -1," +
                        "        \"altQtyUnitAbbr\": \"N/A\"," +
                        "        \"altQty\": null," +
                        "        \"isAltQtyEstimated\": false," +
                        "        \"netWgt\": null," +
                        "        \"isNetWgtEstimated\": false," +
                        "        \"grossWgt\": null," +
                        "        \"isGrossWgtEstimated\": false," +
                        "        \"cifvalue\": 101647050," +
                        "        \"fobvalue\": null," +
                        "        \"primaryValue\": 101647050," +
                        "        \"legacyEstimationFlag\": 0," +
                        "        \"isReported\": true," +
                        "        \"isAggregate\": false" +
                        "    }," +
                        "    {" +
                        "        \"typeCode\": \"C\"," +
                        "        \"freqCode\": \"A\"," +
                        "        \"refPeriodId\": 20100101," +
                        "        \"refYear\": 2010," +
                        "        \"refMonth\": 52," +
                        "        \"period\": \"2010\"," +
                        "        \"reporterCode\": 8," +
                        "        \"reporterISO\": \"ALB\"," +
                        "        \"reporterDesc\": \"Albania\"," +
                        "        \"flowCode\": \"X\"," +
                        "        \"flowDesc\": \"Export\"," +
                        "        \"partnerCode\": 643," +
                        "        \"partnerISO\": \"RUS\"," +
                        "        \"partnerDesc\": \"Russian Federation\"," +
                        "        \"partner2Code\": 0," +
                        "        \"partner2ISO\": \"W00\"," +
                        "        \"partner2Desc\": \"World\"," +
                        "        \"classificationCode\": \"S4\"," +
                        "        \"classificationSearchCode\": \"S4\"," +
                        "        \"isOriginalClassification\": false," +
                        "        \"cmdCode\": \"TOTAL\"," +
                        "        \"cmdDesc\": \"All Commodities\"," +
                        "        \"aggrLevel\": 0," +
                        "        \"isLeaf\": false," +
                        "        \"customsCode\": \"C00\"," +
                        "        \"customsDesc\": \"TOTAL CPC\"," +
                        "        \"mosCode\": \"0\"," +
                        "        \"motCode\": 0," +
                        "        \"motDesc\": \"TOTAL MOT\"," +
                        "        \"qtyUnitCode\": -1," +
                        "        \"qtyUnitAbbr\": \"N/A\"," +
                        "        \"qty\": null," +
                        "        \"isQtyEstimated\": false," +
                        "        \"altQtyUnitCode\": -1," +
                        "        \"altQtyUnitAbbr\": \"N/A\"," +
                        "        \"altQty\": null," +
                        "        \"isAltQtyEstimated\": false," +
                        "        \"netWgt\": null," +
                        "        \"isNetWgtEstimated\": false," +
                        "        \"grossWgt\": null," +
                        "        \"isGrossWgtEstimated\": false," +
                        "        \"cifvalue\": null," +
                        "        \"fobvalue\": 34372," +
                        "        \"primaryValue\": 34372," +
                        "        \"legacyEstimationFlag\": 0," +
                        "        \"isReported\": true," +
                        "        \"isAggregate\": false" +
                        "    }" +
                        "  ]," +
                        "  \"error\": \"\"" +
                        "}",
                        new ComtradeResponse(
                                null,
                                2,
                                argument2,
                                null))
                );
    }
}
