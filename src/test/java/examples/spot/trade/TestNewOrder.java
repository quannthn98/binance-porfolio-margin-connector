package examples.spot.trade;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.exceptions.BinanceClientException;
import com.binance.portfolio.connector.client.exceptions.BinanceConnectorException;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class TestNewOrder {
    private TestNewOrder() {
    }
    private static final double quantity = 0.01;
    private static final int price = 95000;

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();

        SpotClient client = new SpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", quantity);
        parameters.put("price", price);

        try {
            String result = client.createTrade().testNewOrder(parameters);
            System.out.println(result);
        } catch (BinanceConnectorException e) {
            System.err.println((String) String.format("fullErrMessage: %s", e.getMessage()));
        } catch (BinanceClientException e) {
            System.err.println((String) String.format("fullErrMessage: %s \nerrMessage: %s \nerrCode: %d \nHTTPStatusCode: %d",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode()));
        }
    }
}
