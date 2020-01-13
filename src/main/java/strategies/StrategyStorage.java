package strategies;

import java.util.HashMap;
import java.util.Map;

public class StrategyStorage {
    private static StrategyStorage instance;
    private final Map<String, Strategy> strategyMap = new HashMap<>();

    private StrategyStorage() {
        strategyMap.put("openweathermap", new OpenWeatherMapStrategy("Your API Key"));
        strategyMap.put("weatherbit", new WeatherbitStrategy("Your API Key"));
        strategyMap.put("accuweather", new AccuWeatherStrategy("Your API Key"));
    }

    public static StrategyStorage getInstance() {
        if(instance == null) instance = new StrategyStorage();
        return instance;
    }

    public Strategy getStrategy(String strategyName) {
        return strategyMap.get(strategyName);
    }
}
