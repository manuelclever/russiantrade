package eu.russiantrade.database.readAndWrite.monthly_trade;

import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.querydesignations.PSQL.PSQLQTradeData;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSQLTradeWriter implements tradeDataWriter {
    DataSource datasource;

    public PSQLTradeWriter(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public int addDataset(TradeData tradeData) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQTradeData.QUERY_INSERT)) {

            query.setInt(1, tradeData.getPeriod());
            query.setInt(2, tradeData.getReporterCode());
            query.setInt(3, tradeData.getPartnerCode());
            query.setString(4, tradeData.getTradeFlowDesc());
            query.setString(5, tradeData.getCommodityCode());
            query.setString(6, tradeData.getCommodityDesc());
            query.setLong(7, tradeData.getTradeValue());
//            query.setInt(6, dataset.getReporterCode()); //global sanction id
//            query.setInt(7, dataset.getReporterCode()); //local sanction id

            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                conn.commit();
                return rs.getInt(PSQLQTradeData.MONTHLY_TRADE_ID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Integer> addDatasets(List<TradeData> tradeDataList) {
        List<Integer> datasetIDs = new ArrayList<>();

        for(TradeData tradeData : tradeDataList) {
            datasetIDs.add(addDataset(tradeData));
        }
        return datasetIDs;
    }

    @Override
    public boolean removeDataset(TradeData tradeData) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQTradeData.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY)) {

            query.setInt(1, tradeData.getReporterCode());
            query.setInt(2, tradeData.getPeriod());
            query.setString(3, tradeData.getCommodityCode());

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeDataset(int reporter, int period) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQTradeData.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD)) {

            query.setInt(1, reporter);
            query.setInt(2, period);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeDataset(int reporter, int period, String commotidyCode) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query = conn.prepareStatement(PSQLQTradeData.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY)) {

            query.setInt(1, reporter);
            query.setInt(2, period);
            query.setString(3, commotidyCode);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeDatasets(int reporter, int periodStart, int periodEnd) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQTradeData.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_BETWEEN)) {

            query.setInt(1, reporter);
            query.setInt(2, periodStart);
            query.setInt(3, periodEnd);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeDatasets(int reporter, int periodStart, int periodEnd, String commotidyCode) {

        try(Connection conn = datasource.getConnection();
            PreparedStatement query =
                    conn.prepareStatement(PSQLQTradeData.QUERY_DELETE_WHERE_REPORTER_AND_PERIOD_AND_COMMODITY_BETWEEN)) {

            query.setInt(1, reporter);
            query.setInt(2, periodStart);
            query.setInt(3, periodEnd);
            query.setString(4, commotidyCode);

            boolean success = query.execute();
            conn.commit();
            return success;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
