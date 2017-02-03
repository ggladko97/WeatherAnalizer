package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import org.json.JSONObject;

/**
 * Created by ggladko97 on 17.01.17.
 */
public class PredictionDay implements JSONPopulator{

    private int temperatureLow;
    private int temperatureHigh;
    private int code;
    private String data;

    public int getTemperatureHigh() {
        return temperatureHigh;
    }

    public int getTemperatureLow() {
        return temperatureLow;
    }

    public int getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    @Override
    public void populate(JSONObject object) {
        temperatureLow = object.optInt("low");
        temperatureHigh = object.optInt("high");
        code = object.optInt("code");
        data = object.optString("date");

    }
}
