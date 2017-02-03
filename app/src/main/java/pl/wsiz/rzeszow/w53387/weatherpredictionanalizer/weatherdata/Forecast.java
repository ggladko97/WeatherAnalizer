package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by ggladko97 on 17.01.17.
 */
public class Forecast implements JSONPopulator {
//    private PredictionDay day1,day2;
//
//    public PredictionDay getDay1() {
//        return day1;
//    }
//
//    public PredictionDay getDay2() {
//        return day2;
//    }

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
//        day1 = new PredictionDay();
//        day2 = new PredictionDay();
//
//        day1.populate();
//        day2.populate(object.optJSONObject("2"));
        Log.i("Forecast",object.toString());
        temperatureLow = object.optInt("low");
        temperatureHigh = object.optInt("high");
        code = object.optInt("code");
        data = object.optString("date");

    }
}
