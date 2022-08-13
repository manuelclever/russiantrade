package eu.russiantrade.database.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.russiantrade.api.comtrade.parser.TradeData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<TradeData> parseResponseServlet(String json, PrintWriter out) {
        out.println("<p>prepare Mapper</p>");
        try {
            ObjectMapper mapper = prepareMapper(ArrayList.class, new DatabaseTradeDataDeserializer());
            if(json != null) {
                out.println("<p>mapper read value</p>");
                return mapper.readValue(json, ArrayList.class);
            } return null;
        } catch (IOException e) {
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<p>" + Arrays.toString(e.getStackTrace()) + "</p>");
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
