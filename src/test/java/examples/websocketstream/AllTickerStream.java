package examples.websocketstream;

import com.binance.portfolio.connector.client.WebSocketStreamClient;
import com.binance.portfolio.connector.client.impl.WebSocketStreamClientImpl;

public final class AllTickerStream {
    private AllTickerStream() {
    }

    public static void main(String[] args) {
        WebSocketStreamClient client = new WebSocketStreamClientImpl();
        client.allTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
