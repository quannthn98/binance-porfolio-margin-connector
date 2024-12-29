package com.binance.portfolio.connector.client;

import com.binance.portfolio.connector.client.impl.porfoliomargin.Account;
import com.binance.portfolio.connector.client.impl.porfoliomargin.Trade;

public interface PortfolioMarginUMClient {
    Trade createTrade();

    Account createAccount();
}
