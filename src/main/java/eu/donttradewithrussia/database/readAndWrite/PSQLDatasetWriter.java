package eu.donttradewithrussia.database.readAndWrite;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;
import eu.donttradewithrussia.util.LogGenerator;
import org.postgresql.util.PGobject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class PSQLDatasetWriter implements DatasetDataWriter {
    DataSource datasource;

    public PSQLDatasetWriter(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public int addDataset(Dataset dataset) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement queryInsertDataset = conn.prepareStatement(PSQLQTheater.THEATER_QUERY_INSERT)) {

            queryInsertDataset.setString(1, theater.getName());
            queryInsertDataset.setObject(2, seatsJsonObj);
            queryInsertDataset.setString(3, theater.getExtras().toString());

            ResultSet rs = queryInsertDataset.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQTheater.THEATER_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, getClass(), e.getMessage());
        }
        return 0;
    }

    @Override
    public int addDatasets(List<Dataset> datasets) {

    }

    @Override
    public int removeDataset(Dataset dataset) {

    }

    @Override
    public int removeDataset(int reporter, int partner, int period) {

    }

    @Override
    public int removeDataset(int reporter, int partner, int period, char rg) {

    }

    @Override
    public int removeDatasets(int reporter, int partner, int periodStart, int periodEnd) {

    }

    @Override
    public int removeDatasets(int reporter, int partner, int periodStart, int periodEnd, char rg) {

    }
}
