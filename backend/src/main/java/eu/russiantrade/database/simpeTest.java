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
        List<TradeData> result = reader.getDatasets(8, 643, "Imports", 201201);
        String json = TradeMapper.jsonTradeDataMonth(result);
        System.out.println(json);
    }
}
