package eu.russiantrade.database.readAndWrite;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.monthly_trade.PSQLTradeReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.naming.NamingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.stream.Stream;

public class TestDBRead {

    private static final String PATH_PROPERTIES = FileSystems.getDefault()
            .getPath("src", "main", "resources", "database", "testDatabase.properties")
            .toAbsolutePath().toString();
    private static final String PATH_PARAMETERS = FileSystems.getDefault()
            .getPath("src","test","resources","database","parameters")
            .toAbsolutePath().toString();
    private static DSCreator ds = new DSCreator(PATH_PROPERTIES);
    private static PSQLTradeReader reader = null;
    private static Exception exceptionBeforeAll = null;

    @BeforeAll
    public static void init() {
        try {
            reader = new PSQLTradeReader(ds.getDataSourceTradeDBJava());
        } catch (IOException | NamingException e) {
            exceptionBeforeAll = e;
        }
    }

    @ParameterizedTest
    @MethodSource("parametersCommodityMonth")
    void readCommodityMonth(List parameters, List<TradeData> expected) {
        if(reader != null) {
            List<TradeData> resultOneMonth = reader.getCommodityMonth(
                    (int) parameters.get(0),
                    (int) parameters.get(1),
                    (String) parameters.get(2),
                    (int) parameters.get(3),
                    (String) parameters.get(4));

            Assertions.assertEquals(expected.hashCode(), resultOneMonth.hashCode());
        } else {
            exceptionBeforeAll.printStackTrace();
            Assertions.fail();
        }
    }

    private static Stream<Arguments> parametersCommodityMonth() {
        Map<List,List<TradeData>> arguments = readParametersFromFile("readCommodityMonth.txt", "iisis");
        List<Arguments> argumentsToReturn = new ArrayList<>();

        arguments.forEach((parameters,expectedResult) -> argumentsToReturn.add(Arguments.of(
                parameters,
                expectedResult
        )));

        return argumentsToReturn.stream();
    }

    @ParameterizedTest
    @MethodSource("parametersTotalOfYears")
    void readTotalOfYears(List parameters, List<TradeData> expected) {
        if(reader != null) {
            List<TradeData> resultSeveralYears = reader.getTotalOfYears(
                    (int) parameters.get(0),
                    (int) parameters.get(1),
                    (String) parameters.get(2),
                    (int) parameters.get(3),
                    (int) parameters.get(4));

            Assertions.assertEquals(expected.hashCode(), resultSeveralYears.hashCode());
        } else {
            exceptionBeforeAll.printStackTrace();
            Assertions.fail();
        }
    }

    private static Stream<Arguments> parametersTotalOfYears() {
        Map<List,List<TradeData>> arguments = readParametersFromFile("readTotalOfYears.txt", "iisii");
        List<Arguments> argumentsToReturn = new ArrayList<>();

        arguments.forEach((parameters,expectedResult) -> argumentsToReturn.add(Arguments.of(
                parameters,
                expectedResult
        )));

        return argumentsToReturn.stream();
    }

    @ParameterizedTest
    @MethodSource("parametersSeveralMonth")
    void readSeveralMonth(List parameters, List<TradeData> expected) {
        if(reader != null) {
            List<TradeData> resultSeveralYears = reader.getCommodityYear(
                    (int) parameters.get(0),
                    (int) parameters.get(1),
                    (String) parameters.get(2),
                    (int) parameters.get(3),
                    (String) parameters.get(4));

            Assertions.assertEquals(expected.hashCode(), resultSeveralYears.hashCode());
        } else {
            exceptionBeforeAll.printStackTrace();
            Assertions.fail();
        }
    }

    private static Stream<Arguments> parametersSeveralMonth() {
        Map<List,List<TradeData>> arguments = readParametersFromFile("readSeveralMonth.txt","iisis");
        List<Arguments> argumentsToReturn = new ArrayList<>();

        arguments.forEach((parameters,expectedResult) -> argumentsToReturn.add(Arguments.of(
                parameters,
                expectedResult
        )));

        return argumentsToReturn.stream();
    }

    // readability could be better, but works flawless nevertheless
    private static Map<List,List<TradeData>> readParametersFromFile(String fileName, String intOrString) {
        Map<List,List<TradeData>> arguments = new HashMap<>();

        try {
            String path = PATH_PARAMETERS + FileSystems.getDefault().getSeparator() + fileName;
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            List parameters = new ArrayList();
            List<TradeData> expectedResults = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(data.equals("-")) {
                    arguments.put(parameters, expectedResults);
                    parameters = new ArrayList<>();
                    expectedResults = new ArrayList<>();
                } if(data.charAt(0) == '(') {
                    data = data.replace("(","").replace(")","");
                    String[] parameterArray = data.split(",");

                    for(int i = 0; i < parameterArray.length; i++) {
                        parameters.add(intOrString.charAt(i) == 'i' ? Integer.parseInt(parameterArray[i]) :
                                parameterArray[i]);
                    }
                } else {
                    data = data.replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                            .trim();
                    String[] tradeData = data.split(",");

                    // TradeData hashcode uses 6 variables -> only use lines with 6 parameters
                    if (tradeData.length == 6) {
                        expectedResults.add(new TradeData(
                                Integer.parseInt(tradeData[0]),
                                tradeData[1],
                                Integer.parseInt(tradeData[2]),
                                Integer.parseInt(tradeData[3]),
                                tradeData[4],
                                Integer.parseInt(tradeData[5])
                        ));
                    }
                }
            }

            // EOF
            arguments.put(parameters, expectedResults);
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arguments;
    }
}
