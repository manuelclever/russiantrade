package eu.donttradewithrussia.database.readAndWrite.coalition;

public interface CoalitionDataWriter {
    int addGroup(String name);
    boolean removeGroup(String name);
    boolean removeGroup(int groupID);
}
