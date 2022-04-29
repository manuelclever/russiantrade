package eu.russiantrade.database.readAndWrite.tradeData;

import eu.russiantrade.api.comtrade.parser.TradeData;

import java.util.List;

public interface tradeDataReader {
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow);
    List<TradeData> getDatasets(int reporter, int partner, int period);
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int period);
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int period, String commodityCode);
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd);
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd, String commodityCode);
}
