package eu.russiantrade.database.datasource;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.nio.file.FileSystems;
import java.sql.Connection;

public class TestDatabaseConnection {
    private static final String PATH = FileSystems.getDefault()
            .getPath("src", "test", "resources", "database", "testDatabase.properties")
            .toAbsolutePath().toString();

    @Test
    void establishConnection() {
        DSCreator dsCreator = new DSCreator(PATH);
        try {
            DataSource dataSource = dsCreator.getDataSourceTradeDBJava();
            Connection con = dataSource.getConnection();
            Assertions.assertEquals("tradetest_db", con.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
