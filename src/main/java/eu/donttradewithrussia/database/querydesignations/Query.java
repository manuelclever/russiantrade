package eu.donttradewithrussia.database.querydesignations;

import eu.donttradewithrussia.util.LogGenerator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;

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
            LogGenerator.log(Level.WARNING, statement, e.getMessage());
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

            ResultSet rs = query.executeQuery();

            if(rs.next()) {
                String output = rs.getString(1);
                rs.close();
                return output;
            }

        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, statement, e.getMessage());
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
            LogGenerator.log(Level.WARNING, statement, e.getMessage());
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
            LogGenerator.log(Level.WARNING, statement, e.getMessage());
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
            LogGenerator.log(Level.WARNING, statement, e.getMessage());
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
