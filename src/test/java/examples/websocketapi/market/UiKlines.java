package examples.websocketapi.market;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;

public final class UiKlines {

    private UiKlines() {
    }

    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {
        WebSocketApiClient client = new WebSocketApiClientImpl();
        client.connect(((event) -> {
            System.out.println(event);
        }));

        client.market().uiKlines("BTCUSDT", "1d", null);

        Thread.sleep(waitTime);
        client.close();

    }
}
