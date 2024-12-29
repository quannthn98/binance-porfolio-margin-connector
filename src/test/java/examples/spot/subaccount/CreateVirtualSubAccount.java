package examples.spot.subaccount;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class CreateVirtualSubAccount {
    private CreateVirtualSubAccount() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("subAccountString", "");

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().createVirtualSubAccount(parameters);
        System.out.println(result);
    }
}
