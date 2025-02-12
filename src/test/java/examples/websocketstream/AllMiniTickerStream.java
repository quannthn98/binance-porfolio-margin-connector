package examples.websocketstream;

import com.binance.portfolio.connector.client.WebSocketStreamClient;
import com.binance.portfolio.connector.client.impl.WebSocketStreamClientImpl;

public final class AllMiniTickerStream {
    private AllMiniTickerStream() {
    }

    public static void main(String[] args) {
        WebSocketStreamClient client = new WebSocketStreamClientImpl();
        client.allMiniTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
