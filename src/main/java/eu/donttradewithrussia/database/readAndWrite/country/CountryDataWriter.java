package eu.donttradewithrussia.database.readAndWrite.country;

import eu.donttradewithrussia.database.parser.Country;

public interface CountryDataWriter {
    int addCountry(String name, String abbrev, int comtradeId);
    int addCountry(Country country);
    int insertCountry(Country country, int whereCountryID);
    boolean removeCountry(String name);
    boolean removeCountry(int comtradeId);
}
