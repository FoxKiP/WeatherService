package strategies;

import com.fasterxml.jackson.databind.JsonNode;
import model.Forecast;
import util.Connection;
import util.JsonMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class AccuWeatherStrategy implements Strategy {
    private final String TOKEN;
    private final String REQUEST_FORM;

    AccuWeatherStrategy(String token) {
        TOKEN = token;
        REQUEST_FORM = "http://dataservice.accuweather.com/forecasts/v1/hourly/1hour/" +
                "%s?details=true&metric=true&apikey=".concat(TOKEN);
    }

    @Override
    public boolean fillForecast(Forecast forecast) throws IOException {
        String city = forecast.getRequest()[0];
        String encodeCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String cityId = getCityId(encodeCity, forecast);
        boolean result = false;
        if(!cityId.isEmpty()) {
            String data = Connection.getData(String.format(REQUEST_FORM, cityId));
            JsonNode jsonNode = JsonMapper.getNode(data);

            forecast.setWeather(jsonNode.get(0).get("IconPhrase").asText());
            forecast.setTemp(jsonNode.get(0).get("Temperature").get("Value").asText());
            forecast.setLikeTemp(jsonNode.get(0).get("RealFeelTemperature").get("Value").asText());
            forecast.setWindSpeed(jsonNode.get(0).get("Wind").get("Speed").get("Value").asText());
            forecast.setWindDir(jsonNode.get(0).get("Wind").get("Direction").get("Degrees").asText());
            forecast.setClouds(jsonNode.get(0).get("CloudCover").asText());
            forecast.setPressure("no data");
            forecast.setHumidity(jsonNode.get(0).get("RelativeHumidity").asText());
            result = true;
        }
        return result;
    }

    private String getCityId(String city, Forecast forecast) throws IOException {
        String requestForm = "http://dataservice.accuweather.com/locations/v1/cities/search?q=%s&apikey=".concat(TOKEN);
        String data = Connection.getData(String.format(requestForm, city));
        String cityId = "";

        JsonNode jsonNode = JsonMapper.getNode(data);
        if(jsonNode.get(0) != null) {
            forecast.setCity(jsonNode.get(0).get("EnglishName").asText());
            cityId = jsonNode.get(0).get("Key").asText();
        }
        return cityId;
    }
}
