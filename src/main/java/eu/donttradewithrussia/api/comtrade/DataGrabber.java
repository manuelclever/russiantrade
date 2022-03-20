package eu.donttradewithrussia.api.comtrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeDeserializer;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeResponse;
import eu.donttradewithrussia.api.comtrade.parser.Dataset;
import eu.donttradewithrussia.database.datasource.DSCreator;
import eu.donttradewithrussia.database.readAndWrite.country.PSQLCountryWriter;
import eu.donttradewithrussia.database.readAndWrite.dataset.PSQLDatasetWriter;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGrabber {
    private final static Path DB_PROPERTIES = FileSystems.getDefault().getPath(
            "src", "test", "resources", "database", "testDatabase.properties");
    private final static Path API_LOG = FileSystems.getDefault().getPath(
            "src", "main", "resources", "database", "successfulApiResponses.txt");
    private final static Path COUNTRIES_TXT = FileSystems.getDefault()
            .getPath("src", "main", "resources", "countries.txt");

    public static void main(String[] args) {
        try {


            File file = new File(API_LOG.toString());
            if(!file.exists()) {
                if(!file.createNewFile()) {
                    System.out.println("File could not be created.");
                    return;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            DSCreator ds = new DSCreator(DB_PROPERTIES);
            PSQLDatasetWriter dw = new PSQLDatasetWriter(ds.getDataSourceTradeDB());
            PSQLCountryWriter cw = new PSQLCountryWriter(ds.getDataSourceTradeDB());


            List<String[]> countriesTest = getCountries();
            addCountriesToDB(cw, countriesTest);
            List<Integer> periods = getPeriods();

            List<Integer> countries = Arrays.asList(139,528);

            for(Integer country : countries) {
                ComtradeParametersRequest request = new ComtradeParametersRequest('M', country, 201504, 643
                        , new String[]{"TOTAL", "AG2"},
                        10000);

                APICall apiCall = new APICall(request);
                String jsonRequest = apiCall.call();

                if(jsonRequest == null) {

                } else {
                    ComtradeResponse comtradeResponse = parseResponse(jsonRequest);

                    if(comtradeResponse != null && comtradeResponse.isValid()) {
                        // write log
                        bw.write(LocalDateTime.now() + ": " + country);
                        bw.newLine();

                        // write to Database
                        for(Dataset dataset : comtradeResponse.getDatasets()) {
                            dw.addDataset(dataset);
                        }
                    }
                    System.out.println(comtradeResponse);
                }
            }
            bw.close();
        } catch (IOException e) {
        }

//        String jsonAvailability = apiCall.call();
//
//        System.out.println(jsonAvailability);
    }

    private static List<String[]> getCountries() throws IOException {
        List<String[]> countries = new ArrayList<>();
        File file = new File(COUNTRIES_TXT.toString());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            if(line.charAt(0) != '#') {
                String[] country = line.split("_");
                countries.add(country);
            }
        }
        return countries;
    }

    private static void addCountriesToDB(PSQLCountryWriter cw, List<String[]> countries) {
        for(String[] country : countries) {
            int code = Integer.parseInt(country[0]);
            String name = country[1];
            String abbr = country[2];

            cw.addCountry(name, abbr, code);
        }
        cw.addCountry("Russian Federation", "RUS", 643);
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
            System.out.println("reading: " + json);
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
