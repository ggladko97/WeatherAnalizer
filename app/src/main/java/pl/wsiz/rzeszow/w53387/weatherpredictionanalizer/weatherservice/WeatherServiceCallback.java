package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherservice;

import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata.Chanell;

/**
 * Created by ggladko97 on 14.10.16.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Chanell chanell);
    void serviceFailed(Exception exception);
}
