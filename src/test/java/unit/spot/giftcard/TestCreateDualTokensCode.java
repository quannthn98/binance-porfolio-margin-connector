package unit.spot.giftcard;

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

public class TestCreateDualTokensCode {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private final double baseTokenAmount = 1.002;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCreateDualTokensCodeWithoutParameters() {
        String path = "/sapi/v1/giftcard/buyCode";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createGiftCard().createDualTokensCode(parameters));
    }
    
    @Test
    public void testCreateDualTokensCode() {
        String path = "/sapi/v1/giftcard/buyCode?baseToken=BUSD&faceToken=BNB&baseTokenAmount=1.002";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("faceToken", "BNB");
        parameters.put("baseTokenAmount", baseTokenAmount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createGiftCard().createDualTokensCode(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
