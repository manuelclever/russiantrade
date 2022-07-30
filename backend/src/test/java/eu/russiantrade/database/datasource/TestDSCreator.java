package java.eu.russiantrade.database.datasource;

import eu.russiantrade.database.datasource.DSCreator;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TestDSCreator {

    @Test
    void create() {
        Path path = FileSystems
                .getDefault()
                .getPath("backend", "src", "main", "resources", "database", "testDatabase.properties")
                .toAbsolutePath();
        DSCreator dsCreator = new DSCreator(path);
        dsCreator.getDataSourceTradeDB();
        Assertions.assertTrue(true);
    }
}
