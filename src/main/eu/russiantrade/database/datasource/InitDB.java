package eu.russiantrade.database.datasource;

import java.nio.file.FileSystems;

public class InitDB {
    private static final String DB_PROPERTIES = FileSystems.getDefault()
            .getPath("src", "main", "resources", "database", "database.properties")
            .toAbsolutePath().toString();

    public static void main(String[] args) {
        DSCreator dsCreator = new DSCreator(DB_PROPERTIES);
        dsCreator.initializeDatabase();
    }
}
