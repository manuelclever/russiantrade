package eu.donttradewithrussia.api.comtrade.parser;

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

    @Override
    public String toString() {
        return "[" + validation.toString() + ", " + datasets.toString() + "]";
    }
}
