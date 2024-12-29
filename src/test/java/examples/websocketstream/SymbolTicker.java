package examples.websocketstream;

import com.binance.portfolio.connector.client.WebSocketStreamClient;
import com.binance.portfolio.connector.client.impl.WebSocketStreamClientImpl;

public final class SymbolTicker {
    private SymbolTicker() {
    }

    public static void main(String[] args) {
        WebSocketStreamClient client = new WebSocketStreamClientImpl();
        client.symbolTicker("btcusdt", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
