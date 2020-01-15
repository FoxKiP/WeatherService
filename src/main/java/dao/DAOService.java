package dao;

import model.Forecast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DAOService implements Service {
    private static DAOService instance;
    private Storage storage = new Storage();
    private String tableName = "weather";

    private DAOService() {
    }

    public static Service getInstance() {
        if(instance == null) instance = new DAOService();
        return instance;
    }

    @Override
    public boolean fillForecast(Forecast forecast) {
        boolean result = false;
        String[] request = forecast.getRequest();
        String city = request[0];
        String resource = request[1];
        try {
            ResultSet resultSet = getLastQuery(city, resource);
            if(resultSet.next()) {
                forecast.setCity(resultSet.getString("city"));
                forecast.setWeather(resultSet.getString("weather"));
                forecast.setTemp(resultSet.getString("temp"));
                forecast.setLikeTemp(resultSet.getString("likeTemp"));
                forecast.setWindSpeed(resultSet.getString("windSpeed"));
                forecast.setWindDir(resultSet.getString("windDeg"));
                forecast.setClouds(resultSet.getString("clouds"));
                forecast.setPressure(resultSet.getString("pressure"));
                forecast.setHumidity(resultSet.getString("humidity"));
                result = true;
            }
            resultSet.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void addForecast(Forecast forecast) {
        String query = "INSERT INTO %s (date, request, resource, city, weather, temp, likeTemp, windSpeed, windDeg, clouds, pressure, humidity)" +
                "VALUES (TIMESTAMP '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
        String[] request = forecast.getRequest();
        try {
            storage.execute(String.format(query, tableName, LocalDateTime.now(), request[0], request[1], forecast.getCity(), forecast.getWeather(),
                    forecast.getTemp(), forecast.getLikeTemp(), forecast.getWindSpeed(), forecast.getWindDir(), forecast.getClouds(),
                    forecast.getPressure(), forecast.getHumidity()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getLastQuery(String city, String resource) throws SQLException {
        String query = "SELECT * FROM %s WHERE date > timestamp '%s' AND request = '%s' AND resource = '%s' ORDER BY id DESC LIMIT 1;";
        LocalDateTime time = LocalDateTime.now().minusHours(1);
        return storage.executeQuery(String.format(query, tableName, time, city, resource));
    }
}
