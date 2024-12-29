package com.binance.connector.client.impl.porfoliomargin;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;

import java.util.Map;

/**
 * <h2>Trade Endpoints</h2>
 * All endpoints under the
 * <a href="https://developers.binance.com/docs/binance-spot-api-docs/rest-api#trading-endpoints">Spot Account/Trade Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class Account {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;
    private final String POSITION_RISK = "/papi/v1/um/positionRisk";

    public Account(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
        this.showLimitUsage = showLimitUsage;
    }

    public Account(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
        this.showLimitUsage = showLimitUsage;
    }

    public String positionInformation(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, POSITION_RISK, parameters, HttpMethod.GET, showLimitUsage);
    }
}
