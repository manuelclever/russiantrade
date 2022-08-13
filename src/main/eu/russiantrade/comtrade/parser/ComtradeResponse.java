package eu.russiantrade.comtrade.parser;

import java.util.List;

public class ComtradeResponse {
    private Validation validation;
    private List<TradeData> tradeData;

    public ComtradeResponse(Validation validation, List<TradeData> tradeData) {
        this.validation = validation;
        this.tradeData = tradeData;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public List<TradeData> getDatasets() {
        return tradeData;
    }

    public void setDatasets(List<TradeData> tradeData) {
        this.tradeData = tradeData;
    }

    public boolean isValid() {
        if(validation != null) {
            return validation.isValid();
        }
        return false;
    }

    @Override
    public String toString() {
        if(validation.isValid()) {
            return "[" + validation.toString() + ", " + tradeData.toString() + "]";
        }
        return "[" + validation.toString() + ", [null]]";
    }
}
