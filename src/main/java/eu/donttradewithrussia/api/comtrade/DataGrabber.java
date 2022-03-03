package eu.donttradewithrussia.api.comtrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeDeserializer;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeResponse;

import java.io.IOException;

public class DataGrabber {

    public static void main(String[] args) {
        ComtradeParametersAvailability availability = new ComtradeParametersAvailability('M', 56, 201908);
        ComtradeParametersRequest request = new ComtradeParametersRequest('M', 56, 201908, 251, "All", "TOTAL", 10000);

        APICall apiCall = new APICall(availability);
        String jsonAvailability = apiCall.call();

        apiCall = new APICall(request);
        String jsonRequest = apiCall.call();

        ComtradeResponse comtradeResponse = parseResponse(jsonRequest);

        System.out.println(comtradeResponse);
    }

    private static ComtradeResponse parseResponse(String json) {
        try {
            ObjectMapper mapper = prepareMapper(ComtradeResponse.class, new ComtradeDeserializer());
            return mapper.readValue(json, ComtradeResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

    private static ObjectMapper prepareMapper(Class cl, StdDeserializer deserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(cl, deserializer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        return mapper;
    }
}
