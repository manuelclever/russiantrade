package eu.donttradewithrussia.database.readAndWrite.sanction_deprecated;

public interface SanctionDataReader {
    String getSanction(int countryID, int period);
}
