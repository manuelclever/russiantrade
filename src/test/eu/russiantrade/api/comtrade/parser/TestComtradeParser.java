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
        Assertions.assertEquals(response, expected);
    }

    private static Stream<Arguments> comtradeResponse() {
        List<TradeData> argument2 = new ArrayList<>();
        argument2.add(new TradeData(
                "H3",
                2010,
                2010,
                2010,
                2,
                0,
                2,
                "Export",
                8,
                "Albania",
                "ALB",
                643,
                "Russian Federation",
                "RUS",
                0,
                "","",
                "07",
                "Edible vegetables and certain roots and tubers",
                1,
                "No Quantity",
                0,"",0,0,
                26170,
                0,0,
                0));
        argument2.add(new TradeData(
                "H3",
                2010,
                2010,
                2010,
                2,
                0,
                1,
                "Import",
                8,
                "Albania",
                "ALB",
                643,
                "Russian Federation",
                "RUS",
                0,
                "","",
                "08",
                "Edible fruit and nuts; peel of citrus fruit or melons",
                1,
                "No Quantity",
                0,"",0,0,
                226,
                0,0,
                0));

        return Stream.of(
                Arguments.of("{\"validation\":{" +
                                "\"status\":{" +
                                    "\"name\":\"Ok\"," +
                                    "\"value\":0," +
                                    "\"category\":0," +
                                    "\"description\":\"\"," +
                                    "\"helpUrl\":\"For more reference visit http://comtrade.un.org/data/dev/portal/\"}," +
                                    "\"message\":null," +
                                "\"count\":{" +
                                    "\"value\":0," +
                                    "\"started\":\"0001-01-01T00:00:00\"," +
                                    "\"finished\":\"0001-01-01T00:00:00\"," +
                                    "\"durationSeconds\":0.0}," +
                                "\"datasetTimer\":{" +
                                    "\"started\":\"2022-08-14T12:40:57.3776756+02:00\"," +
                                    "\"finished\":\"2022-08-14T12:40:57.4557842+02:00\"," +
                                    "\"durationSeconds\":0.0781086}}," +
                                "\"dataset\":[]}",
                        new ComtradeResponse(
                                new Validation(
                                        "Ok",
                                        0,
                                        0,
                                        "",
                                        "For more reference visit http://comtrade.un.org/data/dev/portal/",
                                        null,
                                        0,
                                        "0001-01-01T00:00:00",
                                        "0001-01-01T00:00:00",
                                        0.0,
                                        "2022-08-14T12:40:57.3776756+02:00",
                                        "2022-08-14T12:40:57.4557842+02:00",
                                        0.0781086),
                                null)),
                Arguments.of("{\"validation\":{" +
                                "\"status\":{" +
                                    "\"name\":\"Ok\"," +
                                    "\"value\":0," +
                                    "\"category\":0," +
                                    "\"description\":\"\"," +
                                    "\"helpUrl\":\"For more reference visit http://comtrade.un.org/data/dev/portal/\"}," +
                                    "\"message\":null," +
                                "\"count\":{\"value\":41," +
                                    "\"started\":\"2022-08-14T13:23:28.202793+02:00\"," +
                                    "\"finished\":\"2022-08-14T13:23:28.374632+02:00\"," +
                                    "\"durationSeconds\":0.171839}," +
                                "\"datasetTimer\":{" +
                                    "\"started\":\"2022-08-14T13:23:28.202793+02:00\"," +
                                    "\"finished\":\"2022-08-14T13:23:28.5670659+02:00\"," +
                                    "\"durationSeconds\":0.3642729}}," +
                                "\"dataset\":[" +
                                    "{\"pfCode\":\"H3\",\"yr\":2010,\"period\":2010,\"periodDesc\":\"2010\",\"aggrLevel\":2,\"IsLeaf\":0,\"rgCode\":2,\"rgDesc\":\"Export\",\"rtCode\":8,\"rtTitle\":\"Albania\",\"rt3ISO\":\"ALB\",\"ptCode\":643,\"ptTitle\":\"Russian Federation\",\"pt3ISO\":\"RUS\",\"ptCode2\":null,\"ptTitle2\":\"\",\"pt3ISO2\":\"\",\"cstCode\":\"\",\"cstDesc\":\"\",\"motCode\":\"\",\"motDesc\":\"\",\"cmdCode\":\"07\",\"cmdDescE\":\"Edible vegetables and certain roots and tubers\",\"qtCode\":1,\"qtDesc\":\"No Quantity\",\"qtAltCode\":null,\"qtAltDesc\":\"\",\"TradeQuantity\":null,\"AltQuantity\":null,\"NetWeight\":null,\"GrossWeight\":null,\"TradeValue\":26170,\"CIFValue\":null,\"FOBValue\":null,\"estCode\":0}," +
                                    "{\"pfCode\":\"H3\",\"yr\":2010,\"period\":2010,\"periodDesc\":\"2010\",\"aggrLevel\":2,\"IsLeaf\":0,\"rgCode\":1,\"rgDesc\":\"Import\",\"rtCode\":8,\"rtTitle\":\"Albania\",\"rt3ISO\":\"ALB\",\"ptCode\":643,\"ptTitle\":\"Russian Federation\",\"pt3ISO\":\"RUS\",\"ptCode2\":null,\"ptTitle2\":\"\",\"pt3ISO2\":\"\",\"cstCode\":\"\",\"cstDesc\":\"\",\"motCode\":\"\",\"motDesc\":\"\",\"cmdCode\":\"08\",\"cmdDescE\":\"Edible fruit and nuts; peel of citrus fruit or melons\",\"qtCode\":1,\"qtDesc\":\"No Quantity\",\"qtAltCode\":null,\"qtAltDesc\":\"\",\"TradeQuantity\":null,\"AltQuantity\":null,\"NetWeight\":null,\"GrossWeight\":null,\"TradeValue\":226,\"CIFValue\":null,\"FOBValue\":null,\"estCode\":0}]}",
                        new ComtradeResponse(
                                new Validation(
                                        "Ok",
                                        0,
                                        0,
                                        "",
                                        "For more reference visit http://comtrade.un.org/data/dev/portal/",
                                        null,
                                        41,
                                        "2022-08-14T13:23:28.202793+02:00",
                                        "2022-08-14T13:23:28.374632+02:00",
                                        0.171839,
                                        "2022-08-14T13:23:28.202793+02:00",
                                        "2022-08-14T13:23:28.5670659+02:00",
                                        0.3642729),
                                argument2)
        ));
    }
}
