package eu.russiantrade.api.comtrade.parser;

public class Validation {
    private String name;
    private int value;
    private int category;
    private String description;
    private String helpUrl;

    private String message;

    private int countValue;
    private String countStarted;
    private String countFinished;
    private double countDurationSeconds;

    private String datasetTimerStarted;
    private String datasetTimerFinished;
    private double datasetTimerDurationSeconds;

    public Validation() {}

    public Validation(String name, int value, int category, String description, String helpUrl, String message,
                      int countValue, String countStarted, String countFinished, double countDurationSeconds,
                      String datasetTimerStarted, String datasetTimerFinished, double datasetTimerDurationSeconds) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.description = description;
        this.helpUrl = helpUrl;
        this.message = message;
        this.countValue = countValue;
        this.countStarted = countStarted;
        this.countFinished = countFinished;
        this.countDurationSeconds = countDurationSeconds;
        this.datasetTimerStarted = datasetTimerStarted;
        this.datasetTimerFinished = datasetTimerFinished;
        this.datasetTimerDurationSeconds = datasetTimerDurationSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }

    public String getCountStarted() {
        return countStarted;
    }

    public void setCountStarted(String countStarted) {
        this.countStarted = countStarted;
    }

    public String getCountFinished() {
        return countFinished;
    }

    public void setCountFinished(String countFinished) {
        this.countFinished = countFinished;
    }

    public double getCountDurationSeconds() {
        return countDurationSeconds;
    }

    public void setCountDurationSeconds(double countDurationSeconds) {
        this.countDurationSeconds = countDurationSeconds;
    }

    public String getDatasetTimerStarted() {
        return datasetTimerStarted;
    }

    public void setDatasetTimerStarted(String datasetTimerStarted) {
        this.datasetTimerStarted = datasetTimerStarted;
    }

    public String getDatasetTimerFinished() {
        return datasetTimerFinished;
    }

    public void setDatasetTimerFinished(String datasetTimerFinished) {
        this.datasetTimerFinished = datasetTimerFinished;
    }

    public double getDatasetTimerDurationSeconds() {
        return datasetTimerDurationSeconds;
    }

    public void setDatasetTimerDurationSeconds(double datasetTimerDurationSeconds) {
        this.datasetTimerDurationSeconds = datasetTimerDurationSeconds;
    }

    public boolean isValid() {
        return countValue != 0;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + value + ", " + category + ", " + description + ", " + helpUrl + ", " + message +
                ", " + countValue + ", " + countStarted + ", " + countFinished + ", " + datasetTimerStarted + ", " +
                datasetTimerFinished + ", " + datasetTimerDurationSeconds + "]";
    }

    @Override
    public int hashCode() {
        return (
                name != null ? name.hashCode() : 0) +
                countValue + (countStarted != null ? countStarted.hashCode() : 0) +
                (countFinished != null ? countFinished.hashCode() : 0) +
                (datasetTimerStarted != null ? datasetTimerStarted.hashCode() : 0) +
                (datasetTimerFinished != null ? datasetTimerFinished.hashCode() : 0) ;
    }
}
