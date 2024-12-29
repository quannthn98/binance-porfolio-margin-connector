package com.binance.portfolio.connector.client.impl.spot;

import java.util.Map;

import com.binance.portfolio.connector.client.enums.HttpMethod;
import com.binance.portfolio.connector.client.utils.ProxyAuth;
import com.binance.portfolio.connector.client.utils.RequestHandler;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.portfolio.connector.client.utils.signaturegenerator.SignatureGenerator;

/**
 * <h2>Rebate Endpoints</h2>
 * All endpoints under the
 * <a href="https://developers.binance.com/docs/rebate/Introduction">Rebate Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class Rebate {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public Rebate(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
        this.showLimitUsage = showLimitUsage;
    }

    public Rebate(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
        this.showLimitUsage = showLimitUsage;
    }

    private final String TAX_QUERY = "/sapi/v1/rebate/taxQuery";
    /**
     * GET /sapi/v1/rebate/taxQuery
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * page -- optional/int -- 	Default 1 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/rebate/rest-api/Get-Spot-Rebate-History-Records">
     *     https://developers.binance.com/docs/rebate/rest-api/Get-Spot-Rebate-History-Records</a>
     */
    public String taxQuery(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, TAX_QUERY, parameters, HttpMethod.GET, showLimitUsage);
    }
}
