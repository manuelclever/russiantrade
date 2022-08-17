package eu.russiantrade.database.readAndWrite.country;

import eu.russiantrade.database.parser.Country;
import eu.russiantrade.database.querydesignations.PSQL.PSQLQCountry;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSQLCountryWriter implements CountryDataWriter {
    DataSource datasource;

    public PSQLCountryWriter(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public int addCountry(String name, String abbrev, int comtradeId) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQCountry.QUERY_INSERT)) {

            query.setInt(1, comtradeId);
            query.setString(2, name);
            query.setString(3, abbrev);

            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQCountry.COUNTRY_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addCountry(Country country) {
        return addCountry(country.getName(), country.getAbbrev(), country.getCountryID());
    }

    @Override
    public int insertCountry(Country country, int whereCountryID) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQCountry.QUERY_UPDATE)) {

            query.setInt(1, country.getCountryID());
            query.setString(2, country.getName());
            query.setString(3, country.getAbbrev());
            query.setInt(4, whereCountryID);

            System.out.println(query);
            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQCountry.COUNTRY_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
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
        }
        return false;
    }

    @Override
    public boolean removeCountry(int comtradeId) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQCountry.QUERY_DELETE_WHERE_COUNTRY_ID)) {

            query.setInt(1, comtradeId);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
