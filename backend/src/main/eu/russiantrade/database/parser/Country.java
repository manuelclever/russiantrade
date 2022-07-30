package russiantrade.database.parser;

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

    public int getCountryID() {
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

    @Override
    public int hashCode() {
        return Integer.hashCode(country_id) * 4 + name.hashCode() * 13 + abbrev.hashCode() *17;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == this.getClass()) {
            return obj.hashCode() == this.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + country_id + ", " + name + ", " + abbrev + "]";
    }
}
