package eu.donttradewithrussia.database.datasource;

import eu.donttradewithrussia.database.datasource.util.CreateUrl;
import eu.donttradewithrussia.database.query.DataDesignations;
import eu.donttradewithrussia.database.query.PSQL.PSQLQueries;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DSCreator {
    private static BasicDataSource dataSourceTradeDB;
//    private static BasicDataSource testDataSourceTradeDB;
    private static Properties properties;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    private static final String DATABASE_PATH = "/data/trade_db";
    // before running, create directory with permissions for user postgres
    private static final String DATABASE_SETTINGS_PATH = FileSystems
            .getDefault()
            .getPath("src", "main", "resources", "database")
            .toAbsolutePath()
            .toString();
    private static final String TABLESPACE = "trade";

    public static DataSource getDataSource() {
        if(dataSourceTradeDB == null) {

            try {
                dataSourceTradeDB = new BasicDataSource();
//                testDataSourceTradeDB = new BasicDataSource();
                properties = new Properties();
                InputStream inputStream = new FileInputStream(
                        DATABASE_SETTINGS_PATH + FileSystems.getDefault().getSeparator() + "database.properties");
                properties.load(inputStream);

                BasicDataSource dataSourcePostgres = createDataSource("source_");
                createUserIfNotExist(dataSourcePostgres);
                createTableSpaceIfNotExist(dataSourcePostgres);

                createDatabaseIfNotExist(dataSourcePostgres);
//                createTestDatabaseIfNotExist(dataSourcePostgres);

                setDataSource(dataSourceTradeDB);
//                setDataSource(testDataSourceTradeDB, "test_");

//                createSchemaIfNotExist(testDataSourceTradeDB);
                createSchemaIfNotExist(dataSourceTradeDB);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return dataSourceTradeDB;
    }

    private static void printDatasource(BasicDataSource basicDS) {
        System.out.println("user=" + basicDS.getUsername() + ", passwort=" + basicDS.getPassword() + ", url=" + basicDS.getUrl() );
    }

//    public static DataSource getTestDataSource() {
//        if(dataSourceTradeDB == null) {
//            getDataSource();
//        }
//        return testDataSourceTradeDB;
//    }

    private static BasicDataSource createDataSource(String type) throws IOException {
        BasicDataSource dataSourcePostgres = new BasicDataSource();
        setDataSource(dataSourcePostgres, type);
        return dataSourcePostgres;
    }

    private static void setDataSource(BasicDataSource basicDS) throws IOException{
        setDSConnection(basicDS, "");
        setDSPool(basicDS);
    }

    private static void setDataSource(BasicDataSource basicDS, String type) throws IOException{
        setDSConnection(basicDS, type);
        setDSPool(basicDS);
    }

    private static void setDSConnection(BasicDataSource basicDS, String type) throws IOException {
        basicDS.setDriverClassName(properties.getProperty("driverClass"));
        basicDS.setUsername(properties.getProperty(type + "user"));
        basicDS.setPassword(properties.getProperty(type + "password"));
        basicDS.setUrl(getUrl(
                basicDS.getDriverClassName(),
                properties.getProperty("server"),
                properties.getProperty("port"),
                properties.getProperty(type + "database")));

        String tempProperty = properties.getProperty(type + "defaultAutoCommit");
        if (tempProperty != null) {
            basicDS.setDefaultAutoCommit(Boolean.parseBoolean(tempProperty));
        }
    }

    private static String getUrl(String driverClassName, String serverName, String port, String database) throws IOException {
        String url = CreateUrl.get(driverClassName, serverName, port, database);
        if (url == null) {
            throw new IOException("Incorrect Database Url");
        }
        return url;
    }

    private static BasicDataSource setDSPool(BasicDataSource basicDS) {
        String tempProperty = properties.getProperty("initialSize");
        if (tempProperty != null) {
            try {
                basicDS.setInitialSize(Integer.parseInt(tempProperty));
            } catch (NumberFormatException ignore) {
            }
        }

        tempProperty = properties.getProperty("maxTotal");
        if (tempProperty != null) {
            try {
                basicDS.setInitialSize(Integer.parseInt(tempProperty));
            } catch (NumberFormatException ignore) {
            }
        }

        tempProperty = properties.getProperty("maxIdle");
        if (tempProperty != null) {
            try {
                basicDS.setMaxIdle(Integer.parseInt(tempProperty));
            } catch (NumberFormatException ignore) {
            }
        }

        tempProperty = properties.getProperty("minIdle");
        if (tempProperty != null) {
            try {
                basicDS.setMinIdle(Integer.parseInt(tempProperty));
            } catch (NumberFormatException ignore) {
            }
        }

        tempProperty = properties.getProperty("maxWaitMillis");
        if (tempProperty != null) {
            try {
                basicDS.setMaxWaitMillis(Long.parseLong(tempProperty));
            } catch (NumberFormatException ignore) {
            }
        }
        return basicDS;
    }

    private static void createUserIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE USER " + properties.getProperty("user") +
                " WITH ENCRYPTED PASSWORD '" + properties.getProperty("password") + "';";
        execute(basicDS, statement);
    }

    private static void execute(BasicDataSource basicDS, String statement) {
        try(Connection con = basicDS.getConnection()) {
            Statement st = con.createStatement();
            System.out.println(statement);
            System.out.println("---");
            st.execute(statement);
            con.commit();
            st.close();
        } catch (SQLException e) {
            System.out.println(ANSI_YELLOW + e.getMessage() + ANSI_RESET);
        }
    }

    private static void executeAuto(BasicDataSource basicDS, String statement) {
        try(Connection con = basicDS.getConnection()) {
            con.setAutoCommit(true);
            Statement st = con.createStatement();
            System.out.println(statement);
            System.out.println("---");
            st.execute(statement);
            st.close();
        } catch (SQLException e) {
            System.out.println(ANSI_YELLOW + e.getMessage() + ANSI_RESET);
        }
    }

    public static void createTableSpaceIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement = "CREATE TABLESPACE " + TABLESPACE + " LOCATION '" + DATABASE_PATH + "';";
        executeAuto(basicDS, statement);
    }

    public static void createSchemaIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE SCHEMA " + properties.getProperty("schema") +
                        " AUTHORIZATION " + properties.getProperty("user") + "\n" +
                        "CREATE TABLE " + PSQLQueries.GROUP + "(\n" +
                            PSQLQueries.GROUP_ID +  " serial PRIMARY KEY,\n" +
                            PSQLQueries.GROUP_NAME + " text NOT NULL)\n" +
                        "CREATE TABLE " + PSQLQueries.COUNTRY + "(\n" +
                            PSQLQueries.COUNTRY_ID + " serial PRIMARY KEY,\n" +
                            PSQLQueries.COUNTRY_NAME + " text NOT NULL,\n" +
                            PSQLQueries.COUNTRY_ABBREV + " text NOT NULL,\n" +
                            PSQLQueries.COUNTRY_COMTRADE_CODE + " smallint )\n" +
                        "CREATE TABLE " +  PSQLQueries.UNION_GROUP_COUNTRY + "(\n" +
                            PSQLQueries.UNION_GROUP_COUNTRY_ID + " serial PRIMARY KEY,\n" +
                            PSQLQueries.GROUP_ID + " int REFERENCES " + PSQLQueries.TABLE_GROUP + ",\n" +
                            PSQLQueries.COUNTRY_ID + " int REFERENCES " + PSQLQueries.TABLE_COUNTRY + " )\n" +
                        "CREATE TABLE " + PSQLQueries.SANCTION +  "(\n" +
                            PSQLQueries.SANCTION_ID + " serial PRIMARY KEY,\n" +
                            PSQLQueries.SANCTION_DESIGNATION + " text NOT NULL,\n" +
                            PSQLQueries.SANCTION_CONTENT + " text NOT NULL )\n" +
                        "CREATE TABLE " + PSQLQueries.MONTHLY_TRADE + "(\n" +
                            PSQLQueries.MONTHLY_TRADE_ID + " serial PRIMARY KEY,\n" +
                            PSQLQueries.PERIOD + " int NOT NULL,\n" +
                            PSQLQueries.MONTHLY_TRADE_FLOW + " text NOT NULL,\n" +
                            PSQLQueries.MONTHLY_TRADE_REPORTER + " int REFERENCES " + PSQLQueries.TABLE_COUNTRY + ",\n" +
                            PSQLQueries.MONTHLY_TRADE_PARTNER + " int REFERENCES " + PSQLQueries.TABLE_COUNTRY + ",\n" +
                            PSQLQueries.MONTHLY_TRADE_COMMODITY_CODE + " smallint NOT NULL,\n" +
                            PSQLQueries.MONTHLY_TRADE_VALUE + " bigint,\n" +
                            PSQLQueries.MONTHLY_TRADE_SANCTION_GLOBAL + " int REFERENCES " + PSQLQueries.TABLE_SANCTION + ",\n" +
                            PSQLQueries.MONTHLY_TRADE_SANCTION_LOCAL + " int REFERENCES " + PSQLQueries.TABLE_SANCTION + " )\n" +
                        "CREATE TABLE " + PSQLQueries.CUSTOM_MONTHLY_TRADE + "(\n" +
                            PSQLQueries.CUSTOM_MONTHLY_TRADE_ID+ " serial PRIMARY KEY,\n" +
                            PSQLQueries.PERIOD + " int NOT NULL,\n" +
                            PSQLQueries.MONTHLY_TRADE_FLOW + " text NOT NULL,\n" +
                            PSQLQueries.MONTHLY_TRADE_REPORTER + " int REFERENCES " + PSQLQueries.TABLE_COUNTRY + ",\n" +
                            PSQLQueries.MONTHLY_TRADE_PARTNER + " int REFERENCES " + PSQLQueries.TABLE_COUNTRY + ",\n" +
                            PSQLQueries.MONTHLY_TRADE_COMMODITY_CODE + " smallint NOT NULL,\n" +
                            PSQLQueries.MONTHLY_TRADE_VALUE + " bigint,\n" +
                            PSQLQueries.MONTHLY_TRADE_SANCTION_GLOBAL + " int REFERENCES " + PSQLQueries.TABLE_SANCTION + ",\n" +
                            PSQLQueries.MONTHLY_TRADE_SANCTION_LOCAL + " int REFERENCES " + PSQLQueries.TABLE_SANCTION + " );";
        execute(basicDS, statement);
    }

    private static void createDatabaseIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE DATABASE " + properties.getProperty("database") +
                    " OWNER " + properties.getProperty("user") +
                    " TABLESPACE " + TABLESPACE + ";";
        executeAuto(basicDS, statement);
    }

//    public static void createTestDatabaseIfNotExist(BasicDataSource basicDS) {
//        printDatasource(basicDS);
//        String statement =
//                "CREATE DATABASE " + properties.getProperty("test_database") +
//                        " OWNER " + properties.getProperty("user") +
//                        " TABLESPACE " + TABLESPACE + ";";
//        executeAuto(basicDS, statement);
//    }
}
