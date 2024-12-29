package examples.websocketapi.signature;

import org.json.JSONObject;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.enums.DefaultUrls;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;
import com.binance.portfolio.connector.client.utils.signaturegenerator.RsaSignatureGenerator;

import examples.PrivateConfig;

public final class Rsa {
    private Rsa() {
    }

    private static final double quantity = 0.01;
    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {
        RsaSignatureGenerator signatureGenerator =  new RsaSignatureGenerator(PrivateConfig.TESTNET_PRIVATE_KEY_PATH);
        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.TESTNET_API_KEY, signatureGenerator, DefaultUrls.TESTNET_WS_API_URL);
        client.connect(((event) -> {
            System.out.println(event);
        }));

        JSONObject params = new JSONObject();
        params.put("requestId", "randomId");
        params.put("quantity", quantity);

        client.trade().testNewOrder("BTCUSDT", "BUY", "MARKET", params);

        Thread.sleep(waitTime);
        client.close();
    }
}

