package examples.websocketstream;

import com.binance.portfolio.connector.client.WebSocketStreamClient;
import com.binance.portfolio.connector.client.impl.WebSocketStreamClientImpl;

public final class RollingWindowTicker {
    private RollingWindowTicker() {
    }

    public static void main(String[] args) {
        WebSocketStreamClient client = new WebSocketStreamClientImpl();
        client.rollingWindowTicker("btcusdt", "1h", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
