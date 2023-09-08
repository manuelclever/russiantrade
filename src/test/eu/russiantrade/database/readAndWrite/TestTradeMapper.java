package eu.russiantrade.database.readAndWrite;

import eu.russiantrade.api.comtrade.parser.TradeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.stream.Stream;

public class TestTradeMapper {
    private static final String PATH_PARAMETERS = FileSystems.getDefault()
            .getPath("src","test","resources","database","parameters")
            .toAbsolutePath().toString();

    @ParameterizedTest
    @MethodSource("parametersJsonCommodity")
    void mapJsonCommodity(List<TradeData> parameters, String expected) {
        Assertions.assertEquals(expected, TradeMapper.jsonCommodity(parameters));
    }

    public static Stream<Arguments> parametersJsonCommodity() {
        Map<List<TradeData>,String> arguments = readParametersFromFile("jsonCommodity.txt");
        List<Arguments> argumentsToReturn = new ArrayList<>();

        arguments.forEach((parameters,expectedResult) -> argumentsToReturn.add(Arguments.of(
                parameters,
                expectedResult
        )));

        return argumentsToReturn.stream();
    }

    @ParameterizedTest
    @MethodSource("parametersJsonTotal")
    void mapJsonTotal(List<TradeData> parameters, String expected) {
        Assertions.assertEquals(expected, TradeMapper.jsonTotal(parameters));
    }

    public static Stream<Arguments> parametersJsonTotal() {
        Map<List<TradeData>,String> arguments = readParametersFromFile("jsonTotal.txt");
        List<Arguments> argumentsToReturn = new ArrayList<>();

        arguments.forEach((parameters,expectedResult) -> argumentsToReturn.add(Arguments.of(
                parameters,
                expectedResult
        )));

        return argumentsToReturn.stream();
    }

    // readability could be better, but works flawless nevertheless
    private static Map<List<TradeData>,String> readParametersFromFile(String fileName) {
        Map<List<TradeData>,String> arguments = new HashMap<>();

        try {
            String path = PATH_PARAMETERS + FileSystems.getDefault().getSeparator() + fileName;
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            List<TradeData> parameters = new ArrayList();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(data.charAt(0) == '{') {
                    arguments.put(parameters, data);

                    parameters = new ArrayList<>();
                } else if(data.charAt(0) == '[') {
                    data = data.replace("[", "")
                            .replace("]", "")
                            .trim();
                    String[] tradeData = data.split(":");

                    // TradeData hashcode uses 6 variables -> only use lines with 6 parameters
                    // TradeData in json for webpage needs between 6 and 7 variables
                    if (tradeData.length == 6) {
                        parameters.add(new TradeData(
                                Integer.parseInt(tradeData[0]),
                                tradeData[1],
                                Integer.parseInt(tradeData[2]),
                                Integer.parseInt(tradeData[3]),
                                tradeData[4],
                                Integer.parseInt(tradeData[5])
                        ));
                    } else if (tradeData.length == 7) {
                        parameters.add(new TradeData(
                                Integer.parseInt(tradeData[0]),
                                tradeData[1],
                                Integer.parseInt(tradeData[2]),
                                Integer.parseInt(tradeData[3]),
                                tradeData[4],
                                tradeData[5],
                                Integer.parseInt(tradeData[6])
                        ));
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arguments;
    }
}
