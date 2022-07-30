package russiantrade.database.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import russiantrade.comtrade.parser.TradeData;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseTradeDataParser {

    public static ArrayList<TradeData> parseResponse(String json) {
        try {
            ObjectMapper mapper = prepareMapper(ArrayList.class, new DatabaseTradeDataDeserializer());
            if(json != null) {
                return mapper.readValue(json, ArrayList.class);
            } return null;
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
