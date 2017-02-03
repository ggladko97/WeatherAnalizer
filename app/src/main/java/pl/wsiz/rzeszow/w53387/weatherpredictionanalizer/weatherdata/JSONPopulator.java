package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ggladko97 on 14.10.16.
 */
public interface JSONPopulator {
     void populate(JSONObject object) throws JSONException;
}
