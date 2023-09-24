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

        String elapsedTime = node.findValue("elapsedTime").asText();
        int count = node.findValue("count").asInt();
        String error = node.findValue("error").asText();
        error = error.isEmpty() ? null : error;

        if (count > 0) {
            List<TradeData> tradeData = createDatasets(node);
            return new ComtradeResponse(elapsedTime, count, tradeData, error);
        }

        return new ComtradeResponse(elapsedTime, count, null, error);
    }

    private List<TradeData> createDatasets(JsonNode node) {
        JsonNode datasetNodes = node.findValue("data");

        List<TradeData> tradeData = new ArrayList<>();
        if (datasetNodes.isArray()) {

            for (JsonNode datasetNode : datasetNodes) {
                tradeData.add(createDataset(datasetNode));
            }
        } else {
            tradeData.add(createDataset(datasetNodes));
        }

        return tradeData;
    }

    private TradeData createDataset(JsonNode node) {
        TradeData tradeData = new TradeData();

        tradeData.setTypeCode(node.findValue("typeCode").asText().charAt(0));
        tradeData.setFreqCode(node.findValue("freqCode").asText().charAt(0));

        tradeData.setRefPeriodId(node.findValue("refPeriodId").asInt());
        tradeData.setRefYear((short) node.findValue("refYear").asInt());
        tradeData.setRefMonth((short) node.findValue("refMonth").asInt());
        tradeData.setPeriod((short) node.findValue("period").asInt());

        tradeData.setFlowCode(node.findValue("flowCode").asText());
        tradeData.setFlowDesc(node.findValue("flowDesc").asText());

        tradeData.setReporterCode(node.findValue("reporterCode").asInt());
        tradeData.setReporterDesc(node.findValue("reporterDesc").asText());
        tradeData.setReporterIso(node.findValue("reporterISO").asText());

        tradeData.setPartnerCode(node.findValue("partnerCode").asInt());
        tradeData.setPartnerDesc(node.findValue("partnerDesc").asText());
        tradeData.setPartnerIso(node.findValue("partnerISO").asText());

        tradeData.setPartner2Code(node.findValue("partner2Code").asInt());
        tradeData.setPartner2Desc(node.findValue("partner2Desc").asText());
        tradeData.setPartner2Iso(node.findValue("partner2ISO").asText());

        tradeData.setClassificationCode(node.findValue("classificationCode").asText());
        tradeData.setClassificationSearchCode(node.findValue("classificationSearchCode").asText());
        tradeData.setOriginalClassification(node.findValue("isOriginalClassification").asBoolean());

        tradeData.setCommodityCode(node.findValue("cmdCode").asText());
        tradeData.setCommodityDesc(node.findValue("cmdDesc").asText());

        tradeData.setAggregationLevel(node.findValue("aggrLevel").asInt());
        tradeData.setIsLeaf(node.findValue("isLeaf").asBoolean());

        tradeData.setCustomsCode(node.findValue("customsCode").asText());
        tradeData.setCustomsDesc(node.findValue("customsDesc").asText());

        tradeData.setModeOfSupplyCode(node.findValue("mosCode").asInt());

        tradeData.setModeOfTransportCode(node.findValue("motCode").asInt());
        tradeData.setModeOfTransportDesc(node.findValue("motDesc").asText());

        tradeData.setQuantityUnitCode(node.findValue("qtyUnitCode").asInt());
        tradeData.setQuantityUnitAbbreviation(node.findValue("qtyUnitAbbr").asText());
        tradeData.setQuantity(node.findValue("qty").asInt());
        tradeData.setQuantityEstimated(node.findValue("isQtyEstimated").asBoolean());

        tradeData.setAlternativeQuantityUnitCode(node.findValue("altQtyUnitCode").asInt());
        tradeData.setAlternativeQuantityUnitAbbreviation(node.findValue("altQtyUnitAbbr").asText());
        tradeData.setAlternativeQuantity(node.findValue("altQty").asInt());
        tradeData.setAlternativeIsQuantityEstimated(node.findValue("isAltQtyEstimated").asBoolean());

        tradeData.setNetWeight(node.findValue("netWgt").asInt());
        tradeData.setNetWeightEstimated(node.findValue("isNetWgtEstimated").asBoolean());

        tradeData.setGrossWeight(node.findValue("grossWgt").asInt());
        tradeData.setGrossWeightEstimated(node.findValue("isGrossWgtEstimated").asBoolean());

        tradeData.setCostInsuranceFreightValue(node.findValue("cifvalue").asInt());
        tradeData.setFreeOnBoardValue(node.findValue("fobvalue").asInt());
        tradeData.setPrimaryValue(node.findValue("primaryValue").asLong());

        tradeData.setLegacyEstimationFlag(node.findValue("legacyEstimationFlag").asInt());
        tradeData.setReported(node.findValue("isReported").asBoolean());
        tradeData.setAggregated(node.findValue("isAggregate").asBoolean());

        return tradeData;
    }
}
