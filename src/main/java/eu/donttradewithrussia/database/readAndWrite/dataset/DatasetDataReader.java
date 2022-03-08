package eu.donttradewithrussia.database.readAndWrite.dataset;

public interface DatasetDataReader {
    String getDataset(int reporter, int partner);
    String getDataset(int reporter, int partner, int period);
    String getDataset(int reporter, int partner, int period, String commodityCode);
    String getDataset(int reporter, int partner, int periodStart, int periodEnd);
    String getDataset(int reporter, int partner, int periodStart, int periodEnd, String commodityCode);
}
