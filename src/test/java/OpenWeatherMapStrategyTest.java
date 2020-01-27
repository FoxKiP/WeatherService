import model.Forecast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import strategies.Strategy;
import strategies.StrategyStorage;

public class OpenWeatherMapStrategyTest extends Assert {
    private Strategy strategy;
    private Forecast forecast;
    private String strategyName = "openweathermap";
    private String city = "челябинск";
    private String answer = "Chelyabinsk";

    @Before
    public void setUp() throws Exception {
        strategy = StrategyStorage.getInstance().getStrategy(strategyName);
        forecast = new Forecast();
        forecast.setRequest(city);
        forecast.setResouce(strategyName);
    }

    @Test
    public void testFillForecast() {
        strategy.fillForecast(forecast);
        assertEquals(answer, forecast.getCity());
    }
}
