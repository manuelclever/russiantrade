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
            "src", "main", "resources", "database", "api.log");
    private static final Path COUNTRIES_TXT = FileSystems.getDefault()
            .getPath("src", "main", "resources", "countries.txt");
    public static final int RUSSIA = 643;

    private static DSCreator dsC;
    private static BufferedWriter logWriter;

    public static void main(String[] args) {
        try {
            dsC = new DSCreator(DB_PROPERTIES);
            logWriter = new BufferedWriter(new FileWriter(createAPILog(), true));

            List<String[]> countries = getCountries();
            addCountriesToDBIfNotExist(countries);
            List<Integer> periods = getPeriods();

            List<Integer> countriesTemp = Arrays.asList(139,528);

            for(Integer country : countriesTemp) {
                int period = 201504; //temp

                if(entryDoesntExist(country, period)) {
                    ComtradeResponse comtradeResponse = apiCall(country, period);

                    if (comtradeResponse != null && comtradeResponse.isValid()) {
                        addComtradeResponseToDB(comtradeResponse, country, period);
                    }
                }
            }
            logWriter.close();
        } catch (IOException e) {
        }
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

    private static boolean entryDoesntExist(int country, int period) {
        PSQLDatasetReader dr = new PSQLDatasetReader(dsC.getDataSourceTradeDB());
        return dr.getDatasets(country, RUSSIA, period) == null;
    }

    private static ComtradeResponse apiCall(int country, int period) {
        ComtradeParametersRequest request = new ComtradeParametersRequest('M', country, period, RUSSIA
                , new String[]{"TOTAL", "AG2"},
                10000);

        APICall apiCall = new APICall(request);
        String jsonRequest = apiCall.call();

        if (jsonRequest != null) {
            return ComtradeParser.parseResponse(jsonRequest);
        } return null;
    }

    private static void addComtradeResponseToDB(ComtradeResponse comtradeResponse, int country, int period) throws IOException {

        logWriter.write(LocalDateTime.now() + ": " + country + ", " + period);
        logWriter.newLine();

        PSQLDatasetWriter dw = new PSQLDatasetWriter(dsC.getDataSourceTradeDB());
        for (Dataset dataset : comtradeResponse.getDatasets()) {
            dw.addDataset(dataset);
        }
        System.out.println(comtradeResponse);
    }

    private static File createAPILog() throws IOException {
        File file = new File(API_LOG.toString());
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException();
            }
        }
        return file;
    }

    private static void addCountriesToDBIfNotExist(List<String[]> countries) {
        PSQLCountryReader cr = new PSQLCountryReader(dsC.getDataSourceTradeDB());
        PSQLCountryWriter cw = new PSQLCountryWriter(dsC.getDataSourceTradeDB());

        for(String[] countryArr : countries) {
            addCountryToDBIfNotExist(cr, cw, new Country(Integer.parseInt(countryArr[0]), countryArr[1], countryArr[2]));
        }
        addCountryToDBIfNotExist(cr, cw, new Country(RUSSIA, "Russian Federation", "RUS"));
    }

    private static void addCountryToDBIfNotExist(PSQLCountryReader cr, PSQLCountryWriter cw, Country country) {
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
