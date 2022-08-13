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
import java.util.List;

public class ServletCommodities extends HttpServlet {
    private static final String DB_PROPERTIES = FileSystems.getDefault().getPath(
            "src", "test", "", "backend/src/main/resources/database", "testDatabase.properties")
            .toAbsolutePath().toString();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket

        try (PrintWriter out = resp.getWriter()) {
            int country = Integer.parseInt(req.getParameter("country"));
            String tradeFlow = req.getParameter("trade_flow");
            int period = Integer.parseInt(req.getParameter("period"));
            String commodity = req.getParameter("commodity");

            DSCreator dsC = new DSCreator(DB_PROPERTIES);
            PSQLTradeReader dr = new PSQLTradeReader(dsC.getDataSourceTradeDBServlet(out));

            List<TradeData> tradeData = null;
            int digits = DigitCount.count(period);
            if (digits == 4) {
                tradeData = dr.getCommodityMonth(country, DataDesignations.RUSSIA, tradeFlow, period, commodity);
            } else if (digits == 6) {
                tradeData = dr.getCommodityYear(country, DataDesignations.RUSSIA, tradeFlow, period, commodity);
            }

            if (tradeData != null) {
                String json = TradeMapper.jsonCommodity(tradeData);
                out.write(json);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
