package eu.donttradewithrussia.api.comtrade.parser;

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
            List<Dataset> datasets = createDatasets(node);
            return new ComtradeResponse(validation, datasets);
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


    private List<Dataset> createDatasets(JsonNode node) {
        JsonNode datasetNodes = node.findValue("dataset");

        List<Dataset> datasets = new ArrayList<>();
        if(datasetNodes.isArray()) {

            for(JsonNode datasetNode : datasetNodes) {
                datasets.add(createDataset(datasetNode));
            }
        } else {
            datasets.add(createDataset(datasetNodes));
        }

        return datasets;
    }

    private Dataset createDataset(JsonNode node) {
        Dataset dataset = new Dataset();

        dataset.setPfCode(node.findValue("pfCode").asText());
        dataset.setYear(node.findValue("yr").asInt());
        dataset.setPeriod(node.findValue("period").asInt());
        dataset.setAggrLevel(node.findValue("aggrLevel").asInt());
        dataset.setIsLeaf(node.findValue("IsLeaf").asInt());

        dataset.setTradeFlowCode(node.findValue("rgCode").asInt());
        dataset.setTradeFlowType(node.findValue("rgDesc").asText());

        dataset.setReporterCode(node.findValue("rtCode").asInt());
        dataset.setReporterDesc(node.findValue("rtTitle").asText());
        dataset.setRt3iso(node.findValue("rt3ISO").asText());

        dataset.setPartnerCode(node.findValue("ptCode").asInt());
        dataset.setPartnerDesc(node.findValue("ptTitle").asText());
        dataset.setPt3iso(node.findValue("pt3ISO").asText());

        dataset.setPartnerCode2(node.findValue("ptCode2").asInt());
        dataset.setPartnerDesc2(node.findValue("ptTitle2").asText());
        dataset.setPt3iso2(node.findValue("pt3ISO2").asText());

        dataset.setCommodityCode(node.findValue("cmdCode").asText());
        dataset.setCommodityDesc(node.findValue("cmdDescE").asText());

        dataset.setQtCode(node.findValue("qtCode").asInt());
        dataset.setQtDesc(node.findValue("qtDesc").asText());

        dataset.setQtAltCode(node.findValue("qtAltCode").asInt());
        dataset.setQtAltDesc(node.findValue("qtAltDesc").asText());
        dataset.setTradeValue(node.findValue("TradeValue").asInt());

        dataset.setCifValue(node.findValue("CIFValue").asInt());
        dataset.setFobValue(node.findValue("FOBValue").asInt());
        dataset.setEstCode(node.findValue("estCode").asInt());

        return dataset;
    }
}
