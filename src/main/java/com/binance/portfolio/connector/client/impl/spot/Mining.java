package com.binance.portfolio.connector.client.impl.spot;

import java.util.Map;

import com.binance.portfolio.connector.client.enums.HttpMethod;
import com.binance.portfolio.connector.client.utils.ParameterChecker;
import com.binance.portfolio.connector.client.utils.ProxyAuth;
import com.binance.portfolio.connector.client.utils.RequestHandler;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.portfolio.connector.client.utils.signaturegenerator.SignatureGenerator;

/**
 * <h2>Mining Endpoints</h2>
 * All endpoints under the
 * <a href="https://developers.binance.com/docs/mining/Introduction">Mining Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class Mining {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public Mining(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
        this.showLimitUsage = showLimitUsage;
    }

    public Mining(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, boolean showLimitUsage, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
        this.showLimitUsage = showLimitUsage;
    }

    private final String ALGO = "/sapi/v1/mining/pub/algoList";
    /**
     * GET /sapi/v1/mining/pub/algoList
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Acquiring-Algorithm">
     *     https://developers.binance.com/docs/mining/rest-api/Acquiring-Algorithm</a>
     */
    public String algorithm(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ALGO, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String COIN_NAME = "/sapi/v1/mining/pub/coinList";
    /**
     * GET /sapi/v1/mining/pub/coinList
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Acquiring-CoinName">
     *     https://developers.binance.com/docs/mining/rest-api/Acquiring-CoinName</a>
     */
    public String coinName(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, COIN_NAME, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DETAIL_MINER_LIST = "/sapi/v1/mining/worker/detail";
    /**
     * GET /sapi/v1/mining/worker/detail
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- sha256 <br>
     * userName -- mandatory/string -- Mining account <br>
     * workerName -- mandatory/string -- Miner's name(required) <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Request-for-Detail-Miner-List">
     *     https://developers.binance.com/docs/mining/rest-api/Request-for-Detail-Miner-List</a>
     */
    public String detailMinerList(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        ParameterChecker.checkParameter(parameters, "workerName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, DETAIL_MINER_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MINER_LIST = "/sapi/v1/mining/worker/list";
    /**
     * GET /sapi/v1/mining/worker/list
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- sha256 <br>
     * userName -- mandatory/string -- Mining account <br>
     * pageIndex -- optional/int -- Page number,default is first page,start form 1 <br>
     * sort -- optional/int -- sort sequence(default=0)0 positive sequence, 1 negative sequence <br>
     * sortColumn -- optional/int -- Sort by( default 1):
     * 1: miner name,
     * 2: real-time computing power,
     * 3: daily average computing power,
     * 4: real-time rejection rate,
     * 5: last submission time <br>
     * workerStatus -- optional/int -- miners status(default=0)0 all,1 valid,2 invalid,3 failure <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Request-for-Miner-List">
     *     https://developers.binance.com/docs/mining/rest-api/Request-for-Miner-List</a>
     */
    public String minerList(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, MINER_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String EARNING_LIST = "/sapi/v1/mining/payment/list";
    /**
     * GET /sapi/v1/mining/payment/list
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- sha256 <br>
     * userName -- mandatory/string -- Mining account <br>
     * coin -- optional/string <br>
     * startDate -- optional/long -- Search date, millisecond timestamp, while empty query all	 <br>
     * endDate -- optional/long -- Search date, millisecond timestamp, while empty query all <br>
     * pageIndex -- optional/int -- Page number, empty default first page, starting from 1 <br>
     * pageSize -- optional/int -- 	Number of pages, minimum 10, maximum 200 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Earnings-List">
     *     https://developers.binance.com/docs/mining/rest-api/Earnings-List</a>
     */
    public String earningList(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, EARNING_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BONUS_LIST = "/sapi/v1/mining/payment/other";
    /**
     * GET /sapi/v1/mining/payment/other
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- sha256 <br>
     * userName -- mandatory/string -- Mining account <br>
     * coin -- optional/string <br>
     * startDate -- optional/long -- Search date, millisecond timestamp, while empty query all	 <br>
     * endDate -- optional/long -- Search date, millisecond timestamp, while empty query all <br>
     * pageIndex -- optional/int -- Page number, empty default first page, starting from 1 <br>
     * pageSize -- optional/int -- 	Number of pages, minimum 10, maximum 200 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Extra-Bonus-List">
     *     https://developers.binance.com/docs/mining/rest-api/Extra-Bonus-List</a>
     */
    public String bonusList(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, BONUS_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HASHRATE_RESALE_LIST = "/sapi/v1/mining/hash-transfer/config/details/list";
    /**
     * GET /sapi/v1/mining/hash-transfer/config/details/list
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * pageIndex -- optional/int -- Page number, empty default first page, starting from 1 <br>
     * pageSize -- optional/int -- 	Number of pages, minimum 10, maximum 200 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Hashrate-Resale-List">
     *     https://developers.binance.com/docs/mining/rest-api/Hashrate-Resale-List</a>
     */
    public String hashrateResaleList(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, HASHRATE_RESALE_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HASHRATE_RESALE_DETAIL = "/sapi/v1/mining/hash-transfer/profit/details";
    /**
     * GET /sapi/v1/mining/hash-transfer/profit/details
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * configId -- mandatory/int -- Mining ID <br>
     * userName -- mandatory/string -- Mining Account <br>
     * pageIndex -- optional/int -- Page number, empty default first page, starting from 1 <br>
     * pageSize -- optional/int -- 	Number of pages, minimum 10, maximum 200 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Hashrate-Resale-Detail">
     *     https://developers.binance.com/docs/mining/rest-api/Hashrate-Resale-Detail</a>
     */
    public String hashrateResaleDetail(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "configId", Integer.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, HASHRATE_RESALE_DETAIL, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HASHRATE_RESALE_REQUEST = "/sapi/v1/mining/hash-transfer/config";
    /**
     * GET /sapi/v1/mining/hash-transfer/config
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * userName -- mandatory/string -- Mining Account <br>
     * algo -- mandatory/string -- Transfer algorithm(sha256) <br>
     * endDate -- mandatory/long -- Resale End Time (Millisecond timestamp) <br>
     * startDate -- mandatory/long -- Resale Start Time(Millisecond timestamp) <br>
     * toPoolUser -- mandatory/string -- Mining Account <br>
     * hashRate -- mandatory/long -- Resale hashrate h/s must be transferred (BTC is greater than 500000000000 ETH is greater than 500000) <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Hashrate-Resale-Request">
     *     https://developers.binance.com/docs/mining/rest-api/Hashrate-Resale-Request</a>
     */
    public String hashrateResaleRequest(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "endDate", Long.class);
        ParameterChecker.checkParameter(parameters, "startDate", Long.class);
        ParameterChecker.checkParameter(parameters, "toPoolUser", String.class);
        ParameterChecker.checkParameter(parameters, "hashRate", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, HASHRATE_RESALE_REQUEST, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String CANCEL_HASHRATE_RESALE_CONFIG = "/sapi/v1/mining/hash-transfer/config/cancel";
    /**
     * GET /sapi/v1/mining/hash-transfer/config/cancel
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * configId -- mandatory/int -- Mining ID <br>
     * userName -- mandatory/string -- Mining Account <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Cancel-hashrate-resale-configuration">
     *     https://developers.binance.com/docs/mining/rest-api/Cancel-hashrate-resale-configuration</a>
     */
    public String cancelHashrateResaleConfig(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "configId", Integer.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CANCEL_HASHRATE_RESALE_CONFIG, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String STATSTICS_LIST = "/sapi/v1/mining/statistics/user/status";
    /**
     * GET /sapi/v1/mining/statistics/user/status
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- Algorithm(sha256) <br>
     * userName -- mandatory/string -- Mining Account <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Statistic-List">
     *     https://developers.binance.com/docs/mining/rest-api/Statistic-List</a>
     */
    public String statsticsList(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, STATSTICS_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ACCOUNT_LIST = "/sapi/v1/mining/statistics/user/list";
    /**
     * GET /sapi/v1/mining/statistics/user/list
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- Algorithm(sha256) <br>
     * userName -- mandatory/string -- Mining Account <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Account-List">
     *     https://developers.binance.com/docs/mining/rest-api/Account-List</a>
     */
    public String accountList(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        ParameterChecker.checkParameter(parameters, "userName", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ACCOUNT_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ACCOUNT_EARNING = "/sapi/v1/mining/payment/uid";
    /**
     * GET /sapi/v1/mining/payment/uid
     * <br>
     * @param
     * parameters Map of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * algo -- mandatory/string -- Algorithm(sha256) <br>
     * startDate -- optional/long -- Millisecond timestamp <br>
     * endDate -- optional/long -- Millisecond timestamp <br>
     * pageIndex -- optional/int -- Default 1 <br>
     * pageSize -- optional/int -- 	Min 10,Max 200 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://developers.binance.com/docs/mining/rest-api/Mining-Account-Earning">
     *     https://developers.binance.com/docs/mining/rest-api/Mining-Account-Earning</a>
     */
    public String accountEarning(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algo", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ACCOUNT_EARNING, parameters, HttpMethod.GET, showLimitUsage);
    }
}
