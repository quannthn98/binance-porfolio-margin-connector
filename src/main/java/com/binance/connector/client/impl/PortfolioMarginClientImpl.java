package com.binance.connector.client.impl;

import com.binance.connector.client.PortfolioMarginClient;
import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.impl.spot.Trade;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;

public class PortfolioMarginClientImpl implements PortfolioMarginClient {
    private final String apiKey;
    private final SignatureGenerator signatureGenerator;
    private final String baseUrl;
    private boolean showLimitUsage = false;
    private ProxyAuth proxy = null;

    public PortfolioMarginClientImpl() {
        this(DefaultUrls.PROD_URL);
    }

    public PortfolioMarginClientImpl(String baseUrl) {
        this("", (SignatureGenerator) null, baseUrl);
    }

    public PortfolioMarginClientImpl(String baseUrl, boolean showLimitUsage) {
        this(baseUrl);
        this.showLimitUsage = showLimitUsage;
    }

    public PortfolioMarginClientImpl(String apiKey, String secretKey) {
        this(apiKey, secretKey, DefaultUrls.PROD_URL);
    }

    public PortfolioMarginClientImpl(String apiKey, String secretKey, String baseUrl) {
        this(apiKey, new HmacSignatureGenerator(secretKey), baseUrl);
    }

    public PortfolioMarginClientImpl(String apiKey, SignatureGenerator signatureGenerator, String baseUrl) {
        this.apiKey = apiKey;
        this.signatureGenerator = signatureGenerator;
        this.baseUrl = baseUrl;
    }

    @Override
    public Trade createTrade() {
        return new Trade(baseUrl, apiKey, signatureGenerator, showLimitUsage, proxy);
    }
}
