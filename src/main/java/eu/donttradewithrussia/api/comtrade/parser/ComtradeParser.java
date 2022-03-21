package eu.donttradewithrussia.api.comtrade.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class ComtradeParser {

    public static ComtradeResponse parseResponse(String json) {
        try {
            ObjectMapper mapper = prepareMapper(ComtradeResponse.class, new ComtradeDeserializer());
            System.out.println("reading: " + json);
            return mapper.readValue(json, ComtradeResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static ObjectMapper prepareMapper(Class cl, StdDeserializer deserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(cl, deserializer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        return mapper;
    }
}
