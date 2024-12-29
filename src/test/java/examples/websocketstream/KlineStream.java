package examples.websocketstream;

import com.binance.portfolio.connector.client.WebSocketStreamClient;
import com.binance.portfolio.connector.client.impl.WebSocketStreamClientImpl;

public final class KlineStream {
    private KlineStream() {
    }

    public static void main(String[] args) {
        WebSocketStreamClient client = new WebSocketStreamClientImpl();
        client.klineStream("btcusdt", "1h", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
