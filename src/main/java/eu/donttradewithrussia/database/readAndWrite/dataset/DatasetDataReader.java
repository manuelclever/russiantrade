package eu.donttradewithrussia.database.readAndWrite.dataset;

import eu.donttradewithrussia.api.comtrade.parser.Dataset;

import java.util.List;

public interface DatasetDataReader {
    List<Dataset> getDatasets(int reporter, int partner);
    List<Dataset> getDatasets(int reporter, int partner, int period);
    List<Dataset> getDatasets(int reporter, int partner, int period, String commodityCode);
    List<Dataset> getDatasets(int reporter, int partner, int periodStart, int periodEnd);
    List<Dataset> getDatasets(int reporter, int partner, int periodStart, int periodEnd, String commodityCode);
}
