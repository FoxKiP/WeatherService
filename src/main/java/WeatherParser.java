import dao.Service;
import model.Forecast;
import strategies.Strategy;
import strategies.StrategyStorage;
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
        Forecast forecast = new Forecast();
        forecast.setRequest(city.toLowerCase(), resource);
        String jsonForecast = "{}";
        try {
            if (!daoService.fillForecast(forecast)) {
                Strategy strategy = strategyStorage.getStrategy(resource);
                if(strategy != null) {
                    if(strategy.fillForecast(forecast)) {
                        daoService.addForecast(forecast);
                    }
                }
            }
            jsonForecast = JsonMapper.getJson(forecast);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonForecast;
    }
}
