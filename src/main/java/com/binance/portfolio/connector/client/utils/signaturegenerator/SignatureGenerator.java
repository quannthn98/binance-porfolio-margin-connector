package com.binance.portfolio.connector.client.utils.signaturegenerator;

public interface SignatureGenerator {
    String getSignature(String payload);
}
