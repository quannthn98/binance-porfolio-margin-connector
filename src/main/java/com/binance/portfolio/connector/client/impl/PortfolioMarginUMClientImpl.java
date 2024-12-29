package com.binance.portfolio.connector.client.impl;

import com.binance.portfolio.connector.client.PortfolioMarginUMClient;
import com.binance.portfolio.connector.client.enums.DefaultUrls;
import com.binance.portfolio.connector.client.impl.spot.Trade;
import com.binance.portfolio.connector.client.utils.ProxyAuth;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.portfolio.connector.client.utils.signaturegenerator.SignatureGenerator;

public class PortfolioMarginUMClientImpl implements PortfolioMarginUMClient {
    private final String apiKey;
    private final SignatureGenerator signatureGenerator;
    private final String baseUrl;
    private boolean showLimitUsage = false;
    private ProxyAuth proxy = null;

    public PortfolioMarginUMClientImpl() {
        this(DefaultUrls.PROD_URL);
    }

    public PortfolioMarginUMClientImpl(String baseUrl) {
        this("", (SignatureGenerator) null, baseUrl);
    }

    public PortfolioMarginUMClientImpl(String baseUrl, boolean showLimitUsage) {
        this(baseUrl);
        this.showLimitUsage = showLimitUsage;
    }

    public PortfolioMarginUMClientImpl(String apiKey, String secretKey) {
        this(apiKey, secretKey, DefaultUrls.PROD_URL);
    }

    public PortfolioMarginUMClientImpl(String apiKey, String secretKey, String baseUrl) {
        this(apiKey, new HmacSignatureGenerator(secretKey), baseUrl);
    }

    public PortfolioMarginUMClientImpl(String apiKey, SignatureGenerator signatureGenerator, String baseUrl) {
        this.apiKey = apiKey;
        this.signatureGenerator = signatureGenerator;
        this.baseUrl = baseUrl;
    }

    @Override
    public Trade createTrade() {
        return new Trade(baseUrl, apiKey, signatureGenerator, showLimitUsage, proxy);
    }
}
