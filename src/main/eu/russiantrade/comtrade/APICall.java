package eu.russiantrade.comtrade;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class APICall {
    private static final String URL_AVAILABILITY = "http://comtrade.un.org/api//refs/da/view?";
    private static final String URL_REQUEST = "http://comtrade.un.org/api/get?";

    ComtradeParameters comtradeParameters;
    private StringBuilder requestUrl;

    public APICall(ComtradeParameters comtradeParametersRequest) {
        this.comtradeParameters = comtradeParametersRequest;
    }

    public String call() {
        System.out.println(getRequestUrl());
        HttpResponse<JsonNode> httpResponse = Unirest.get(getRequestUrl()).asJson();
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

        if(comtradeParameters.getClass() == ComtradeParametersAvailability.class) {
            requestUrl.append(URL_AVAILABILITY);
        } else {
            requestUrl.append(URL_REQUEST);
        }

        comtradeParameters.stream().forEach(param -> {
            if(param != null) {
                requestUrl.append(param).append("&");
            }
        });
        requestUrl.replace(requestUrl.length()-1, requestUrl.length(), ""); //remove last &

        return requestUrl.toString();
    }
}
