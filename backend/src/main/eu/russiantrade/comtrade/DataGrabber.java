package eu.russiantrade.api.comtrade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.russiantrade.api.comtrade.parser.ComtradeParser;
import eu.russiantrade.api.comtrade.parser.ComtradeResponse;
import eu.russiantrade.api.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.parser.Country;
import eu.russiantrade.database.querydesignations.DataDesignations;
import eu.russiantrade.database.readAndWrite.country.PSQLCountryReader;
import eu.russiantrade.database.readAndWrite.country.PSQLCountryWriter;
import eu.russiantrade.database.readAndWrite.tradeData.PSQLTradeReader;
import eu.russiantrade.database.readAndWrite.tradeData.PSQLTradeWriter;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataGrabber {
    private static final Path DB_PROPERTIES = FileSystems.getDefault().getPath("backend", "src", "main", "resources",
            "database", "testDatabase.properties");
    private  static final Path API_LOG = FileSystems.getDefault().getPath(
            "backend", "src",  "main", "resources", "database", "api.log");
    private static final Path COUNTRIES_TXT = FileSystems.getDefault()
            .getPath("backend", "src",  "main", "resources", "countries.txt");

    public static final int REQUEST_DELAY = 1000;
    public static final int RATE_LIMIT_HOUR = 100;

    private static DSCreator dsC;
    private static BufferedWriter logWriter;

    public static void main(String[] args) {
        try {
            dsC = new DSCreator(DB_PROPERTIES);

            List<Country> countries = getCountries();
            addCountriesToDBIfNotExist(countries);
            List<Integer> periods = getPeriods();

            int c = 0;
            for(Country country : countries) {

                for(int period : periods) {
                    if (entryDoesntExist(country, period)) {
                        if(c >= RATE_LIMIT_HOUR) {
                            try {
                                Thread.sleep(60000);
                            } catch(InterruptedException e) {
                                writeToLog("Hour delay interrupted:\n" + e.getMessage());
                            }
                        }

                        try {
                            Thread.sleep(REQUEST_DELAY);
                            ComtradeResponse comtradeResponse = apiCall(country, period);
                            c++;

                            if (comtradeResponse != null && comtradeResponse.isValid()) {
                                System.out.println(comtradeResponse);
                                addComtradeResponseToDB(comtradeResponse, country, period);
                            }
                        } catch (InterruptedException e){
                            writeToLog("Minute delay interrupted:\n" + e.getMessage());
                        }
                    }
                }
            }
            logWriter.close();
        } catch (IOException ignore) {
        }
    }

    private static List<Country> getCountries() throws IOException {
        List<Country> countries = new ArrayList<>();
        File file = new File(COUNTRIES_TXT.toString());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            if(line.charAt(0) != '#') {
                String[] country = line.split("_");
                countries.add(new Country(Integer.parseInt(country[0]), country[1], country[2]));
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
        while(Integer.parseInt(period) <= Integer.parseInt(end)) {
            periods.add(Integer.parseInt(period));

            if(month < 12) {
                month++;
            } else {
                periods.add(year);
                year++;
                month = 1;
            }
            period = year + String.format("%02d", month);
        }
        return periods;
    }


    private static boolean entryDoesntExist(Country country, int period) {
        PSQLTradeReader dr = new PSQLTradeReader(dsC.getDataSourceTradeDB());
        System.out.println(country + " " + period + ":");
        List<TradeData> data = dr.getDatasets(country.getCountryID(), DataDesignations.RUSSIA, period);
        return data == null;
    }

    private static ComtradeResponse apiCall(Country country, int period) {
        char frequency = countDigits(period) == 4 ? 'A' : 'M';

        ComtradeParametersRequest request = new ComtradeParametersRequest(
                frequency, country.getCountryID(), period, DataDesignations.RUSSIA, new String[]{"TOTAL", "AG2"});

        APICall apiCall = new APICall(request);
        String jsonRequest = apiCall.call();

        if (jsonRequest != null) {
            return ComtradeParser.parseResponse(jsonRequest);
        } return null;
    }

    private static int countDigits(int n) {
        int temp = 1;
        int count = 0;

        while(temp <= n) {
            temp *= 10;
            count++;
        }
        return count;
    }


    private static void addComtradeResponseToDB(ComtradeResponse comtradeResponse, Country country, int period) throws IOException {
        writeToLog(country + ", " + period);

        PSQLTradeWriter dw = new PSQLTradeWriter(dsC.getDataSourceTradeDB());
        for (TradeData tradeData : comtradeResponse.getDatasets()) {
            dw.addDataset(tradeData);
        }
    }

    private static void writeToLog(String info) {
        try {
            if(logWriter == null) {
                logWriter = new BufferedWriter(new FileWriter(createAPILog(), true));
            }

            logWriter.write(LocalDateTime.now() + ": " + info);
            logWriter.newLine();
        } catch(IOException ignore) {}
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

    private static void addCountriesToDBIfNotExist(List<Country> countries) {
        PSQLCountryReader cr = new PSQLCountryReader(dsC.getDataSourceTradeDB());
        PSQLCountryWriter cw = new PSQLCountryWriter(dsC.getDataSourceTradeDB());

        for(Country country : countries) {
            addCountryToDBIfNotExist(cr, cw, country);
        }
        addCountryToDBIfNotExist(cr, cw, new Country(DataDesignations.RUSSIA, "Russian Federation", "RUS"));
    }

    private static void addCountryToDBIfNotExist(PSQLCountryReader cr, PSQLCountryWriter cw, Country country) {
        try {
            String dbReturn = cr.getCountry(country.getCountryID());

            if(dbReturn != null) {
                Country countryDB = new ObjectMapper().readValue(dbReturn, Country.class);

                if(!countryDB.equals(country)) {
                    cw.insertCountry(country, countryDB.getCountryID());
                }
            } else {
                cw.addCountry(country);
            }
        } catch (JsonMappingException e) {
            cw.addCountry(country);
        } catch (JsonProcessingException ignore) {}
    }
}
