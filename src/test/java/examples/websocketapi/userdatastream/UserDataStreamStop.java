package examples.websocketapi.userdatastream;

import com.binance.portfolio.connector.client.WebSocketApiClient;
import com.binance.portfolio.connector.client.enums.DefaultUrls;
import com.binance.portfolio.connector.client.impl.WebSocketApiClientImpl;
import com.binance.portfolio.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import examples.PrivateConfig;

public final class UserDataStreamStop {
    
    private UserDataStreamStop() {
    }

    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {
        HmacSignatureGenerator signatureGenerator = new HmacSignatureGenerator(PrivateConfig.TESTNET_SECRET_KEY);
        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.TESTNET_API_KEY, signatureGenerator, DefaultUrls.TESTNET_WS_API_URL);

        client.connect(((event) -> {
            System.out.println(event);
        }));
        
        client.userDataStream().userDataStreamStop("listenKey", null);

        Thread.sleep(waitTime);
        client.close();
    }
}