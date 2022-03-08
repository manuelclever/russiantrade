package eu.donttradewithrussia.database.readAndWrite.dataset;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;

import java.util.List;

public interface DatasetDataWriter {
    int addDataset(Dataset dataset);
    List<Integer> addDatasets(List<Dataset> datasets);
    boolean removeDataset(Dataset dataset);
    boolean removeDataset(int reporter, int period);
    boolean removeDataset(int reporter, int period, String commotidyCode);
    boolean removeDatasets(int reporter, int periodStart, int periodEnd);
    boolean removeDatasets(int reporter, int periodStart, int periodEnd, String commotidyCode);
}
