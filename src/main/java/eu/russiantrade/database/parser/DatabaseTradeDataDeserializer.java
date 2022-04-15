package eu.russiantrade.database.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.querydesignations.DataDesignations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseTradeDataDeserializer extends StdDeserializer<List<TradeData>> {

    public DatabaseTradeDataDeserializer() {
        this(null);
    }

    public DatabaseTradeDataDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<TradeData> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        return createDatasets(node);
    }

    private List<TradeData> createDatasets(JsonNode node) {
        List<TradeData> tradeData = new ArrayList<>();

        if(node.isArray()) {
            Iterator<JsonNode> iter = node.elements();

            while(iter.hasNext()) {
                JsonNode datasetNode = iter.next();
                tradeData.add(createDataset(datasetNode));
            }
        } else {
            tradeData.add(createDataset(node));
        }

        return tradeData;
    }

    private TradeData createDataset(JsonNode node) {
        TradeData tradeData = new TradeData();

        tradeData.setTradeFlowType(node.get(DataDesignations.MONTHLY_TRADE_FLOW).asText());
        tradeData.setReporterCode(node.get(DataDesignations.MONTHLY_TRADE_REPORTER).asInt());
        tradeData.setReporterDesc(node.get(DataDesignations.MONTHLY_TRADE_REPORTER_DESC).asText());
        tradeData.setPartnerCode(node.get(DataDesignations.MONTHLY_TRADE_PARTNER).asInt());
        tradeData.setPartnerDesc(node.get(DataDesignations.MONTHLY_TRADE_PARTNER_DESC).asText());
        tradeData.setCommodityCode(node.get(DataDesignations.MONTHLY_TRADE_COMMODITY_CODE).asText());
        tradeData.setCommodityDesc(node.get(DataDesignations.MONTHLY_TRADE_COMMODITY_DESC).asText());
        tradeData.setTradeValue(node.get("value").asLong());

        return tradeData;
    }
}
