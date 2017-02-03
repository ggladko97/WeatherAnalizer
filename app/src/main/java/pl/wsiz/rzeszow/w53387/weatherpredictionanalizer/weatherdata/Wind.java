package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by ggladko97 on 17.01.17.
 */
public class Wind implements JSONPopulator {
    private double windSpeed;

    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public void populate(JSONObject object) {
        Log.i("WInd",object.toString());
        windSpeed = object.optInt("speed");
    }
}
