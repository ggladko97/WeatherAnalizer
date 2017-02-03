package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import org.json.JSONObject;

/**
 * Created by ggladko97 on 14.10.16.
 */
public class Condition implements JSONPopulator {
    private  int code;
    private int temperature;
    private String date;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void populate(JSONObject object) {
        code = object.optInt("code");
        temperature = object.optInt("temp");
        date=object.optString("date");

    }
}
