package eu.russiantrade.database.readAndWrite;

import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.coalition.PSQLCoalitionReader;
import eu.russiantrade.database.readAndWrite.coalition.PSQLCoalitionWriter;
import eu.russiantrade.database.readAndWrite.country.PSQLCountryReader;
import eu.russiantrade.database.readAndWrite.country.PSQLCountryWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TestDBWrite {
    private static final String PATH = FileSystems.getDefault()
            .getPath("src", "test", "resources", "database", "testDatabase.properties")
            .toAbsolutePath().toString();
    private static DataSource dataSource;

    @BeforeAll
    static void establishConnection() {
        DSCreator dsCreator = new DSCreator(PATH);
        try {
            dataSource = dsCreator.getDataSourceTradeDBJava();
        } catch (IOException | NamingException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void removeDBEntries() {
        removeCountries();
        removeCoalitions();
    }

    static void removeCountries() {
        PSQLCountryWriter countryWriter = new PSQLCountryWriter(dataSource);
        PSQLCountryReader countryReader = new PSQLCountryReader(dataSource);
        countries().forEach(arguments -> countryWriter.removeCountry((int) arguments.get()[2]));
        countries().forEach(arguments -> {
            if(countryReader.getCountry((int) arguments.get()[2]) != null) {
                Assertions.fail("Country " + arguments.get()[0] + " wasn't removed after running tests.");
            }
        });
    }
    
    static void removeCoalitions() {
        PSQLCoalitionWriter coalitionWriter = new PSQLCoalitionWriter(dataSource);
        PSQLCoalitionReader coalitionReader = new PSQLCoalitionReader(dataSource);
        coalitions().forEach(arguments -> coalitionWriter.removeCoalition((String) arguments.get()[0]));
        coalitions().forEach(arguments -> {
            if(coalitionReader.getCoalition((String) arguments.get()[0]) != null) {
                Assertions.fail("Coalition " + arguments.get()[0] + " wasn't removed after running tests.");
            }
        });
    }

    @ParameterizedTest
    @MethodSource("countries")
    void readAndWriteCountry(String country, String abbr, int id, String expected) {
        PSQLCountryWriter writer = new PSQLCountryWriter(dataSource);
        writer.addCountry(country, abbr, id);

        PSQLCountryReader reader = new PSQLCountryReader(dataSource);
        // getCountry could return several results, so string is in "array" brackets
        String c1 = reader.getCountry(country).replace("[","").replace("]","");
        String c2 = reader.getCountry(id);      // returns only on result, cause id is unique

        if(c1.equals(c2)) {
            Assertions.assertEquals(expected, c2);
        } else {
            Assertions.fail();
        }
    }

    private static Stream<Arguments> countries() {
        return Stream.of(
                Arguments.of("Spain","esp",724,"{\"country_id\":724,\"name\":\"Spain\",\"abbrev\":\"esp\"}"),
                Arguments.of("Slovakia","svk",210,"{\"country_id\":210,\"name\":\"Slovakia\",\"abbrev\":\"svk\"}"));
    }

    @ParameterizedTest
    @MethodSource("coalitions")
    void writeCoalition(String name) {
        PSQLCoalitionWriter writer = new PSQLCoalitionWriter(dataSource);
        writer.addCoalition(name);

        PSQLCoalitionReader reader = new PSQLCoalitionReader(dataSource);
        String c1 = reader.getCoalition(name); // returns only on result, cause name is unique
        
        String regex = "\\{\"coalition_id\":[0-9]+,\"coalition\":\"" + name + "\"\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(c1);
        
        if(matcher.matches()) {
            Assertions.assertTrue(true);
        } else {
            Assertions.fail();
        }
    }

    private static Stream<Arguments> coalitions() {
        return Stream.of(
                Arguments.of("NATO"),
                Arguments.of("Europe"));
    }

    @ParameterizedTest
    @MethodSource("countriesInCoalition")
    void writeCountryToCoalition() {

    }

    private static Stream<Arguments> countriesInCoalition() {
        return Stream.of(
                Arguments.of(

                ));
    }

    @ParameterizedTest
    @MethodSource("monthlyTradeDatasets")
    void writeMonthlyTradeDataset() {

    }

    private static Stream<Arguments> monthlyTradeDatasets() {
        return Stream.of(
                Arguments.of(

                ));
    }
}
