package eu.russiantrade.database.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import eu.russiantrade.api.comtrade.parser.Dataset;
import eu.russiantrade.database.querydesignations.DataDesignations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseDatasetDeserializer extends StdDeserializer<List<Dataset>> {

    public DatabaseDatasetDeserializer() {
        this(null);
    }

    public DatabaseDatasetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Dataset> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        return createDatasets(node);
    }

    private List<Dataset> createDatasets(JsonNode node) {
        List<Dataset> datasets = new ArrayList<>();

        if(node.isArray()) {
            Iterator<JsonNode> iter = node.elements();

            while(iter.hasNext()) {
                JsonNode datasetNode = iter.next();
                datasets.add(createDataset(datasetNode));
            }
        } else {
            datasets.add(createDataset(node));
        }

        return datasets;
    }

    private Dataset createDataset(JsonNode node) {
        Dataset dataset = new Dataset();

        dataset.setTradeFlowType(node.get(DataDesignations.MONTHLY_TRADE_FLOW).asText());
        dataset.setReporterCode(node.get(DataDesignations.MONTHLY_TRADE_REPORTER).asInt());
        dataset.setReporterDesc(node.get(DataDesignations.MONTHLY_TRADE_REPORTER_DESC).asText());
        dataset.setPartnerCode(node.get(DataDesignations.MONTHLY_TRADE_PARTNER).asInt());
        dataset.setPartnerDesc(node.get(DataDesignations.MONTHLY_TRADE_PARTNER_DESC).asText());
        dataset.setCommodityCode(node.get(DataDesignations.MONTHLY_TRADE_COMMODITY_CODE).asText());
        dataset.setCommodityDesc(node.get(DataDesignations.MONTHLY_TRADE_COMMODITY_DESC).asText());
        dataset.setTradeValue(node.get("value").asLong());

        return dataset;
    }
}
