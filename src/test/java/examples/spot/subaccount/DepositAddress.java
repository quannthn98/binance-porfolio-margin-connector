package examples.spot.subaccount;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class DepositAddress {
    private DepositAddress() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "");
        parameters.put("coin", "USDT");

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().depositAddress(parameters);
        System.out.println(result);
    }
}
