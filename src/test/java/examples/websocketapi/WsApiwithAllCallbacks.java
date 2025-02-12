package examples.websocketapi;

import org.json.JSONObject;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketClosedCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketClosingCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketFailureCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketMessageCallback;
import com.binance.portfolio.connector.client.utils.websocketcallback.WebSocketOpenCallback;


public final class WsApiwithAllCallbacks {
    private WsApiwithAllCallbacks() {
    }

    private static WebSocketOpenCallback onOpenCallback;
    private static WebSocketMessageCallback onMessageCallback;
    private static WebSocketClosingCallback onClosingCallback;
    private static WebSocketClosedCallback onClosedCallback;
    private static WebSocketFailureCallback onFailureCallback;

    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {

        onOpenCallback = openEvent -> {
            System.out.println("Open Connection: " + openEvent.toString());
        };

        onMessageCallback = (message) -> {
            System.out.println("Connection Message:" + message);
        };

        onClosingCallback = (code, reason) -> {
            System.out.println("Closing Connection: code=" + code + ", reason=" + reason);
        };

        onClosedCallback = (code, reason) -> {
            System.out.println("Closed Connection: code=" + code + ", reason=" + reason);
        };

        onFailureCallback = (throwable, response) -> {
            System.out.println("Connection Failed: throwable=" + throwable.getMessage());
        };

        WebSocketApiClient client = new WebSocketApiClientImpl();
        client.connect(onOpenCallback, onMessageCallback, onClosingCallback, onClosedCallback, onFailureCallback);
        
        JSONObject params = new JSONObject();
        params.put("requestId", "randomId");
        client.general().ping(params);

        Thread.sleep(waitTime);
        client.close();
    }
}