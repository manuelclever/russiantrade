package eu.donttradewithrussia.database.readAndWrite.dataset;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;
import eu.donttradewithrussia.database.querydesignations.PSQL.PSQLQDataset;
import eu.donttradewithrussia.util.LogGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            PreparedStatement query = conn.prepareStatement(PSQLQDataset.QUERY_INSERT)) {

            query.setInt(1, dataset.getPeriod());
            query.setInt(2, dataset.getReporterCode());
            query.setInt(3, dataset.getPartnerCode());
            query.setInt(4, dataset.getTradeFlowCode());
            query.setString(5, dataset.getCommodityCode());
            query.setString(6, dataset.getCommodityDesc());
            query.setLong(7, dataset.getTradeValue());
//            query.setInt(6, dataset.getReporterCode()); //global sanction id
//            query.setInt(7, dataset.getReporterCode()); //local sanction id

            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQDataset.MONTHLY_TRADE_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            LogGenerator.log(Level.WARNING, getClass(), e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Integer> addDatasets(List<Dataset> datasets) {
        List<Integer> datasetIDs = new ArrayList<>();

        for(Dataset dataset : datasets) {
            datasetIDs.add(addDataset(dataset));
        }
        return datasetIDs;
    }

    @Override
    public boolean removeDataset(Dataset dataset) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQDataset.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY)) {

            query.setInt(1, dataset.getReporterCode());
            query.setInt(2, dataset.getPeriod());
            query.setString(3, dataset.getCommodityCode());

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
    public boolean removeDataset(int reporter, int period) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQDataset.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD)) {

            query.setInt(1, reporter);
            query.setInt(2, period);

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
    public boolean removeDataset(int reporter, int period, String commotidyCode) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQDataset.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY)) {

            query.setInt(1, reporter);
            query.setInt(2, period);
            query.setString(3, commotidyCode);

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
    public boolean removeDatasets(int reporter, int periodStart, int periodEnd) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQDataset.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_BETWEEN)) {

            query.setInt(1, reporter);
            query.setInt(2, periodStart);
            query.setInt(3, periodEnd);

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
    public boolean removeDatasets(int reporter, int periodStart, int periodEnd, String commotidyCode) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQDataset.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY_BETWEEN)) {

            query.setInt(1, reporter);
            query.setInt(2, periodStart);
            query.setInt(3, periodEnd);
            query.setString(4, commotidyCode);

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
