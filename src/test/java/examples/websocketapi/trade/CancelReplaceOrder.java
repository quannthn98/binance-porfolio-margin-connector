package examples.websocketapi.trade;

import org.json.JSONObject;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.enums.DefaultUrls;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import examples.PrivateConfig;

public final class CancelReplaceOrder {

    private CancelReplaceOrder() {
    }

    private static final double quantity = 0.01;
    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {

        HmacSignatureGenerator signatureGenerator = new HmacSignatureGenerator(PrivateConfig.TESTNET_SECRET_KEY);
        WebSocketApiClient wsApiClient = new WebSocketApiClientImpl(PrivateConfig.TESTNET_API_KEY, signatureGenerator, DefaultUrls.TESTNET_WS_API_URL);

        wsApiClient.connect(((message) -> {
            System.out.println(message);
        }));
      
        JSONObject params = new JSONObject();
        params.put("requestId", "randomId");
        params.put("quantity", quantity);
        params.put("cancelOrigClientOrderId", "randomClientOrderId");
      
        wsApiClient.trade().cancelReplaceOrder("BTCUSDT", "STOP_ON_FAILURE", "BUY", "MARKET", params);
      
        Thread.sleep(waitTime);
      
        wsApiClient.close();
    }
}

