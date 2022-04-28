<style>
.path {
    color: orange;
}
</style>

<h1>Installation</h1>

<h2>Add a new Country</h2>
<p>Countries to download data into the database for are saved in <span class="path">.
/backend/src/main/resources/countries.txt</span>. After adding another country the next cronjob execution will 
download that country's data to the database. For the country to appear in the dropdown menu, execute <span 
class="path">./webapp/WEB-INF/classes/eu/russiantrade/util/ConvertCountriesForWeb.class</span> and rebuild the react 
webapp.
</p>

<h2>Change something about the svg world map</h2>
<p>For more information consult <span class="path">.
/webapp/webpage/src/components/worldMap/manualRefCreation/createRefs.md</span></p>