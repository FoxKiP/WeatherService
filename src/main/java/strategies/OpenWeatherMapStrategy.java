package strategies;

import com.fasterxml.jackson.databind.JsonNode;
import model.Forecast;
import util.Connection;
import util.JsonMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class OpenWeatherMapStrategy implements Strategy {
    private final String TOKEN;
    private final String REQUEST_FORM;

    OpenWeatherMapStrategy(String token) {
        TOKEN = token;
        REQUEST_FORM = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&APPID=".concat(TOKEN);
    }

    @Override
    public boolean fillForecast(Forecast forecast) throws IOException {
        String city = forecast.getRequest()[0];
        String encodeCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String data = Connection.getData(String.format(REQUEST_FORM, encodeCity));
        JsonNode jsonNode = JsonMapper.getNode(data);
        boolean result = false;
        if(jsonNode != null) {
            forecast.setCity(jsonNode.get("name").asText());
            forecast.setWeather(jsonNode.get("weather").get(0).get("description").asText());
            forecast.setTemp(jsonNode.get("main").get("temp").asText());
            forecast.setLikeTemp(jsonNode.get("main").get("feels_like").asText());
            forecast.setWindSpeed(jsonNode.get("wind").get("speed").asText());
            forecast.setWindDir(jsonNode.get("wind").get("deg").asText());
            forecast.setClouds(jsonNode.get("clouds").get("all").asText());
            forecast.setPressure(jsonNode.get("main").get("pressure").asText());
            forecast.setHumidity(jsonNode.get("main").get("humidity").asText());
            result = true;
        }
        return result;
    }
}
