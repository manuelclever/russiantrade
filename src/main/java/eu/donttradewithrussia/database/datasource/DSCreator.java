package eu.donttradewithrussia.database.datasource;

import eu.donttradewithrussia.database.datasource.util.CreateUrl;
import eu.donttradewithrussia.database.querydesignations.DataDesignations;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DSCreator {
    private BasicDataSource dataSourceTradeDB;
    //    private  BasicDataSource testDataSourceTradeDB;
    private Properties properties;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    // before running, create directory with permissions for user postgres


    private final String DATABASE_SETTINGS_PATH;

    public DSCreator(Path databaseProperties) {
        this.DATABASE_SETTINGS_PATH = databaseProperties.toString();
    }

    public DataSource getDataSourceTradeDB() {
        if (dataSourceTradeDB == null) {

            try {
                properties = new Properties();
                InputStream inputStream = new FileInputStream(DATABASE_SETTINGS_PATH);
                properties.load(inputStream);

                BasicDataSource dataSourcePostgres = createDataSource("source_");
                createUserIfNotExist(dataSourcePostgres);
                createTableSpaceIfNotExist(dataSourcePostgres);
                createDatabaseIfNotExist(dataSourcePostgres);

                dataSourceTradeDB = new BasicDataSource();
                setDataSourceTradeDB(dataSourceTradeDB);
                createSchemaIfNotExist(dataSourceTradeDB);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataSourceTradeDB;
    }

    private BasicDataSource createDataSource(String type) throws IOException {
        BasicDataSource dataSourcePostgres = new BasicDataSource();
        setDataSource(dataSourcePostgres, type);
        return dataSourcePostgres;
    }

    private void setDataSource(BasicDataSource basicDS, String type) throws IOException {
        setDSConnection(basicDS, type);
        setDSPool(basicDS);
    }

    private void setDSConnection(BasicDataSource basicDS, String type) throws IOException {
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

    private String getUrl(String driverClassName, String serverName, String port, String database) throws IOException {
        String url = CreateUrl.get(driverClassName, serverName, port, database);
        if (url == null) {
            throw new IOException("Incorrect Database Url");
        }
        return url;
    }

    private BasicDataSource setDSPool(BasicDataSource basicDS) {
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

    private void createUserIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE USER " + properties.getProperty("user") +
                        " WITH ENCRYPTED PASSWORD '" + properties.getProperty("password") + "';";
        execute(basicDS, statement);
    }

    private void execute(BasicDataSource basicDS, String statement) {
        try (Connection con = basicDS.getConnection()) {
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

    public void createTableSpaceIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE TABLESPACE " + properties.getProperty("tablespace") + " LOCATION '" +
                        properties.getProperty("databasePath") + "';";
        executeAuto(basicDS, statement);
    }

    private void createDatabaseIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE DATABASE " + properties.getProperty("database") +
                        " OWNER " + properties.getProperty("user") +
                        " TABLESPACE " + properties.getProperty("tablespace") + ";";
        executeAuto(basicDS, statement);
    }

    private void executeAuto(BasicDataSource basicDS, String statement) {
        try (Connection con = basicDS.getConnection()) {
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

    private void printDatasource(BasicDataSource basicDS) {
        System.out.println("user=" + basicDS.getUsername() + ", passwort=" + basicDS.getPassword() + ", url=" + basicDS.getUrl());
    }

    private void setDataSourceTradeDB(BasicDataSource basicDS) throws IOException {
        setDSConnection(basicDS, "");
        setDSPool(basicDS);
    }

    public void createSchemaIfNotExist(BasicDataSource basicDS) {
        printDatasource(basicDS);
        String statement =
                "CREATE SCHEMA " + properties.getProperty("schema") +
                        " AUTHORIZATION " + properties.getProperty("user") + "\n" +
                        "CREATE TABLE " + DataDesignations.COALITION + "(\n" +
                        DataDesignations.COALITION_ID + " serial PRIMARY KEY,\n" +
                        DataDesignations.COALITION_NAME + " text NOT NULL)\n" +
                        "CREATE TABLE " + DataDesignations.COUNTRY + "(\n" +
                        DataDesignations.COUNTRY_ID + " serial PRIMARY KEY,\n" +
                        DataDesignations.COUNTRY_NAME + " text NOT NULL,\n" +
                        DataDesignations.COUNTRY_ABBREV + " text NOT NULL)\n" +
//                        DataDesignations.COUNTRY_COMTRADE_ID + " smallint )\n" +
                        "CREATE TABLE " + DataDesignations.UNION + "(\n" +
                        DataDesignations.UNION_ID + " serial PRIMARY KEY,\n" +
                        DataDesignations.COALITION_ID + " int REFERENCES " + DataDesignations.COALITION + ",\n" +
                        DataDesignations.COUNTRY_ID + " int REFERENCES " + DataDesignations.COUNTRY + " )\n" +
                        "CREATE TABLE " + DataDesignations.MONTHLY_TRADE + "(\n" +
                        DataDesignations.MONTHLY_TRADE_ID + " serial PRIMARY KEY,\n" +
                        DataDesignations.PERIOD + " int NOT NULL,\n" +
                        DataDesignations.MONTHLY_TRADE_FLOW + " text NOT NULL,\n" +
                        DataDesignations.MONTHLY_TRADE_REPORTER + " int REFERENCES " + DataDesignations.COUNTRY + "," + "\n" +
                        DataDesignations.MONTHLY_TRADE_PARTNER + " int REFERENCES " + DataDesignations.COUNTRY + ",\n" +
                        DataDesignations.MONTHLY_TRADE_COMMODITY_CODE + " text NOT NULL,\n" +
                        DataDesignations.MONTHLY_TRADE_COMMODITY_DESC + " text,\n" +
                        DataDesignations.MONTHLY_TRADE_VALUE + " bigint)\n" +
                        "CREATE TABLE " + DataDesignations.CUSTOM_MONTHLY_TRADE + "(\n" +
                        DataDesignations.CUSTOM_MONTHLY_TRADE_ID + " serial PRIMARY KEY,\n" +
                        DataDesignations.PERIOD + " int NOT NULL,\n" +
                        DataDesignations.MONTHLY_TRADE_FLOW + " text NOT NULL,\n" +
                        DataDesignations.MONTHLY_TRADE_REPORTER + " int REFERENCES " + DataDesignations.COUNTRY + ",\n" +
                        DataDesignations.MONTHLY_TRADE_PARTNER + " int REFERENCES " + DataDesignations.COUNTRY + ",\n" +
                        DataDesignations.MONTHLY_TRADE_COMMODITY_CODE + " smallint NOT NULL,\n" +
                        DataDesignations.MONTHLY_TRADE_COMMODITY_DESC + " text,\n" +
                        DataDesignations.MONTHLY_TRADE_VALUE + " bigint);";

//        depreciated table sanction
//        "CREATE TABLE " + DataDesignations.TABLE_SANCTION + "(\n" +
//                DataDesignations.SANCTION_ID + " serial PRIMARY KEY,\n" +
//                DataDesignations.COALITION + " int REFERENCES " + DataDesignations.UNION + ",\n" +
//                DataDesignations.COUNTRY + " int REFERENCES " + DataDesignations.COUNTRY + ",\n" +
//                DataDesignations.SANCTION_PERIOD_START + " int NOT NULL,\n" +
//                DataDesignations.SANCTION_PERIOD_END + " int NOT NULL,\n" +
//                DataDesignations.SANCTION_CONTENT + " text NOT NULL )\n" +

//        references of monthly_trade
//        DataDesignations.MONTHLY_TRADE_SANCTION_GLOBAL + " int REFERENCES " + DataDesignations.SANCTION + ",\n" +
//        DataDesignations.MONTHLY_TRADE_SANCTION_LOCAL + " int REFERENCES " + DataDesignations.SANCTION + " )\n" +

//        references of custom_monthly_trade
//        DataDesignations.MONTHLY_TRADE_SANCTION_GLOBAL + " int REFERENCES " + DataDesignations.SANCTION + ",\n" +
//        DataDesignations.MONTHLY_TRADE_SANCTION_LOCAL + " int REFERENCES " + DataDesignations.SANCTION + " );";

        execute(basicDS, statement);
    }
}
