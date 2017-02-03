package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ggladko97 on 14.10.16.
 */
public class Item implements JSONPopulator {
    private Condition condition;
    private Forecast forecast,forecast2;

    public Forecast getForecast() {
        return forecast;
    }

    public Forecast getForecast2() {
        return forecast2;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject object) throws JSONException {
        condition = new Condition();
        condition.populate(object.optJSONObject("condition"));

        forecast = new Forecast();
        forecast2 = new Forecast();

        //forecast.populate(object.optJSONObject("forecast"));
        JSONArray arr = object.optJSONArray("forecast");

//        for(int i=0; i<2; i++){
        Log.i("ItemForecastArray",arr.toString());

        forecast.populate(arr.optJSONObject(1));
        forecast2.populate(arr.optJSONObject(2));
       // }

    }
}
