package examples.spot.margin;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class NextHourlyInterestRate {
    private NextHourlyInterestRate() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("assets", "BTC,ETH");
        parameters.put("isIsolated", true);

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().nextHourlyInterestRate(parameters);
        System.out.println(result);
    }
}
