package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ggladko97 on 14.10.16.
 */
public class Chanell implements JSONPopulator {
    private Item item;
    private Units units;
    private Wind wind;
    private Atmosphere atmosphere;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    public Wind getWind(){ return wind; }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @Override
    public void populate(JSONObject object) throws JSONException {
        units=new Units();
        units.populate(object.optJSONObject("units"));


        Log.i("Chanell",object.toString());

        item = new Item();
        item.populate(object.optJSONObject("item"));

        wind = new Wind();
        wind.populate(object.optJSONObject("wind"));

        atmosphere = new Atmosphere();
        atmosphere.populate(object.optJSONObject("atmosphere"));


    }
}
