package eu.donttradewithrussia.database.readAndWrite.country;

public interface CountryDataReader {
    String getCountry(String name);
    String getCountry(int comtradeId);
}
