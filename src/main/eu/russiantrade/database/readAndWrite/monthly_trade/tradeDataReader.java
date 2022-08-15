package eu.russiantrade.database.readAndWrite.monthly_trade;

import eu.russiantrade.api.comtrade.parser.TradeData;

import java.util.List;

public interface tradeDataReader {
    List<TradeData> getTotalOfYear(int reporter, int partner, String tradeFlow, int period);
    List<TradeData> getTotalOfYears(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd);
    List<TradeData> getCommodityMonth(int reporter, int partner, String tradeFlow, int period, String commodityCode);
    List<TradeData> getCommodityYear(int reporter, int partner, String tradeFlow, int period, String commodityCode);

    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow);
    List<TradeData> getDatasets(int reporter, int partner, int period);
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int period);
    List<TradeData> getDatasets(int reporter, int partner, String tradeFlow, int periodStart, int periodEnd);
}
