package examples.websocketapi.trade;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.enums.DefaultUrls;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import examples.PrivateConfig;

public final class GetOpenOrders {

    private GetOpenOrders() {
    }

    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {

        HmacSignatureGenerator signatureGenerator = new HmacSignatureGenerator(PrivateConfig.TESTNET_SECRET_KEY);
        WebSocketApiClient wsApiClient = new WebSocketApiClientImpl(PrivateConfig.TESTNET_API_KEY, signatureGenerator, DefaultUrls.TESTNET_WS_API_URL);

        wsApiClient.connect(((message) -> {
            System.out.println(message);
        }));
      
        wsApiClient.trade().getOpenOrders(null);
      
        Thread.sleep(waitTime);
      
        wsApiClient.close();
    }
}
