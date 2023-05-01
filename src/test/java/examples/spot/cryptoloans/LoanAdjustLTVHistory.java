package examples.spot.cryptoloans;

import java.util.LinkedHashMap;

import com.binance.connector.client.impl.SpotClientImpl;

import examples.PrivateConfig;

public final class LoanAdjustLTVHistory {

    private LoanAdjustLTVHistory() {
    }

    private static final long orderId = 100000001;

    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", orderId);
        parameters.put("loanCoin", "BUSD");
        
        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createCryptoLoans().loanAdjustLTVHistory(parameters);
        System.out.println(result);
    }
}