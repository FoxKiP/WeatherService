package dao;

import model.Forecast;

public interface Service {
    boolean fillForecast(Forecast forecast);
    void addForecast(Forecast forecast);
}
