package dao;

import model.Forecast;

public interface Service {
    Forecast getForecast(String request, String resource);
    void addForecast(Forecast forecast);
}
