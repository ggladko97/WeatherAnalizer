package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherservice;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata.Chanell;

/**
 * Created by ggladko97 on 14.10.16.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }
    public void refreshWeather(String l){
        location=l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                if(s==null&& error!=null){
                    callback.serviceFailed(error);
                    return;
                }
                super.onPostExecute(s);
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject query = data
                            .optJSONObject("query");

                    int count = query.optInt("count");

                    if(count == 0){
                        callback.serviceFailed(new LocationWeatherException("No weather info has been found for this place "+location));
                        return;
                    }

                    Chanell chanell = new Chanell();
                    Log.i("JSON",query.optJSONObject("results").optJSONObject("channel").toString());
                    chanell.populate(query.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSuccess(chanell);



                } catch (JSONException e) {
                    callback.serviceFailed(e);

                }
            }

            @Override
            protected String doInBackground(String... params) {

                String BASE_URL = "http://query.yahooapis.com/v1/public/yql";
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",params[0]);
                String endpoint = BASE_URL + "?q=" + Uri.encode(YQL) + "&format=json";

                Log.i("QUERY",endpoint);


                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();

                    InputStream is = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    StringBuilder result = new StringBuilder();

                    String line;
                    while((line = reader.readLine())!=null){
                        result.append(line);

                    }
                    Log.i("line",result.toString());
                    return result.toString();
                } catch (Exception e) {
                    error=e;

                }
                return null;
            }
        }.execute(location);



    }
    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
