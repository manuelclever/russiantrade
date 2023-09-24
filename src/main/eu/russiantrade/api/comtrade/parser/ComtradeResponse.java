package eu.russiantrade.api.comtrade.parser;

import java.util.List;

public class ComtradeResponse {
    private String elapsedTime;
    private int count;
    private List<TradeData> tradeData;
    private String error;

    public ComtradeResponse(String elapsedTime, int count, List<TradeData> tradeData, String error) {
        this.elapsedTime = elapsedTime;
        this.count = count;
        this.tradeData = tradeData;
        this.error = error;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public int getCount() {
        return count;
    }

    public List<TradeData> getDatasets() {
        return tradeData;
    }

    public String getError() {
        return error;
    }

    public boolean isValid() {
        return error == null;
    }

    @Override
    public String toString() {
        if(this.count > 0) {
            return "[" + elapsedTime + ", " + count + ", " + tradeData.toString() + ", " + error + "]";
        }
        return "[" + elapsedTime + ", " + count + ", " + ", [null]" + error + "] ";
    }

    @Override
    public int hashCode() {
        return (count * 7) +
                (tradeData != null ? tradeData.hashCode() * 13 : 0) +
                (error != null ? error.hashCode() * 37 : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if(obj.getClass() == this.getClass()) {
                return obj.hashCode() == this.hashCode();
            }

        }
        return false;
    }
}
