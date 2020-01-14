package strategies;

import com.fasterxml.jackson.databind.JsonNode;
import model.Forecast;
import util.Connection;
import util.JsonMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class WeatherbitStrategy implements Strategy {
    private final String TOKEN;
    private final String REQUEST_FORM;

    WeatherbitStrategy(String token) {
        TOKEN = token;
        REQUEST_FORM = "https://api.weatherbit.io/v2.0/current?city=%s&key=".concat(TOKEN);
    }

    @Override
    public boolean fillForecast(Forecast forecast) throws IOException {
        String city = forecast.getRequest()[0];
        String encodeCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String data = Connection.getData(String.format(REQUEST_FORM, encodeCity));
        JsonNode jsonNode = JsonMapper.getNode(data);
        boolean result = false;
        if(jsonNode != null) {
            forecast.setCity(jsonNode.get("data").get(0).get("city_name").asText());
            forecast.setWeather(jsonNode.get("data").get(0).get("weather").get("description").asText());
            forecast.setTemp(jsonNode.get("data").get(0).get("temp").asText());
            forecast.setLikeTemp(jsonNode.get("data").get(0).get("app_temp").asText());
            forecast.setWindSpeed(jsonNode.get("data").get(0).get("wind_spd").asText());
            forecast.setWindDir(jsonNode.get("data").get(0).get("wind_dir").asText());
            forecast.setClouds(jsonNode.get("data").get(0).get("clouds").asText());
            forecast.setPressure(jsonNode.get("data").get(0).get("pres").asText());
            forecast.setHumidity(jsonNode.get("data").get(0).get("rh").asText());
            result = true;
        }
        return result;
    }
}
