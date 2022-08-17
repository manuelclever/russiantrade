package eu.russiantrade.servlets;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.TradeMapper;
import eu.russiantrade.database.querydesignations.DataDesignations;
import eu.russiantrade.database.readAndWrite.monthly_trade.PSQLTradeReader;
import eu.russiantrade.util.DigitCount;

import javax.sql.DataSource;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;

public class Test {
    private static final String DB_PROPERTIES = FileSystems.getDefault().getPath(
            "src", "main", "resources", "database", "database.properties")
            .toAbsolutePath().toString();

    public static void main(String[] args) {
        // Set the response message's MIME type
        // Allocate a System.output writer to write the response message into the network socket

        System.out.println("<p>starting</p>");
        try {
            int country = 8;
            String tradeFlow = "Export";
            int periodStart = 2010;
            int periodEnd = 2020;

            System.out.println("country: " + country);
            System.out.println("tradeFlow: " + tradeFlow);
            System.out.println("periodStart: " + periodStart);
            System.out.println("periodEnd: " + periodEnd);

            DSCreator dsC = new DSCreator(DB_PROPERTIES);
//            InitialContext ctx = new InitialContext();
//            DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/DefaultDB");
//            PSQLTradeReader dr = new PSQLTradeReader(dataSource);
            DataSource ds = dsC.getDataSourceTradeDBJava();
            System.out.println("<p>Datasource " + ds.toString() + "</p>");
            PSQLTradeReader dr = new PSQLTradeReader(ds);

            List<TradeData> tradeData = null;
            if (DigitCount.count(periodStart) == 4 && DigitCount.count(periodEnd) == 4) {
                System.out.println("<p>getting trade data</p>");
                tradeData = dr.getTotalOfYears(country, DataDesignations.RUSSIA, tradeFlow, periodStart, periodEnd);
            }

            if (tradeData != null) {
                String json = TradeMapper.jsonTotal(tradeData);
                System.out.println("<p>" + country + ", " + tradeFlow + ", " + periodStart + ", " + periodEnd + "</p>");

                System.out.println(json);
            }
        } catch (Exception e) {
            System.out.println("<p>Error!!!</p>");
            System.out.println("<p>" + e.getMessage() + "</p>");
            System.out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");

        } finally {
            System.out.close();
        }
    }
}
