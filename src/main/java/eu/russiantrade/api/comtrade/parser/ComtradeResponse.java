package eu.russiantrade.api.comtrade.parser;

import java.util.List;

public class ComtradeResponse {
    private Validation validation;
    private List<Dataset> datasets;

    public ComtradeResponse(Validation validation, List<Dataset> dataset) {
        this.validation = validation;
        this.datasets = dataset;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
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
            return "[" + validation.toString() + ", " + datasets.toString() + "]";
        }
        return "[" + validation.toString() + ", [null]]";
    }
}
