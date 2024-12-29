package com.binance.connector.client;

import com.binance.connector.client.impl.spot.Trade;

public interface PortfolioMarginClient {
    Trade createTrade();
}
