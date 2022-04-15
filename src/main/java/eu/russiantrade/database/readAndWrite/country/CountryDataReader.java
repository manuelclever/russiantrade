package eu.russiantrade.database.readAndWrite.country;

public interface CountryDataReader {
    String getCountry(String name);
    String getCountry(int comtradeId);
}
