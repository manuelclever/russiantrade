package eu.russiantrade.api.comtrade;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class APICall {
    private static final String URL_BASE = "https://comtradeapi.un.org/data/v1/get";
    private static final String URL_AVAILABILITY = "http://comtrade.un.org/api//refs/da/view?";
    private static final String URL_REQUEST = "http://comtrade.un.org/api/get?";

    //New-API: https://comtradeapi.un.org/data/v1/get/{typeCode}/{freqCode}/{clCode}
    // [?reporterCode]
    // [&period]
    // [&partnerCode]
    // [&partner2Code]
    // [&cmdCode]
    // [&flowCode]
    // [&customsCode]
    // [&motCode]
    // [&aggregateBy]
    // [&breakdownMode]
    // [&includeDesc]

    // Beispiel: GET https://comtradeapi.un.org/data/v1/get/C/A/HS?reporterCode=8&period=2010&partnerCode=643&flowCode=-1&customsCode=-1&motCode=-1&aggregateBy=cmdCode&breakdownMode=classic&includeDesc=false

    ComtradeAPIParameters comtradeAPIParameters;
    private StringBuilder requestUrl;

    public APICall(ComtradeAPIParameters comtradeAPIParametersRequest) {
        this.comtradeAPIParameters = comtradeAPIParametersRequest;
    }

    public String call() {
        System.out.println(getRequestUrl());
        HttpResponse<JsonNode> httpResponse = Unirest.get(getRequestUrl())
                .header("Ocp-Apim-Subscription-Key", "1468af1849604304a6328b7a70d7c4f5")
                .asJson();
        try {
            return httpResponse.getBody().toString();
        }catch (NullPointerException e) {
            return null;
        }
    }

    public static String call(String request) {
        System.out.println(URL_REQUEST + request);
        HttpResponse<JsonNode> httpResponse = Unirest.get(URL_REQUEST + request).asJson();
        return httpResponse.getBody().toString();
    }

    private String getRequestUrl() {
        requestUrl = new StringBuilder();

        if(comtradeAPIParameters.getClass() == ComtradeAPIParametersAvailability.class) {
            requestUrl.append(URL_AVAILABILITY);
        } else {
            requestUrl.append(URL_REQUEST);
        }

        comtradeAPIParameters.stream().forEach(param -> {
            if(param != null) {
                requestUrl.append(param).append("&");
            }
        });
        requestUrl.replace(requestUrl.length()-1, requestUrl.length(), ""); //remove last &

        return requestUrl.toString();
    }
}
