package eu.donttradewithrussia.database.readAndWrite.country;

public interface CountryDataWriter {
    int addCountry(String name, String abbrev, int comtradeId);
    boolean removeCountry(String name);
    boolean removeCountry(int comtradeId);
}
