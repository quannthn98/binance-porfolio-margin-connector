package examples.spot.margin;


import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class CrossMarginCollateralRatio {
    private CrossMarginCollateralRatio() {
    }

    public static void main(String[] args) {
        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().crossMarginCollateralRatio();
        System.out.println(result);
    }
}
