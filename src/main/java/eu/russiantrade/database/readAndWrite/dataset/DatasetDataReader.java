package eu.russiantrade.database.readAndWrite.dataset;

import eu.russiantrade.api.comtrade.parser.Dataset;

import java.util.List;

public interface DatasetDataReader {
    List<Dataset> getDatasets(int reporter, int partner, String tradeFlow);
    List<Dataset> getDatasets(int reporter, int partner, String tradeFlow, int period);
    List<Dataset> getDatasets(int reporter, int partner, String tradeFlow, int period, String commodityCode);
    List<Dataset> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd);
    List<Dataset> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd, String commodityCode);
}
