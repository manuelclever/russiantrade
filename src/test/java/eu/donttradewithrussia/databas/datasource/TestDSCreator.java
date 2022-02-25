package eu.donttradewithrussia.databas.datasource;

import eu.donttradewithrussia.database.datasource.DSCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TestDSCreator {

    @Test
    void create() {
        Path path = FileSystems
                .getDefault()
                .getPath("src", "test", "resources", "database", "testDatabase.properties")
                .toAbsolutePath();
        DSCreator dsCreator = new DSCreator(path);
        dsCreator.getDataSourceTradeDB();
        Assertions.assertTrue(true);
    }
}
