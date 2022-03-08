package eu.donttradewithrussia.database.readAndWrite.country;

import eu.donttradewithrussia.database.querydesignations.PSQL.PSQLQCountry;
import eu.donttradewithrussia.util.LogGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class PSQLCountryWriter implements CountryDataWriter {
    DataSource datasource;

    public PSQLCountryWriter(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public int addCountry(String name, String abbrev, int comtradeId) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQCountry.QUERY_INSERT)) {

            query.setString(1, name);
            query.setString(2, abbrev);
            query.setInt(3, comtradeId);

            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQCountry.COUNTRY_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, getClass(), e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean removeCountry(String name) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQCountry.QUERY_DELETE_WHERE_NAME)) {

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
    public boolean removeCountry(int comtradeId) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQCountry.QUERY_DELETE_WHERE_COMTRADE_ID)) {

            query.setInt(1, comtradeId);

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
