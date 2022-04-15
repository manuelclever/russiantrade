package eu.russiantrade.database.readAndWrite;

import eu.russiantrade.api.comtrade.parser.Dataset;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.coalition.PSQLCoalitionWriter;
import eu.russiantrade.database.readAndWrite.country.PSQLCountryWriter;
import eu.russiantrade.database.readAndWrite.dataset.PSQLDatasetWriter;
import eu.russiantrade.database.readAndWrite.union.PSQLUnionWriter;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ReadAndWrite {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("src", "test", "resources", "database", "testDatabase.properties");
        DSCreator dsCreator = new DSCreator(path.toAbsolutePath());

        PSQLCountryWriter psqlCountryWriter = new PSQLCountryWriter(dsCreator.getDataSourceTradeDB());
        PSQLCoalitionWriter psqlCoalitionWriter = new PSQLCoalitionWriter(dsCreator.getDataSourceTradeDB());
        PSQLUnionWriter psqlUnionWriter = new PSQLUnionWriter(dsCreator.getDataSourceTradeDB());
        PSQLDatasetWriter psqlDatasetWriter = new PSQLDatasetWriter(dsCreator.getDataSourceTradeDB());

        psqlCountryWriter.addCountry("Denmark", "dk", 208);
        psqlCountryWriter.addCountry("France", "fr", 89);
        psqlCoalitionWriter.addCoalition("NATO");

        Dataset dataset = new Dataset();
        dataset.setPeriod(102021);
        dataset.setReporterCode(208);
        dataset.setPartnerCode(89);
        dataset.setTradeFlowCode(1);
        dataset.setCommodityCode("All");
        dataset.setTradeValue(20000000000L);

        psqlDatasetWriter.addDataset(dataset);

    }
}
