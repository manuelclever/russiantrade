package eu.russiantrade.api.comtrade.parser;

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

    @Override
    public int hashCode() {
        return (validation != null ? validation.hashCode() * 7 : 0) +
                (tradeData != null ? tradeData.hashCode() * 13 : 0);
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
