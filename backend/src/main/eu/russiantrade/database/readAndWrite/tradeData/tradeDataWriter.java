package eu.russiantrade.database.readAndWrite.tradeData;

import eu.russiantrade.comtrade.parser.TradeData;

import java.util.List;

public interface tradeDataWriter {
    int addDataset(TradeData tradeData);
    List<Integer> addDatasets(List<TradeData> tradeData);
    boolean removeDataset(TradeData tradeData);
    boolean removeDataset(int reporter, int period);
    boolean removeDataset(int reporter, int period, String commotidyCode);
    boolean removeDatasets(int reporter, int periodStart, int periodEnd);
    boolean removeDatasets(int reporter, int periodStart, int periodEnd, String commotidyCode);
}
