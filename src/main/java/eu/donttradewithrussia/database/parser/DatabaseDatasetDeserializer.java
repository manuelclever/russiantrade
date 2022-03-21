package eu.donttradewithrussia.database.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import eu.donttradewithrussia.api.comtrade.parser.Dataset;

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

        dataset.setTradeFlowType(node.get("trade_flow").asText());
        dataset.setReporterCode(node.get("reporter").asInt());
        dataset.setPartnerCode(node.get("partner").asInt());
        dataset.setCommodityCode(node.get("commodity_code").asText());
        dataset.setCommodityDesc(node.get("commodity_desc").asText());
        dataset.setTradeValue(node.get("value").asLong());

        return dataset;
    }
}
