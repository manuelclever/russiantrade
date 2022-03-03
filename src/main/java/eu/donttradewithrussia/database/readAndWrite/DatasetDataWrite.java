package eu.donttradewithrussia.database.readAndWrite;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;

import java.util.List;

public interface DatasetDataWrite {
    void addDataset(Dataset dataset);
    void addDatasets(List<Dataset> datasets);
    void removeDataset(Dataset dataset);
    void removeDataset(int reporter, int partner, int period);
    void removeDataset(int reporter, int partner, int period, char rg);
    void removeDatasets(int reporter, int partner, int periodStart, int periodEnd);
    void removeDatasets(int reporter, int partner, int periodStart, int periodEnd, char rg);
}
