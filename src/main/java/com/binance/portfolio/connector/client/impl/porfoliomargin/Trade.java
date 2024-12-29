package com.binance.portfolio.connector.client.impl.porfoliomargin;

import com.binance.portfolio.connector.client.enums.HttpMethod;
import com.binance.portfolio.connector.client.utils.ParameterChecker;
import com.binance.portfolio.connector.client.utils.ProxyAuth;
import com.binance.portfolio.connector.client.utils.RequestHandler;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.portfolio.connector.client.utils.signaturegenerator.SignatureGenerator;

import java.util.Map;

/**
 * <h2>Trade Endpoints</h2>
 * All endpoints under the
 * <a href="https://developers.binance.com/docs/binance-spot-api-docs/rest-api#trading-endpoints">Spot Account/Trade Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class Trade {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;
    private final String UM_ORDER = "/papi/v1/um/order";
    private final String UM_CONDITIONAL_ORDER = "/papi/v1/um/conditional/order";
    private final String UM_ALL_CONDITIONAL_ORDER = "/papi/v1/um/conditional/allOpenOrders";

    public Trade(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
        this.showLimitUsage = showLimitUsage;
    }
    
    public Trade(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
        this.showLimitUsage = showLimitUsage;
    }

    public String newOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        return requestHandler.sendSignedRequest(baseUrl, UM_ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    public String newUmConditionalOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "strategyType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, UM_CONDITIONAL_ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    public String cancelUmOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendSignedRequest(baseUrl, UM_ORDER, parameters, HttpMethod.DELETE, showLimitUsage);
    }

    public String cancelUmConditionalOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendSignedRequest(baseUrl, UM_CONDITIONAL_ORDER, parameters, HttpMethod.DELETE, showLimitUsage);
    }

    public String cancelAllUmConditionalOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendSignedRequest(baseUrl, UM_ALL_CONDITIONAL_ORDER, parameters, HttpMethod.DELETE, showLimitUsage);
    }
}
