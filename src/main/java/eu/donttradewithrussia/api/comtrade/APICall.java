package eu.donttradewithrussia.api.comtrade;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class APICall {
    private static final String URL_AVAILABILITY = "http://comtrade.un.org/api//refs/da/view\\?";
    private static final String URL_REQUEST = "http://comtrade.un.org/api/get\\?";

    ComtradeParameters comtradeParameters;
    private StringBuilder requestUrl;

    public APICall(ComtradeParametersRequest comtradeParametersRequest) {
        this.comtradeParameters = comtradeParametersRequest;
    }

    public boolean call() {
        HttpResponse<JsonNode> httpResponse = Unirest.get(getRequestUrl()).asJson();
        return true;
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
        requestUrl.replace(requestUrl.length()-1, requestUrl.length(), "");

        return requestUrl.toString();
    }
}
