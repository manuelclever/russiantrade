package eu.russiantrade.database.querydesignations;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;

public class Query {

    public static String queryWhereInt(DataSource datasource, String statement, int ... args) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            int i = 1;
            for(int arg : args) {
                query.setInt(i++, arg);
            }
            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryWhereStringAndInt(DataSource datasource, String statement, String s, int ... args) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            query.setString(1, s);
            int i = 2;
            for(int arg : args) {
                query.setInt(i++, arg);
            }
            System.out.println(query);
            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryWhereTwoStringAndInt(DataSource datasource, String statement, String s1,
                                                   String s2, int ... args) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            query.setString(1, s1);
            query.setString(2, s2);
            int i = 3;
            for(int arg : args) {
                query.setInt(i++, arg);
            }

            System.out.println(query);
            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryWhereTwoStringAndIntServlet(PrintWriter out, DataSource datasource, String statement,
                                                          String s1,
                                                          String s2, int ... args) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            query.setString(1, s1);
            query.setString(2, s2);
            int i = 3;
            for(int arg : args) {
                query.setInt(i++, arg);
            }

            out.println("<p>Query: " + query + "</p>");
            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                out.println("<p>Output: " + output + "</p>");
                out.println("<p>close re</p>");
                rs.close();
                out.println("<p>return output</p>");
                return output;
            }

        } catch(SQLException e) {
            out.println("<p>SQL Exception</p>");
            out.println(e.getMessage());
            out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");
        }
        return null;
    }

    public static String queryWhereOneString(DataSource datasource, String statement, String string) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            query.setString(0, string);
            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(0);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryWhereString(DataSource datasource, String statement, String ... args) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            int i = 1;
            for(String arg : args) {
                query.setString(i++, arg);
            }

            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryAll(DataSource datasource, String statement) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(statement)) {

            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ResultSet executeQuery(DataSource datasource, String sql) {
        try(Connection con = datasource.getConnection();
            Statement st = con.createStatement()) {
            return st.executeQuery(sql);
        } catch (SQLException ignore) {}
        return null;
    }
}
