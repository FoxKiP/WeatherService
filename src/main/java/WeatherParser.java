import dao.Service;
import model.Forecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import strategies.Strategy;
import strategies.StrategyStorage;
import util.Connection;
import util.JsonMapper;

import java.io.IOException;

public class WeatherParser {
    private Service daoService;
    private StrategyStorage strategyStorage;

    public WeatherParser(StrategyStorage strategyStorage, Service daoService) {
        this.strategyStorage = strategyStorage;
        this.daoService = daoService;
    }

    public String getForecast(String city, String resource) {
        Forecast forecast;
        forecast = daoService.getForecast(city, resource);
        if(forecast == null) {
            forecast = new Forecast();
            forecast.setRequest(city.toLowerCase());
            forecast.setResouce(resource);
            Strategy strategy = strategyStorage.getStrategy(resource);
            if(strategy != null && strategy.fillForecast(forecast)) {
                daoService.addForecast(forecast);
            } else return "{\"Error\":\"error in receiving data from the service: " + resource + "\"}";
        }
        return JsonMapper.getJson(forecast);
    }
}
