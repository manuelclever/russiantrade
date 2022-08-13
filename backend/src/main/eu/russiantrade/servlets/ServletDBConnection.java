package eu.russiantrade.servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ServletDBConnection extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket

        PrintWriter out = resp.getWriter();

        try {
            InitialContext initCtx = new InitialContext();
            if (initCtx == null) {
                throw new Exception("Uh oh -- no context!");
            }

            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/tradetest_db");

            if (ds == null) {
                throw new Exception("Lookup didn't work. Data source not found!");
            }
            out.println("<p>Connection: " + ds.getConnection().getClientInfo() + "</p>");
        } catch (Exception e) {
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
