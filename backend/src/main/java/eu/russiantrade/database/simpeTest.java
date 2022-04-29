package eu.russiantrade.database;

import com.google.gson.JsonObject;
import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.TradeMapper;
import eu.russiantrade.database.readAndWrite.tradeData.PSQLTradeReader;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class simpeTest {
    private static final Path PATH = FileSystems.getDefault().getPath("backend", "src", "test", "resources",
            "database", "testDatabase.properties");

    public static void main(String[] args) {
        DSCreator ds = new DSCreator(PATH);

        JsonObject myJsonOj = new JsonObject();

        PSQLTradeReader reader = new PSQLTradeReader(ds.getDataSourceTradeDB());
        List<TradeData> resultOneMonth = reader.getDatasets(8, 643, "Imports", 201201);
        String jsonOneMonth = TradeMapper.jsonTradeDataOneTimePeriod(resultOneMonth);
        System.out.println(jsonOneMonth);

        List<TradeData> resultOneYear = reader.getDatasets(8, 643, "Imports", 2012);
        String jsonOneYear = TradeMapper.jsonTradeDataOneTimePeriod(resultOneYear);
        System.out.println(jsonOneYear);

        List<TradeData> resultSeveralMonth = reader.getDatasets(8, 643, "Imports", 2012);
        String jsonSeveralMonth = TradeMapper.jsonTradeDataMonth(resultSeveralMonth);
        System.out.println(jsonSeveralMonth);

        List<TradeData> resultSeveralYears = reader.getDatasets(8, 643, "Imports", 2012);
        String jsonSeveralYears = TradeMapper.jsonTradeDataYears(resultSeveralYears);
        System.out.println(jsonSeveralYears);
    }
}
