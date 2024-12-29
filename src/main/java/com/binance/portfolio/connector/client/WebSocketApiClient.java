package com.binance.portfolio.connector.client;

import com.binance.portfolio.connector.client.impl.websocketapi.WebSocketApiAccount;
import com.binance.portfolio.connector.client.impl.websocketapi.WebSocketApiAuth;
import com.binance.portfolio.connector.client.impl.websocketapi.WebSocketApiGeneral;
import com.binance.portfolio.connector.client.impl.websocketapi.WebSocketApiMarket;
import com.binance.portfolio.connector.client.impl.websocketapi.WebSocketApiTrade;
import com.binance.portfolio.connector.client.impl.websocketapi.WebSocketApiUserDataStream;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketClosedCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketClosingCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketFailureCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketMessageCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketOpenCallback;

public interface WebSocketApiClient {
    void connect(WebSocketMessageCallback onMessageCallback);
    void connect(WebSocketOpenCallback onOpenCallback, WebSocketMessageCallback onMessageCallback, WebSocketClosingCallback onClosingCallback, WebSocketClosedCallback onClosedCallback, WebSocketFailureCallback onFailureCallback);
    void close();
    WebSocketApiAccount account();
    WebSocketApiAuth auth();
    WebSocketApiGeneral general();
    WebSocketApiMarket market();
    WebSocketApiTrade trade();
    WebSocketApiUserDataStream userDataStream();
}
