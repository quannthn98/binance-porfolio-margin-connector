package examples.websocketapi.general;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketMessageCallback;

public final class ServerTime {
    private ServerTime() {
    }

    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {
        WebSocketApiClient client = new WebSocketApiClientImpl();

        WebSocketMessageCallback onMessageCallback = (event) -> {
            System.out.println(event);
        };

        client.connect(onMessageCallback);
        
        client.general().serverTime(null);

        Thread.sleep(waitTime);
        client.close();
    }
}