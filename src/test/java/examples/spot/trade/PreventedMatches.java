package examples.spot.trade;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class PreventedMatches {
    private PreventedMatches() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createTrade().preventedMatches(parameters);
        System.out.println(result);
    }
}
