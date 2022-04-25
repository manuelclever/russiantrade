import isoCodes from "./iso_codes.json" assert {type: "json"};

var select = null;
isoCodes.forEach(countryJson => {
    const iso = countryJson.Code.toLowerCase();
    const country = document.getElementsByClassName(iso);
    var countryEdited = null;


    if(country != null && iso != "ru" && iso != "ua") {

        const exceptions = getExceptions(iso);
        if(exceptions != null) {
            exceptions.forEach(exIso => {
                var exCountry = document.getElementsByClassName(exIso);
                countryEdited = subtract(country, exCountry);
            });
        } else {
            countryEdited = country;
        }
    
        for(var i = 0; i < countryEdited.length; i++) {
            countryEdited[i].addEventListener("mouseover", function() {
                if(select == null) {
                    fill(iso, countryEdited);
                }
            });
            countryEdited[i].addEventListener("mouseleave", function() {
                if(select == null) {
                    unfill(countryEdited)
                }
            });
            countryEdited[i].addEventListener("click", function() {actionOnClick(countryEdited)});

        }
    }
});
const sea = document.getElementsByClassName("oceanxx");
for(var i = 0; i < sea.length; i++) {
    sea[i].addEventListener("click", function() {actionOnClick(sea)});
            
}

function fill(iso, country) {
    document.getElementById("curCountry").innerHTML = iso;

    for(var i = 0; i < country.length; i++) {
        country[i].style = "fill:#005bbb";
    }
}

function unfill(country) {
    document.getElementById("curCountry").innerHTML = "World";

    for(var i = 0; i < country.length; i++) {
        country[i].style = "fill:#e0e0e0";
    }
}

function actionOnClick(country) {
    // console.log(country[0].firstElementChild.innerHTML);
    // console.log(country);
    if(select == null) {
        fill(null, country);
        select = country;
    } else {
        if(select != sea) {
            unfill(select);
        }

        if(country == sea) {
            select = null;
        } else {
            fill(null, country);
            select = country;
        }
    }
}

function getExceptions(iso) {
    if (iso == "rs") { //serbia and kosovo
        return ["xk"];
    } else if (iso == "ru") { //russia and ukraine
        return ["ua"];
    } else if(iso == "il") { //israel and palestine
        return ["ps"];
    } else if(iso == "cn") { //china und taiwan
        return ["tw"];
    }
    return null;
}

function subtract(c1, c2) {
    var countryEdited = [];

    for(var i = 0; i < c1.length; i++) {
        var push = true;

        for(var j = 0; j < c2.length; j++) {

            if(c1[i].outerHTML.localeCompare(c2[j].outerHTML) == 0) {
                push = false;
                break;
            }
        }

        if(push) {
            countryEdited.push(c1[i]);
        }
    }
    return countryEdited;
}