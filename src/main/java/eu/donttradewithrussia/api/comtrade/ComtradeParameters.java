package eu.donttradewithrussia.api.comtrade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class ComtradeParameters {
    private static final String TRADE_DATA_TYPE = "type=";
    private static final String FREQUENCY = "freq=";
    private static final String REPORTER = "r=";
    private static final String PERIOD = "ps=";
    private static final String CLASSIFICATION = "px=";
    private static final String TOKEN = "token=";
    private static final String PARTNER = "p=";
    private static final String TRADE_REGIME = "rg=";
    private static final String CLASSIFICATION_CODE = "cc=";
    private static final String OUTPUT_FORMAT = "fmt=";
    private static final String MAX = "max=";
    private static final String HEAD = "head=";
    private static final String IMTS = "imts=";

    private String tradeDataType;
    private String frequency;
    private String reporter;
    private String period;
    private String classification;
    private String token;
    private String partner;
    private String tradeRegime;
    private String classificationCode;
    private String outputFormat;
    private String max;
    private String head;
    private String imts;

    public ComtradeParameters(char tradeDataType, char frequency, int reporter, long period,
                              String classification, String token, int partner, String tradeRegime,
                              String classificationCode, String outputFormat, int max, char head, int imts) {
        setTradeDataType(tradeDataType);
        setFrequency(frequency);
        setReporter(reporter);
        setPeriod(period);
        setClassification(classification);
        setToken(token);
        setPartner(partner);
        setTradeRegime(tradeRegime);
        setClassificationCode(classificationCode);
        setOutputFormat(outputFormat);
        setMax(max);
        setHead(head);
        setImts(imts);
    }

    public String getTradeDataType() {
        return tradeDataType;
    }

    public void setTradeDataType(char tradeDataType) {
        if (tradeDataType != Character.MIN_VALUE) {
            this.tradeDataType = TRADE_DATA_TYPE + tradeDataType;
        } else {
            this.tradeDataType = null;
        }
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(char frequency) {
        if (frequency != Character.MIN_VALUE) {
            this.frequency = FREQUENCY + frequency;
        } else {
            this.tradeDataType = null;
        }
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        if (reporter != -1) {
            this.reporter = REPORTER + reporter;
        } else {
            this.reporter = null;
        }
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        if (period != -1) {
            this.period = PERIOD + period;
        } else {
            this.period = null;
        }
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        if (classification != null) {
            this.classification = CLASSIFICATION + classification;
        } else {
            this.classification = null;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if (token != null) {
            this.token = TOKEN + token;
        } else {
            this.token = null;
        }
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        if (partner != -1) {
            this.partner = PARTNER + partner;
        } else {
            this.partner = null;
        }
    }

    public String getTradeRegime() {
        return tradeRegime;
    }

    public void setTradeRegime(String tradeRegime) {
        if (tradeRegime != null) {
            this.tradeRegime = TRADE_REGIME + tradeRegime;
        } else {
            this.tradeRegime = null;
        }
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(String classificationCode) {
        if (classificationCode != null) {
            this.classificationCode = CLASSIFICATION_CODE + classificationCode;
        } else {
            this.classificationCode = null;
        }
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        if (outputFormat != null) {
            this.outputFormat = OUTPUT_FORMAT + outputFormat;
        } else {
            this.outputFormat = null;
        }
    }

    public String getMax() {
        return max;
    }

    public void setMax(int max) {
        if (max != -1) {
            this.max = MAX + max;
        } else {
            this.max = null;
        }
    }

    public String getHead() {
        return head;
    }

    public void setHead(char head) {
        if (head != Character.MIN_VALUE) {
            this.head = HEAD + head;
        } else {
            this.head = null;
        }
    }

    public String getImts() {
        return imts;
    }

    public void setImts(int imts) {
        if (imts != -1) {
            this.imts = IMTS + imts;
        } else {
            this.imts = null;
        }
    }

    public Stream<String> stream() {
        List<String> list = new ArrayList<>();
        list.add(tradeDataType);
        list.add(frequency);
        list.add(reporter);
        list.add(period);
        list.add(classification);
        list.add(token);
        list.add(partner);
        list.add(tradeRegime);
        list.add(classificationCode);
        list.add(outputFormat);
        list.add(max);
        list.add(head);
        list.add(imts);

        return list.stream();
    }
}
