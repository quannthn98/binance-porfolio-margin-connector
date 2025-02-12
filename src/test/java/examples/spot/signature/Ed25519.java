package examples.spot.signature;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.exceptions.BinanceClientException;
import com.binance.portfolio.connector.client.exceptions.BinanceConnectorException;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;
import com.binance.portfolio.connector.client.utils.signaturegenerator.Ed25519SignatureGenerator;

import examples.PrivateConfig;

public final class Ed25519 {
    private Ed25519() {
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Map<String, Object> parameters = new LinkedHashMap<>();

        Ed25519SignatureGenerator signGenerator =  new Ed25519SignatureGenerator(PrivateConfig.TESTNET_PRIVATE_KEY_PATH); // Private Key file path as input
        SpotClient client = new SpotClientImpl(PrivateConfig.TESTNET_API_KEY, signGenerator, PrivateConfig.BASE_URL);

        try {
            String result = client.createTrade().account(parameters);
            System.out.println(result);
        } catch (BinanceConnectorException e) {
            System.err.println((String) String.format("fullErrMessage: %s", e.getMessage()));
        } catch (BinanceClientException e) {
            System.err.println((String) String.format("fullErrMessage: %s \nerrMessage: %s \nerrCode: %d \nHTTPStatusCode: %d",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode()));
        }
    }
}
