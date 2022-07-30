package russiantrade.servlets;

import russiantrade.comtrade.parser.TradeData;
import russiantrade.database.datasource.DSCreator;
import russiantrade.database.querydesignations.DataDesignations;
import russiantrade.database.readAndWrite.TradeMapper;
import russiantrade.database.readAndWrite.tradeData.PSQLTradeReader;
import russiantrade.util.DigitCount;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ServletTotal extends HttpServlet {
    private static final Path DB_PROPERTIES = FileSystems.getDefault().getPath(
            "backend", "src", "main", "", "database", "testDatabase.properties");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<p>starting</p>");
        try {
            int country = Integer.parseInt(req.getParameter("country"));
            String tradeFlow = req.getParameter("trade_flow");
            int periodStart = Integer.parseInt(req.getParameter("periodStart"));
            int periodEnd = Integer.parseInt(req.getParameter("periodEnd"));

            out.println("<p>country: " + country + "</p>");
            out.println("<p>tradeFlow: " + tradeFlow + "</p>");
            out.println("<p>periodStart: " + periodStart + "</p>");
            out.println("<p>periodEnd: " + periodEnd + "</p>");

            DSCreator dsC = new DSCreator(DB_PROPERTIES);
//            InitialContext ctx = new InitialContext();
//            DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/DefaultDB");
//            PSQLTradeReader dr = new PSQLTradeReader(dataSource);
            DataSource ds = dsC.getDataSourceTradeDB();
            System.out.println("<p>Datasource " + ds.toString() + "</p>");
            PSQLTradeReader dr = new PSQLTradeReader(ds);

            List<TradeData> tradeData = null;
            if (DigitCount.count(periodStart) == 4 && DigitCount.count(periodEnd) == 4) {
                out.println("<p>getting trade data</p>");
                tradeData = dr.getTotalOfYears(country, DataDesignations.RUSSIA, tradeFlow, periodStart, periodEnd);
            }

            if (tradeData != null) {
                String json = TradeMapper.jsonTotal(tradeData);
                out.println("<p>" + country + ", " + tradeFlow + ", " + periodStart + ", " + periodEnd + "</p>");

                out.write(json);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.println("<p>This is a paragraph</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error!!!</p>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");

        } finally {
            out.close();
        }
    }
}
