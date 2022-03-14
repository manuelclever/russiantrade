package eu.donttradewithrussia.database.readAndWrite.coalition;

public interface CoalitionDataWriter {
    int addCoalition(String name);
    boolean removeCoalition(String name);
    boolean removeCoalition(int groupID);
}
