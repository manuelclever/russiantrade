package eu.russiantrade.servlets;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.querydesignations.DataDesignations;
import eu.russiantrade.database.readAndWrite.TradeMapper;
import eu.russiantrade.database.readAndWrite.tradeData.PSQLTradeReader;
import eu.russiantrade.util.DigitCount;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class ServletTotal extends HttpServlet {
    private static final Path DB_PROPERTIES = FileSystems.getDefault().getPath(
            "src", "test", "resources", "backend/src/main/resources/database", "testDatabase.properties");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket

        try (PrintWriter out = resp.getWriter()) {
            int country = Integer.parseInt(req.getParameter("country"));
            String tradeFlow = req.getParameter("trade_flow");
            int periodStart = Integer.parseInt(req.getParameter("periodStart"));
            int periodEnd = Integer.parseInt(req.getParameter("periodEnd"));

            DSCreator dsC = new DSCreator(DB_PROPERTIES);
            PSQLTradeReader dr = new PSQLTradeReader(dsC.getDataSourceTradeDB());

            List<TradeData> tradeData = null;
            if (DigitCount.count(periodStart) == 4 && DigitCount.count(periodEnd) == 4) {
                tradeData = dr.getTotalOfYears(country, DataDesignations.RUSSIA, tradeFlow, periodStart, periodEnd);
            }

            if (tradeData != null) {
                String json = TradeMapper.jsonTotal(tradeData);
                out.write(json);
            }
        }
    }
}
