<style>
.mark {
    color: orange;
}
</style>

<p>This template creates ref properties for each country from iso_codes.json. Only needed to change onMouseHover, 
onMouseLeave, onClick,... and their interactions</p>

<p>Start the template.html website in your favorite browser. Open the developer console and copy the outerHTML of 
<span class="mark">#worldMap</span>. Now paste it in WorldMap.tsx. It includes refs properties named after the iso 
codes of each country.</p> 

<p>In WorldMap.tsx replace the style property in the svg with a JSX reference (width and height need to be 100%). Then 
search and replace:<br></p>
<pre>
    'class' with 'className'
    '"{' with '{'
    '}"' with '}'
</pre>

<p>Now copy the innerHTML of <span class="mark">#refInit</span> via the dev tools in your browser and paste them in 
WorldMap.tsx somewhere above the return.</p>