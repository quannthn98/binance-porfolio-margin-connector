package examples.spot.market;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;
import com.binance.portfolio.connector.client.impl.spot.Market;

import examples.PrivateConfig;

public final class TradingDayTicker {
    private TradingDayTicker() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        Market market = client.createMarket();

        parameters.put("symbol", "BNBUSDT");
        parameters.put("type", "MINI");
        String result = market.tradingDayTicker(parameters);
        System.out.println(result);
        parameters.clear();

        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        symbols.add("BNBUSDT");
        parameters.put("symbols", symbols);
        result = market.tradingDayTicker(parameters);
        System.out.println(result);
    }
}
