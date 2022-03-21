package eu.donttradewithrussia.database.parser;

public class Country {
    // all variable names have to equal DataDesignations of column in database
    private int country_id;
    private String name;
    private String abbrev;

    public Country() {
    }

    public Country(int country_id, String name, String abbrev) {
        this.country_id = country_id;
        this.name = name;
        this.abbrev = abbrev;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }
}
