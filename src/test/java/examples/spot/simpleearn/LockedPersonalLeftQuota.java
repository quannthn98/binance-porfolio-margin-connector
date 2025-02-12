package examples.spot.simpleearn;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class LockedPersonalLeftQuota {
    private LockedPersonalLeftQuota() {
    }
    
    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "USDT001");

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSimpleEarn().lockedPersonalLeftQuota(parameters);
        System.out.println(result);
    }
}
