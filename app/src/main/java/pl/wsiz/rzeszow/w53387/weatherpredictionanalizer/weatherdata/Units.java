package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import org.json.JSONObject;

/**
 * Created by ggladko97 on 14.10.16.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject object) {
        temperature=object.optString("temperature");

    }
}
