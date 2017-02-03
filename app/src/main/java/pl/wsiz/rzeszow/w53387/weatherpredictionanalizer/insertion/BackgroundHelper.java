package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer.insertion;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * Created by ggladko97 on 01.11.16.
 */
public class BackgroundHelper extends AsyncTask<String, Void, String> {
    Context context;

    String login =null;
    boolean flag = true;
    //AlertDialog alertDialog;
    String city;
    String currentDate;
    double windspeed;
    int humidity;
    int current_code;
    int currentTemperature;

    int pred_temperatureLowyTomorrow;
    int pred_temperatureHighTomorrow;
    int pred_codeTomorrow;
    int pred_temperatureLowyAFTERTomorrow;
    int pred_temperatureHighAFTERTomorrow;
    int pred_codeAFTERTomorrow;
    String pred_data_tomorrow;
    String pred_data_aftertomorrow;

    public BackgroundHelper() {
    }

    public BackgroundHelper(Context context, String city, String currentDate, double windspeed, int humidity,
                            int current_code, int currentTemperature, int pred_temperatureLowyTomorrow,
                            int pred_temperatureHighTomorrow, int pred_codeTomorrow,
                            int pred_temperatureLowyAFTERTomorrow, int pred_temperatureHighAFTERTomorrow,
                            int pred_codeAFTERTomorrow, String pred_data_tomorrow, String pred_data_aftertomorrow) {
        this.context = context;
        this.city = city;
        this.currentDate = currentDate;
        this.windspeed = windspeed;
        this.humidity = humidity;
        this.current_code = current_code;
        this.currentTemperature = currentTemperature;
        this.pred_temperatureLowyTomorrow = pred_temperatureLowyTomorrow;
        this.pred_temperatureHighTomorrow = pred_temperatureHighTomorrow;
        this.pred_codeTomorrow = pred_codeTomorrow;
        this.pred_temperatureLowyAFTERTomorrow = pred_temperatureLowyAFTERTomorrow;
        this.pred_temperatureHighAFTERTomorrow = pred_temperatureHighAFTERTomorrow;
        this.pred_codeAFTERTomorrow = pred_codeAFTERTomorrow;
        this.pred_data_tomorrow = pred_data_tomorrow;
        this.pred_data_aftertomorrow = pred_data_aftertomorrow;
    }

    @Override
    protected String doInBackground(String... params) {
        //Possible parameters are in comments
        String typeParametr = params[0];//weather
        String paramDetails  = params[1];//weather/average/analizer
        String addtional = params[2];//table/date/cityanalize
        String additonalParam = params[3];//table name/date [date datatype]/city [string datatype]
        String dateAnalizeParam = params[4];
        String dateAnalizeResult = null;
        if(dateAnalizeParam.equals("dateanalize")){
            dateAnalizeResult = params[5];
        }



        String urlGet = "http://192.168.0.106:96/downloadData.php?"+typeParametr+"="+paramDetails;

        if(Objects.equals(typeParametr, "")){
            flag=true;
        }else if(Objects.equals(typeParametr, "weather")){
            flag=false;
        }else{flag=false;}
        Log.i("FLAG EBANII", String.valueOf(flag));

        if(flag) {
            login = "http://192.168.0.106:96/downloadData.php";

        }else{
            login = "http://192.168.0.106:96/downloadData.php?weather"+"="+paramDetails+"&"+addtional+"="+additonalParam;
            if(dateAnalizeParam.equals("dateanalize")){
                login = "http://192.168.0.106:96/downloadData.php?weather"+"="+paramDetails+"&"+addtional+"="+additonalParam+"&"+dateAnalizeParam+"="+dateAnalizeResult;
            }
           // login = "http://192.168.0.106:96/downloadData.php?weather=analizer&cityanalize='pert'&dateanalize='2017/01/22'";

        }
        Log.i("Login: ",login);
        //send get but php recognizes as post




//        if(type.equals("login")){
            try {
                Log.i("doInBAck", "Im in try block");
                URL url = new URL(login);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if(flag){
                connection.setRequestMethod("POST");
                }else{
                    connection.setRequestMethod("GET");
                }


                Log.i("Conn method: ",connection.getRequestMethod());
//

                if(flag){
                connection.setDoInput(true);
                connection.setDoOutput(true);
                }

                if(flag){
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String posted_data = URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&"
                        + URLEncoder.encode("currentDate", "UTF-8") + "=" + URLEncoder.encode(currentDate, "UTF-8") + "&" + URLEncoder
                        .encode("windspeed", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(windspeed), "UTF-8") + "&" + URLEncoder
                        .encode("currentTemperature", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(currentTemperature), "UTF-8") + "&" + URLEncoder
                        .encode("humidity", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(humidity), "UTF-8") + "&" + URLEncoder
                        .encode("current_code", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(current_code), "UTF-8") + "&" + URLEncoder
                        .encode("pred_temperatureLowyTomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_temperatureLowyTomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_temperatureHighTomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_temperatureHighTomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_codeTomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_codeTomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_temperatureLowyAFTERTomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_temperatureLowyAFTERTomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_temperatureHighAFTERTomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_temperatureHighAFTERTomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_codeAFTERTomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_codeAFTERTomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_data_tomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_data_tomorrow), "UTF-8") + "&" + URLEncoder
                        .encode("pred_data_aftertomorrow", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pred_data_aftertomorrow), "UTF-8");
                bufferedWriter.write(posted_data);
                Log.i("doInBAck", "Ive probably sent data to php file");
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();}else{}

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String recieved_data = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    recieved_data += line;
                }
                Log.i("recievedData"+typeParametr, recieved_data);

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return recieved_data;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //}
        return null;
    }

    @Override
    protected void onPreExecute() {
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Insertion to php result: ");
    }

    @Override
    protected void onPostExecute(String aVoid) {
//        Log.i("Message from php:",aVoid);
        //alertDialog.setMessage(aVoid);
       // alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
