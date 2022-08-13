package eu.russiantrade.database;

import eu.russiantrade.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.TradeMapper;
import eu.russiantrade.database.readAndWrite.tradeData.PSQLTradeReader;

import java.nio.file.FileSystems;
import java.util.List;

public class DBTest {
    private static final String PATH = FileSystems.getDefault().getPath(
                    "backend", "src", "main", "resources", "database", "testDatabase.properties")
            .toAbsolutePath().toString();

    public static void main(String[] args) {
        DSCreator ds = new DSCreator(PATH);

        try {
            PSQLTradeReader reader = new PSQLTradeReader(ds.getDataSourceTradeDBJava());
            List<TradeData> resultOneMonth = reader.getCommodityMonth(8, 643, "Import", 201201, "AG2");
            String jsonOneMonth = TradeMapper.jsonCommodity(resultOneMonth);
            System.out.println(jsonOneMonth);
            System.out.println("------------------------------");

            List<TradeData> resultSeveralYears = reader.getTotalOfYears(8, 643, "Import", 2010, 2020);
            String jsonSeveralYears = TradeMapper.jsonTotal(resultSeveralYears);
            System.out.println(jsonSeveralYears);
            System.out.println("------------------------------");

            List<TradeData> resultSeveralMonth = reader.getCommodityYear(8, 643, "Import", 2012, "AG2");
            String jsonSeveralMonth = TradeMapper.jsonCommodity(resultSeveralMonth);
            System.out.println(jsonSeveralMonth);
            System.out.println("------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
