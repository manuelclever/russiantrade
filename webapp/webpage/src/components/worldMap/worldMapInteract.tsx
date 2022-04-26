// const isoCodes = require("./temp/iso_codes.json");


export default function initialiseWorldMap() {
//     var select = null;

//     isoCodes.forEach(countryJson => {
//         const iso = countryJson.Code.toLowerCase();
//         const country = document.getElementsByClassName(iso);
//         var countryEdited = null;

//         if(country != null && country.length > 0 && iso != "ru" && iso != "ua") {
//             const countryName = document.getElementById(iso).childNodes[1].innerHTML;
            
//             const exceptions = getExceptions(iso);
//             if(exceptions != null) {
//                 exceptions.forEach(exIso => {
//                     var exCountry = document.getElementsByClassName(exIso);
//                     countryEdited = subtract(country, exCountry);
//                 });
//             } else {
//                 countryEdited = country;
//             }
        
//             for(var i = 0; i < countryEdited.length; i++) {
//                 countryEdited[i].addEventListener("mouseover", function() {
//                     if(select == null) {
//                         fill(countryName, countryEdited);
//                     }
//                 });
//                 countryEdited[i].addEventListener("mouseleave", function() {
//                     if(select == null) {
//                         unfill(countryEdited)
//                     }
//                 });
//                 countryEdited[i].addEventListener("click", function() {actionOnClick(countryName, countryEdited)});

//             }
//         }
//     });
//     const sea = document.getElementsByClassName("oceanxx");
//     for(var i = 0; i < sea.length; i++) {
//         sea[i].addEventListener("click", function() {actionOnClick(null, sea)});
                
//     }
// }

// function fill(name, country) {
//     document.getElementById("curCountry").innerHTML = name;

//     for(var i = 0; i < country.length; i++) {
//         country[i].style = "fill:#005bbb";
//     }
// }

// function unfill(country) {
//     document.getElementById("curCountry").innerHTML = "World";

//     for(var i = 0; i < country.length; i++) {
//         country[i].style = "fill:#e0e0e0";
//     }
// }

// function actionOnClick(name, country) {

//     if(select == null && country != sea) {
//         fill(name, country);
//         select = country;
//     } else {
//         if(select != null && select != sea) {
//             unfill(select);
//         }
//         if(country == sea) {
//             select = null;
//         } else {
//             fill(name, country);
//             select = country;
//         }
//     }
// }

// function getExceptions(iso) {
//     if (iso == "rs") { //serbia and kosovo
//         return ["xk"];
//     } else if (iso == "ru") { //russia and ukraine
//         return ["ua"];
//     } else if(iso == "il") { //israel and palestine
//         return ["ps"];
//     } else if(iso == "cn") { //china und taiwan
//         return ["tw"];
//     } else if(iso == "ma") { //morocco and western sahara
//         return ["eh"];
//     }
//     return null;

//     // removed from json with iso codes
//     // {"Code": "GF", "Name": "French Guiana"},
// }

// function subtract(c1, c2) {
//     var countryEdited = [];

//     for(var i = 0; i < c1.length; i++) {
//         var push = true;

//         for(var j = 0; j < c2.length; j++) {

//             if(c1[i].outerHTML.localeCompare(c2[j].outerHTML) == 0) {
//                 push = false;
//                 break;
//             }
//         }

//         if(push) {
//             countryEdited.push(c1[i]);
//         }
//     }
//     return countryEdited;
}