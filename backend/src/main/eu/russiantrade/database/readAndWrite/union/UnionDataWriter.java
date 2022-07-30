package russiantrade.database.readAndWrite.union;

public interface UnionDataWriter {
    int addConnection(int coalitionID, int countryID);
    boolean removeConnection(int coalitionID, int countryID);
}
