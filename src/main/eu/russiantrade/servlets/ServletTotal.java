package eu.russiantrade.servlets;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.querydesignations.DataDesignations;
import eu.russiantrade.database.readAndWrite.TradeMapper;
import eu.russiantrade.database.readAndWrite.monthly_trade.PSQLTradeReader;
import eu.russiantrade.util.DigitCount;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class ServletTotal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context = req.getServletContext();
                // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        try {
            int country = Integer.parseInt(req.getParameter("country"));
            String tradeFlow = req.getParameter("trade_flow");
            int periodStart = Integer.parseInt(req.getParameter("periodStart"));
            int periodEnd = Integer.parseInt(req.getParameter("periodEnd"));

            out.println("<p>country: " + country + "</p>");
            out.println("<p>tradeFlow: " + tradeFlow + "</p>");
            out.println("<p>periodStart: " + periodStart + "</p>");
            out.println("<p>periodEnd: " + periodEnd + "</p>");


            DSCreator dsC = new DSCreator(out);

            out.println("<p>getDataSourceTradeDB</p>");
            DataSource ds = dsC.getDataSourceTradeDBServlet(out);
            out.println("<p>string is being created</p>");
            PSQLTradeReader dr = new PSQLTradeReader(ds, out);

            List<TradeData> tradeData = null;
            if (DigitCount.count(periodStart) == 4 && DigitCount.count(periodEnd) == 4) {
                out.println("<p>getting trade data</p>");
                tradeData = dr.getTotalOfYears(country, DataDesignations.RUSSIA, tradeFlow, periodStart, periodEnd);
            }
            out.println("<p>is trade data other than null?</p>");
            if (tradeData != null) {
                String json = TradeMapper.jsonTotal(tradeData);
                out.println("<p>" + country + ", " + tradeFlow + ", " + periodStart + ", " + periodEnd + "</p>");

                out.write(json);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
            }
        } catch (Exception e) {
            out.println("<p>Error!!!</p>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");

        } finally {
            out.close();
            out.println("<p>closed</p>");
        }
        out.println("<p>end</p>");
    }
}
