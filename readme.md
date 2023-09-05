# russiantrade

This project was initiated during the Russian invasion of Ukraine with the aim of developing an interactive visualization of Russian global trade regarding the sanctions. Data sourced from https://comtrade.un.org/.

The backend is completed, but the frontend remains unfinished.

Data is regularly retrieved via a cronjob from the Comtrade database and stored in a local PostgreSQL instance. Another cronjob periodically checks for new data in the Comtrade database and updates the local database accordingly.

The frontend currently features an interactive map for visualizing countries, as well as templates for representing the data.

## Installation

### Add a new Country
Countries to download data into the database for are saved in [src/main/resources/countries.txt](https://github.com/manuelclever/russiantrade/blob/master/src/main/resources/countries.txt). After adding another country the next cronjob execution will download that country's data to the database. For the country to appear in the dropdown menu, execute [/webapp/WEB-INF/classes/eu/russiantrade/util/ConvertCountriesForWeb.class](https://github.com/manuelclever/russiantrade/blob/master/src/main/eu/russiantrade/util/ConvertCountriesForWeb.java) and rebuild the react webapp.

## Change something about the svg world map
For more information consult [src/webapp/webpage/src/components/worldMap/manualRefCreation/createRefs.md](https://github.com/manuelclever/russiantrade/blob/master/src/webapp/webpage/src/components/worldMap/manualRefCreation/createRefs.md)
