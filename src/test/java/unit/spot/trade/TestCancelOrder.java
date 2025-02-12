package unit.spot.trade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.portfolio.connector.client.SpotClient;
import com.binance.portfolio.connector.client.enums.HttpMethod;
import com.binance.portfolio.connector.client.exceptions.BinanceClientException;
import com.binance.portfolio.connector.client.exceptions.BinanceConnectorException;
import com.binance.portfolio.connector.client.impl.SpotClientImpl;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class TestCancelOrder {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final int orderId = 123;
    private final int origClientOrderId = 456;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCancelOrderWithoutSymbol() {
        String path = "/api/v3/order";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createTrade().cancelOrder(parameters));
    }

    @Test
    public void testCancelOrderWithoutOrderId() {
        String path = "/api/v3/order?symbol=BNBUSDT&orderId=";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("orderId", "");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_CLIENT_ERROR);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceClientException.class, () -> client.createTrade().cancelOrder(parameters));
    }

    @Test
    public void testCancelOrder() {
        String path = "/api/v3/order?symbol=BNBUSDT&orderId=123&origClientOrderId=456";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("orderId", orderId);
        parameters.put("origClientOrderId", origClientOrderId);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createTrade().cancelOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
