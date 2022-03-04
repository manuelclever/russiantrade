package eu.donttradewithrussia.database.readAndWrite;

public interface DatasetDataReader {
    String getDataset(int reporter, int partner, int period);
    String getDataset(int reporter, int partner, int period, char rg);
    String getDataset(int reporter, int partner, int periodStart, int periodEnd);
    String getDataset(int reporter, int partner, int periodStart, int periodEnd, char rg);
}
