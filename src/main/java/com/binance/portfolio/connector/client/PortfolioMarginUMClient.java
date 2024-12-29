package com.binance.portfolio.connector.client;

import com.binance.portfolio.connector.client.impl.spot.Trade;

public interface PortfolioMarginUMClient {
    Trade createTrade();
}
