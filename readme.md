# russiantrade

This project was developed in the context of the Russian invasion of Ukraine. The goal was to create an interactive representation of Russian global trade data using [https://comtrade.un.org/](https://comtrade.un.org/).

Backend finished. Frontend unfinished, because personal stuff happened and i had no time anymore.

Data is being pulled  via cronjob from the comtrade database to a local postgresql instance. A cronjob checks if new data has been added to the comtrade database and if so updates the local database.

Frontend only has an interactive map visualising the countries and some templates for the unfinished datea representation.

## Installation

### Add a new Country
Countries to download data into the database for are saved in [src/main/resources/countries.txt](https://github.com/manuelclever/russiantrade/blob/master/src/main/resources/countries.txt). After adding another country the next cronjob execution will download that country's data to the database. For the country to appear in the dropdown menu, execute [/webapp/WEB-INF/classes/eu/russiantrade/util/ConvertCountriesForWeb.class](https://github.com/manuelclever/russiantrade/blob/master/src/main/eu/russiantrade/util/ConvertCountriesForWeb.java) and rebuild the react webapp.

## Change something about the svg world map
For more information consult [src/webapp/webpage/src/components/worldMap/manualRefCreation/createRefs.md](https://github.com/manuelclever/russiantrade/blob/master/src/webapp/webpage/src/components/worldMap/manualRefCreation/createRefs.md)
