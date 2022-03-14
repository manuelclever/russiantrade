package eu.donttradewithrussia.database.readAndWrite.coalition;

import eu.donttradewithrussia.database.querydesignations.PSQL.PSQLQCoalition;
import eu.donttradewithrussia.util.LogGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class PSQLCoalitionWriter implements CoalitionDataWriter {
    DataSource datasource;

    public PSQLCoalitionWriter(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public int addCoalition(String name) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQCoalition.QUERY_INSERT)) {

            query.setString(1, name);

            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQCoalition.COALITION_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, getClass(), e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean removeCoalition(String name) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQCoalition.QUERY_DELETE_WHERE_NAME)) {

            query.setString(1, name);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, getClass(), e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeCoalition(int groupID) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQCoalition.QUERY_DELETE_WHERE_ID)) {

            query.setInt(1, groupID);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, getClass(), e.getMessage());
        }
        return false;
    }
}
