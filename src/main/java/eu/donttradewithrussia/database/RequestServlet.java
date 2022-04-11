package eu.donttradewithrussia.database;

import eu.donttradewithrussia.database.datasource.DSCreator;
import eu.donttradewithrussia.database.readAndWrite.dataset.PSQLDatasetReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class RequestServlet extends HttpServlet {
    private static final Path DB_PROPERTIES = FileSystems.getDefault().getPath(
            "src", "test", "resources", "database", "testDatabase.properties");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = resp.getWriter();

        try {
            String country = req.getParameter("country");
            String type = req.getParameter("type");
            String commodity = req.getParameter("commodity");

            DSCreator dsC = new DSCreator(DB_PROPERTIES);
            PSQLDatasetReader dr = new PSQLDatasetReader(dsC.getDataSourceTradeDB());

            out.println("<!DOCTYPE html");  // HTML 5
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<body>");

            boolean noParam = true;
            String firstName = req.getParameter("firstname");
            if (firstName != null && (firstName = firstName.trim()).length() != 0) {
                out.println(" = " + HtmlFilter.filter(firstName) + "<br />");
                noParam = false;
            }

            String lastName = req.getParameter("lastname");
            if (lastName != null && (lastName = lastName.trim()).length() != 0) {
                out.println(" = " + HtmlFilter.filter(lastName));
                noParam = false;
            }
            out.println("<br /><br />");

            // Display a form to prompt user for parameters.
            // Use default "action" to the current page
            out.println("<form method='get'>");
            out.println("<input type='text' name='firstname'><br />");
            out.println("<input type='text' name='lastname'><br />");
            out.println("<input type='submit' value='SEND'>");
            out.println("</form></body></html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}
