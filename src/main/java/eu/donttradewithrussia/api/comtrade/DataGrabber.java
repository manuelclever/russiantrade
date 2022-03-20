package eu.donttradewithrussia.api.comtrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeDeserializer;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataGrabber {

    public static void main(String[] args) {
        try {
            List<Integer> countries = getCountries();
            List<Integer> periods = getPeriods();

            for(Integer country : countries) {
                ComtradeParametersRequest request = new ComtradeParametersRequest('M', country, 201504, 643
                        , new String[]{"TOTAL", "AG2"},
                        10000);

                APICall apiCall = new APICall(request);
                String jsonRequest = apiCall.call();
                ComtradeResponse comtradeResponse = parseResponse(jsonRequest);
                System.out.println(comtradeResponse);
            }
        } catch (IOException e) {

        }

//        String jsonAvailability = apiCall.call();
//
//        System.out.println(jsonAvailability);


    }

    private static List<Integer> getCountries() throws IOException {
        List<Integer> countries = new ArrayList<>();

        File file = new File(FileSystems.getDefault()
                .getPath("src", "main", "resources", "countries.txt").toString());

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                if(line.charAt(0) != '#') {
                    String[] country = line.split("_");
                    countries.add(Integer.parseInt(country[0]));
                }
            }

            return countries;
    }

    private static List<Integer> getPeriods() {
        int year = 2010;
        int month = 1;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate now = LocalDate.now();
        String end = dtf.format(now);

        List<Integer> periods = new ArrayList<>();
        String period = year + String.format("%02d", month);
        for(; Integer.parseInt(period) <= Integer.parseInt(end);) {
            periods.add(Integer.parseInt(period));

            if(month < 12) {
                month++;
            } else {
                year++;
                month = 1;
            }
            period = year + String.format("%02d", month);
        }
        return periods;
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
