package eu.donttradewithrussia.database.readAndWrite;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;

import java.util.List;

public interface DatasetDataWriter {
    int addDataset(Dataset dataset);
    int addDatasets(List<Dataset> datasets);
    int removeDataset(Dataset dataset);
    int removeDataset(int reporter, int partner, int period);
    int removeDataset(int reporter, int partner, int period, char rg);
    int removeDatasets(int reporter, int partner, int periodStart, int periodEnd);
    int removeDatasets(int reporter, int partner, int periodStart, int periodEnd, char rg);
}
