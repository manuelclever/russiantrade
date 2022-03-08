package eu.donttradewithrussia.database.readAndWrite.sanction_deprecated;

public interface SanctionDataWriter {
    int addSanctionGlobal(int groupID, int periodStart, String content);
    int addSanctionGlobal(int groupID, int periodStart, int periodEnd, String content);
    int addSanctionLocal(int countryID, int periodStart, String content);
    int addSanctionLocal(int countryID, int periodStart, int periodEnd, String content);
    boolean removeSanction(int sanctionID);
}
