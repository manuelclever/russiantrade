package eu.russiantrade.database.readAndWrite;

import eu.russiantrade.comtrade.parser.TradeData;
import eu.russiantrade.database.datasource.DSCreator;
import eu.russiantrade.database.readAndWrite.tradeData.PSQLTradeWriter;
import eu.russiantrade.database.readAndWrite.coalition.PSQLCoalitionWriter;
import eu.russiantrade.database.readAndWrite.country.PSQLCountryWriter;
import eu.russiantrade.database.readAndWrite.union.PSQLUnionWriter;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ReadAndWrite {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("backend", "src", "test", "",
                "database", "testDatabase.properties");
        DSCreator dsCreator = new DSCreator(path.toAbsolutePath());

        PSQLCountryWriter psqlCountryWriter = new PSQLCountryWriter(dsCreator.getDataSourceTradeDB());
        PSQLCoalitionWriter psqlCoalitionWriter = new PSQLCoalitionWriter(dsCreator.getDataSourceTradeDB());
        PSQLUnionWriter psqlUnionWriter = new PSQLUnionWriter(dsCreator.getDataSourceTradeDB());
        PSQLTradeWriter psqlDatasetWriter = new PSQLTradeWriter(dsCreator.getDataSourceTradeDB());

        psqlCountryWriter.addCountry("Denmark", "dk", 208);
        psqlCountryWriter.addCountry("France", "fr", 89);
        psqlCoalitionWriter.addCoalition("NATO");

        TradeData tradeData = new TradeData();
        tradeData.setPeriod(102021);
        tradeData.setReporterCode(208);
        tradeData.setPartnerCode(89);
        tradeData.setTradeFlowCode(1);
        tradeData.setCommodityCode("All");
        tradeData.setTradeValue(20000000000L);

        psqlDatasetWriter.addDataset(tradeData);

    }
}
