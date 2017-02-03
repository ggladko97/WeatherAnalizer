package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import org.json.JSONObject;

/**
 * Created by ggladko97 on 17.01.17.
 */
public class Atmosphere implements JSONPopulator{

    private int humidity;

    public int getHumidity() {
        return humidity;
    }

    @Override
    public void populate(JSONObject object) {
        humidity = object.optInt("humidity");
    }
}
