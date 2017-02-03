package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.insertion.BackgroundHelper;
import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata.Atmosphere;
import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata.Chanell;
import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata.Item;
import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherdata.Wind;
import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherservice.WeatherServiceCallback;
import pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.weatherservice.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    Button btnSubmit, btnLoadFromYahoo,btnSaveToDatabase,btnRecieve, btnGetAverage,btnAnalize;
    ListView listViewCities;
    EditText editTextInput, editTextParameter,editText2;
    ArrayList<String> cityNames;
    ArrayList<Weather> weatherList;
    ArrayAdapter<String> listAdapter;
    Weather weather;


    private YahooWeatherService service;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNames = new ArrayList<>();
        weatherList = new ArrayList<>();


        service = new YahooWeatherService(this);

        btnSubmit = (Button)findViewById(R.id.buttonSubmit);
        btnRecieve = (Button)findViewById(R.id.btnRecieve);
        btnLoadFromYahoo = (Button)findViewById(R.id.buttonSend);
        btnSaveToDatabase = (Button)findViewById(R.id.btnSendPHP);
        btnGetAverage = (Button)findViewById(R.id.btnGetAvg);
        btnAnalize = (Button)findViewById(R.id.btnGetInsertAnalize);
        listViewCities = (ListView) findViewById(R.id.listView);
        editTextInput = (EditText)findViewById(R.id.editText);
        editTextParameter = (EditText)findViewById(R.id.editTextParameter);
        editText2 = (EditText)findViewById(R.id.editText2);

        listAdapter =
                new ArrayAdapter<String>(MainActivity.this,R.layout.simple_list_item_1,cityNames);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cityNames.add(editTextInput.getText().toString());
               editTextInput.setText("");
               updateList(listViewCities,listAdapter);
           }
       });
        btnLoadFromYahoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading data...");
                progressDialog.show();
                //if(cityNames.size()>1){
                    for(String location:cityNames){
                        service.refreshWeather(location);

                    }
                for(Weather weather: weatherList){
                    //Log.i("insideList",weather.toString());
                }
            }
        });
        btnRecieve.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String param = editTextParameter.getText().toString();
                BackgroundHelper helper = new BackgroundHelper();
                editTextParameter.setText("");
                helper.execute("weather","weather","table",param,"","");
            }
        });
        btnAnalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param = editTextParameter.getText().toString();
                BackgroundHelper helper = new BackgroundHelper();
                editTextParameter.setText("");
                String paramDate = editText2.getText().toString();
                helper.execute("weather","analizer","cityanalize",param,"dateanalize"+paramDate);
            }
        });
        btnSaveToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weather!=null){
                    Weather _weather = weather;
                    //Log.i("weathertothrow",_weather.getCurrentDate());
                    double windspeed = _weather.getWindspeed();
                    String city = _weather.getCity();
                    int current_code = _weather.getCurrent_code();
                    int humidity = _weather.getHumidity();
                    int currentTemperature = _weather.getCurrentTemperature();
                    String current_date = _weather.getCurrentDate();
                    StringBuilder sb = new StringBuilder(current_date);
                    sb.delete(16,sb.capacity());
                    sb.delete(0,5);
                    current_date = sb.toString();
                    try {
                        current_date = changeMonths(current_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    int Pred_codeTomorrow= _weather.getPred_codeTomorrow();
                    int pred_codeAfterTomor = _weather.getPred_codeAFTERTomorrow();
                    String pred_data_tomorrow = _weather.getPred_data_tomorrow();
                    //Log.i("weathertothrowPred",pred_data_tomorrow);
                    try {
                        pred_data_tomorrow = changeMonths(pred_data_tomorrow);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String pred_data_after_tomm = _weather.getPred_data_aftertomorrow();

                    try {
                        pred_data_after_tomm = changeMonths(pred_data_after_tomm);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    int pred_temp_low_tomm = _weather.getPred_temperatureLowyTomorrow();
                    int pred_temp_low_after_tomm = _weather.getPred_temperatureLowyAFTERTomorrow();
                    int pred_temp_high_tomm = _weather.getPred_temperatureHighTomorrow();
                    int pred_temp_high_after_tomm = _weather.getPred_temperatureHighAFTERTomorrow();

//                    BackgroundHelper helperGet = new BackgroundHelper();
//                    helperGet.execute("weather");

                    BackgroundHelper helper = new BackgroundHelper(MainActivity.this,city,current_date,windspeed,humidity,
                            current_code,currentTemperature,pred_temp_low_tomm,pred_temp_high_tomm,
                            Pred_codeTomorrow,pred_temp_low_after_tomm,
                            pred_temp_high_after_tomm,pred_codeAfterTomor,
                            pred_data_tomorrow,pred_data_after_tomm);
                    helper.execute("");
                }else{
                 Log.i("sorry","sorry");
                }
               // }

            }
        });
        btnGetAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param = editTextParameter.getText().toString();
                BackgroundHelper helper = new BackgroundHelper();
                editTextParameter.setText("");
                helper.execute("weather","average","date",param,"","");
            }
        });


    }
    private void updateList(ListView listViewCities,ArrayAdapter<String>listAdapter){
        listViewCities.setAdapter(listAdapter);
    }
    private String changeMonths(String input) throws ParseException {
        StringBuilder sb = new StringBuilder(input);
        String month = sb.substring(4,6);
        String outMonth = null;
        switch (month){
            case "Jan":
                outMonth="01";
                break;
            case "Feb":
                outMonth="02";
                break;
            case "Mar":
                outMonth="03";
                break;
            case "Apr":
                outMonth="04";
                break;
            case "May":
                outMonth="05";
                break;
            case "Jun":
                outMonth="06";
                break;
            case "Jul":
                outMonth="07";
                break;
            case "Aug":
                outMonth="08";
                break;
            case "Sep":
                outMonth="09";
                break;
            case "Oct":
                outMonth="10";
                break;
            case "Nov":
                outMonth="11";
                break;
            case "Dec":
                outMonth="12";
                break;
            default:
                outMonth="01";
                break;
        }

        sb.delete(3,7);
        sb.insert(2,"/"+outMonth+"/");
       // Log.i("SBafter",sb.toString());

        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";
        String newDateString=null;


        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(sb.toString());

        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
      //  Log.i("SBafter",newDateString);


        return newDateString;
    }

    @Override
    public void serviceSuccess(Chanell chanell) {
         weather = new Weather();
        progressDialog.hide();

        Item item = chanell.getItem();
        Atmosphere atmosphere =  chanell.getAtmosphere();
        Wind wind = chanell.getWind();

        double windspeed = wind.getWindSpeed();
        int currentTemperature = item.getCondition().getTemperature();
        int current_code = item.getCondition().getCode();
        int humidity = atmosphere.getHumidity();
        String currentDate = item.getCondition().getDate();
        String city = service.getLocation();

        Log.i("WeatherInsider: ",windspeed+" "+current_code+" "+humidity+" "+currentDate+" "+city+"prediction:"+
        " "+item.getForecast().getCode()+" "+item.getForecast2().getCode());

        weather.setWindspeed(windspeed);
        weather.setCity(city);
        weather.setCurrent_code(current_code);
        weather.setHumidity(humidity);
        weather.setCurrentDate(currentDate);
        weather.setCurrentTemperature(currentTemperature);


        weather.setPred_codeTomorrow(item.getForecast().getCode());
        weather.setPred_codeAFTERTomorrow(item.getForecast2().getCode());

        weather.setPred_data_tomorrow(item.getForecast().getData());
        weather.setPred_data_aftertomorrow(item.getForecast2().getData());

        weather.setPred_temperatureLowyTomorrow(item.getForecast().getTemperatureLow());
        weather.setPred_temperatureLowyAFTERTomorrow(item.getForecast2().getTemperatureLow());

        weather.setPred_temperatureHighTomorrow(item.getForecast().getTemperatureHigh());
        weather.setPred_temperatureHighAFTERTomorrow(item.getForecast2().getTemperatureHigh());
        Log.i("WEATHEROBJ",weather.toString());

        //weatherList.add(weather);
        //Log.i("WEATHEROBJ",weatherList.toString());
    }

    @Override
    public void serviceFailed(Exception exception) {
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
        progressDialog.hide();
       // Log.i("WEATHEROBJ",weather.toString());
    }

}
