package unit.spot.simpleearn;

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

public class TestFlexibleSubscriptionPreview {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private static final double amount = 1.1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testFlexibleSubscriptionPreviewWithoutParameters() {
        String path = "/sapi/v1/simple-earn/flexible/subscriptionPreview";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().flexibleSubscriptionPreview(parameters));
    }
    
    @Test
    public void testFlexibleSubscriptionPreview() {
        String path = "/sapi/v1/simple-earn/flexible/subscriptionPreview?productId=40607&amount=1.1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "40607");
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSimpleEarn().flexibleSubscriptionPreview(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
