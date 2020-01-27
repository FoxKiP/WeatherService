import dao.DAOService;
import dao.Service;
import model.Forecast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class DAOTest extends Assert {
    private Forecast forecast;
    private Service daoService;
    private String test = "test";

    @Before
    public void setUp() throws Exception {
        daoService = DAOService.getInstance();
        forecast = new Forecast();
        forecast.setRequest(test);
        forecast.setResouce(test);
        forecast.setCity(test);
        forecast.setWeather(test);
        forecast.setTemp(test);
        forecast.setLikeTemp(test);
        forecast.setWindSpeed(test);
        forecast.setWindDeg(test);
        forecast.setClouds(test);
        forecast.setPressure(test);
        forecast.setHumidity(test);
    }

    @Test
    public void saveLoadTest() {
        daoService.addForecast(forecast);
        assertEquals(forecast, daoService.getForecast(test, test));
    }
}
