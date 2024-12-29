package examples.spot.portfoliomargin;

import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class SwitchAutoRepayFutures {
    private SwitchAutoRepayFutures() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("autoRepay", true);

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createPortfolioMargin().switchAutoRepayFutures(parameters);
        System.out.println(result);
    }
}
