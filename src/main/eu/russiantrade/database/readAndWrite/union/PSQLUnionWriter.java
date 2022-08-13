package eu.russiantrade.database.readAndWrite.union;

import eu.russiantrade.database.querydesignations.PSQL.PSQLQCoalition;
import eu.russiantrade.database.querydesignations.PSQL.PSQLQUnion;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSQLUnionWriter implements UnionDataWriter {
    DataSource datasource;

    public PSQLUnionWriter(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public int addConnection(int coalitionID, int countryID) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQUnion.QUERY_INSERT)) {

            query.setInt(1, coalitionID);
            query.setInt(2, countryID);

            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQCoalition.COALITION_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean removeConnection(int coalitionID, int countryID) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQUnion.QUERY_DELETE)) {

            query.setInt(1, coalitionID);
            query.setInt(2, countryID);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
