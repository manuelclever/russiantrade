package eu.russiantrade.api.comtrade.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComtradeDeserializer extends StdDeserializer<ComtradeResponse> {

    public ComtradeDeserializer() {
        this(null);
    }

    public ComtradeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ComtradeResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        Validation validation = createValidation(node);

        if(validation.isValid()) {
            List<TradeData> tradeData = createDatasets(node);
            return new ComtradeResponse(validation, tradeData);
        }
        return new ComtradeResponse(validation, null);
    }

    private Validation createValidation(JsonNode node) {
        Validation validation = new Validation();

        JsonNode validationNode = node.findValue("validation");

        JsonNode status = validationNode.findValue("status");
        validation.setName(status.get("name").asText());
        validation.setValue(status.get("value").asInt());
        validation.setCategory(status.get("category").asInt());
        validation.setDescription(status.get("description").asText());
        validation.setHelpUrl(status.get("helpUrl").asText());

        validation.setMessage(validationNode.get("message").asText());

        JsonNode count = validationNode.findValue("count");
        validation.setCountValue(count.get("value").asInt());
        validation.setCountStarted(count.get("started").asText());
        validation.setCountFinished(count.get("finished").asText());
        validation.setCountDurationSeconds(count.get("durationSeconds").asDouble());

        JsonNode datasetTimer = validationNode.findValue("datasetTimer");
        if(!datasetTimer.toString().equals("null")) { //datasetTimer could be null
            validation.setDatasetTimerStarted(datasetTimer.get("started").asText());
            validation.setDatasetTimerFinished(datasetTimer.get("finished").asText());
            validation.setDatasetTimerDurationSeconds(datasetTimer.get("durationSeconds").asDouble());
        }

        return validation;
    }


    private List<TradeData> createDatasets(JsonNode node) {
        JsonNode datasetNodes = node.findValue("dataset");

        List<TradeData> tradeData = new ArrayList<>();
        if(datasetNodes.isArray()) {

            for(JsonNode datasetNode : datasetNodes) {
                tradeData.add(createDataset(datasetNode));
            }
        } else {
            tradeData.add(createDataset(datasetNodes));
        }

        return tradeData;
    }

    private TradeData createDataset(JsonNode node) {
        TradeData tradeData = new TradeData();

        tradeData.setPfCode(node.findValue("pfCode").asText());
        tradeData.setYear(node.findValue("yr").asInt());
        tradeData.setPeriod(node.findValue("period").asInt());
        tradeData.setAggrLevel(node.findValue("aggrLevel").asInt());
        tradeData.setIsLeaf(node.findValue("IsLeaf").asInt());

        tradeData.setTradeFlowCode(node.findValue("rgCode").asInt());
        tradeData.setTradeFlowType(node.findValue("rgDesc").asText());

        tradeData.setReporterCode(node.findValue("rtCode").asInt());
        tradeData.setReporterDesc(node.findValue("rtTitle").asText());
        tradeData.setRt3iso(node.findValue("rt3ISO").asText());

        tradeData.setPartnerCode(node.findValue("ptCode").asInt());
        tradeData.setPartnerDesc(node.findValue("ptTitle").asText());
        tradeData.setPt3iso(node.findValue("pt3ISO").asText());

        tradeData.setPartnerCode2(node.findValue("ptCode2").asInt());
        tradeData.setPartnerDesc2(node.findValue("ptTitle2").asText());
        tradeData.setPt3iso2(node.findValue("pt3ISO2").asText());

        tradeData.setCommodityCode(node.findValue("cmdCode").asText());
        tradeData.setCommodityDesc(node.findValue("cmdDescE").asText());

        tradeData.setQtCode(node.findValue("qtCode").asInt());
        tradeData.setQtDesc(node.findValue("qtDesc").asText());

        tradeData.setQtAltCode(node.findValue("qtAltCode").asInt());
        tradeData.setQtAltDesc(node.findValue("qtAltDesc").asText());
        tradeData.setTradeValue(node.findValue("TradeValue").asInt());

        tradeData.setCifValue(node.findValue("CIFValue").asInt());
        tradeData.setFobValue(node.findValue("FOBValue").asInt());
        tradeData.setEstCode(node.findValue("estCode").asInt());

        return tradeData;
    }
}
