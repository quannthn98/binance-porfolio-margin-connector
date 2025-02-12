package unit.spot.viploans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.enums.HttpMethod;
import com.binance.portfolio.connector.client.exceptions.BinanceConnectorException;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class TestBorrow {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private static final long loanAccountId = 12345678;
    private static final double loanAmount = 100.55;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testBorrowWithoutParameters() {
        String path = "/sapi/v1/loan/vip/borrow";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }
    
    @Test
    public void testBorrow() {
        String path = String.format("/sapi/v1/loan/vip/borrow?loanAccountId=%s&loanAmount=%s&loanCoin=%s&collateralAccountId=%s&collateralCoin=%s&isFlexibleRate=true", loanAccountId, loanAmount, "BTC", "87654321", "BUSD");
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", loanAccountId);
        parameters.put("loanAmount", loanAmount);
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralAccountId", "87654321");
        parameters.put("collateralCoin", "BUSD");
        parameters.put("isFlexibleRate", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createVIPLoans().borrow(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
