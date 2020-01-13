package strategies;

import model.Forecast;

import java.io.IOException;

public interface Strategy {
    boolean fillForecast(Forecast forecast) throws IOException;
}
