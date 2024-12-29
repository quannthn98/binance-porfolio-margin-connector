package examples.spot.spotalgo;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class CancelOrder {
    private CancelOrder() {
    }
    
    private static final long algoId = 1;
    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algoId", algoId);

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSpotAlgo().cancelOrder(parameters);
        System.out.println(result);
    }
}
