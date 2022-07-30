package russiantrade.database;

import com.google.gson.JsonObject;
import russiantrade.comtrade.parser.TradeData;
import russiantrade.database.datasource.DSCreator;
import russiantrade.database.readAndWrite.TradeMapper;
import russiantrade.database.readAndWrite.tradeData.PSQLTradeReader;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class simpeTest {
    private static final Path PATH = FileSystems.getDefault().getPath("backend", "src", "test", "",
            "database", "testDatabase.properties");

    public static void main(String[] args) {
        DSCreator ds = new DSCreator(PATH);

        JsonObject myJsonOj = new JsonObject();


        PSQLTradeReader reader = new PSQLTradeReader(ds.getDataSourceTradeDB());
        List<TradeData> resultOneMonth = reader.getCommodityMonth(8, 643, "Import", 201201, "AG2");
        String jsonOneMonth = TradeMapper.jsonCommodity(resultOneMonth);
        System.out.println(jsonOneMonth);

//        System.out.println(PSQLQTradeData.SELECT_DATASET +
//                PSQLQTradeData.JOIN_COUNTRY_REPORTER +
//                PSQLQTradeData.JOIN_COUNTRY_PARTNER);

        List<TradeData> resultSeveralYears = reader.getTotalOfYears(8, 643, "Import", 2010, 2020);
        String jsonSeveralYears = TradeMapper.jsonTotal(resultSeveralYears);
        System.out.println(jsonSeveralYears);

        List<TradeData> resultSeveralMonth = reader.getCommodityYear(8, 643, "Import", 2012, "AG2");
        String jsonSeveralMonth = TradeMapper.jsonCommodity(resultSeveralMonth);
        System.out.println(jsonSeveralMonth);
    }
}
