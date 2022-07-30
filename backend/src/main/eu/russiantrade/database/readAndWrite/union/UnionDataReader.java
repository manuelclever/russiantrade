package russiantrade.database.readAndWrite.union;

public interface UnionDataReader {
    String getAll(String coalitionName);
    String getAll(int coalitionID);
}
