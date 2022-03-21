package eu.donttradewithrussia.api.comtrade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeParser;
import eu.donttradewithrussia.api.comtrade.parser.ComtradeResponse;
import eu.donttradewithrussia.api.comtrade.parser.Dataset;
import eu.donttradewithrussia.database.datasource.DSCreator;
import eu.donttradewithrussia.database.parser.Country;
import eu.donttradewithrussia.database.readAndWrite.country.PSQLCountryReader;
import eu.donttradewithrussia.database.readAndWrite.country.PSQLCountryWriter;
import eu.donttradewithrussia.database.readAndWrite.dataset.PSQLDatasetReader;
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
    private static final Path DB_PROPERTIES = FileSystems.getDefault().getPath(
            "src", "test", "resources", "database", "testDatabase.properties");
    private  static final Path API_LOG = FileSystems.getDefault().getPath(
            "src", "main", "resources", "database", "successfulApiResponses.txt");
    private static final Path COUNTRIES_TXT = FileSystems.getDefault()
            .getPath("src", "main", "resources", "countries.txt");
    public static final int RUSSIA = 643;

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
            PSQLDatasetReader dr = new PSQLDatasetReader(ds.getDataSourceTradeDB());
            PSQLDatasetWriter dw = new PSQLDatasetWriter(ds.getDataSourceTradeDB());
            PSQLCountryReader cr = new PSQLCountryReader(ds.getDataSourceTradeDB());
            PSQLCountryWriter cw = new PSQLCountryWriter(ds.getDataSourceTradeDB());

            List<String[]> countriesTest = getCountries();
            addCountriesToDB(cr, cw, countriesTest);
            List<Integer> periods = getPeriods();

            List<Integer> countries = Arrays.asList(139,528);

            for(Integer country : countries) {
                ComtradeParametersRequest request = new ComtradeParametersRequest('M', country, 201504, RUSSIA
                        , new String[]{"TOTAL", "AG2"},
                        10000);

                //check before requesting
                String result = dr.getDataset(country, RUSSIA, 201504);

                APICall apiCall = new APICall(request);
                String jsonRequest = apiCall.call();

                if(jsonRequest != null) {
                    ComtradeResponse comtradeResponse = ComtradeParser.parseResponse(jsonRequest);

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

    private static void addCountriesToDB(PSQLCountryReader cr, PSQLCountryWriter cw, List<String[]> countries) {
        for(String[] countryArr : countries) {
            addCountryToDB(cr, cw, new Country(Integer.parseInt(countryArr[0]), countryArr[1], countryArr[2]));
        }
        addCountryToDB(cr, cw, new Country(RUSSIA, "Russian Federation", "RUS"));
    }

    private static void addCountryToDB(PSQLCountryReader cr, PSQLCountryWriter cw, Country country) {
        try {
            Country countryDB = new ObjectMapper().readValue(cr.getCountry(
                    country.getCountry_id()), Country.class);

            if (countryDB == null) {
                cw.addCountry(country);
            }
        } catch (JsonMappingException e) {
            cw.addCountry(country);
        } catch (JsonProcessingException ignore) {}
    }

    private static List<Integer> getPeriods() {
        int year = 2010;
        int month = 1;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate now = LocalDate.now();
        String end = dtf.format(now);

        List<Integer> periods = new ArrayList<>();
        String period = year + String.format("%02d", month);
        while(Integer.parseInt(period) <= Integer.parseInt(end)) {
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
}
