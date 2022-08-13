package java.eu.russiantrade.database.datasource;

import eu.russiantrade.database.datasource.DSCreator;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TestDSCreator {

    @Test
    void create() {
        Path path = FileSystems
                .getDefault()
                .getPath("backend", "src", "main", "resources", "database", "testDatabase.properties")
                .toAbsolutePath();
        DSCreator dsCreator = new DSCreator(path.toAbsolutePath().toString());
        try {
            dsCreator.getDataSourceTradeDBJava();
            Assertions.assertTrue(true);
        } catch (IOException | NamingException e) {
            Assertions.fail();
        }
    }
}
