package examples.spot.convert;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class OrderStatus {
    private OrderStatus() {
    }

    public static void main(String[] args) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put("quoteId", "12415572564");

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createConvert().orderStatus(parameters);
        System.out.println(result);
    }
}
