package eu.russiantrade.database.datasource;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.FileSystems;

public class TestDSCreator {
    private static final String PATH = FileSystems.getDefault()
            .getPath("src", "test", "resources", "database", "testDatabase.properties")
            .toAbsolutePath().toString();

    @Test
    void initializeDatabase() {
        DSCreator dsCreator = new DSCreator(PATH);
        dsCreator.initializeDatabase();
        Assertions.assertTrue(true);
    }
}
