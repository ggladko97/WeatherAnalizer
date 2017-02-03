package pl.wsiz.rzeszow.w53387.weatherpredictionanalizer;

/**
 * Created by ggladko97 on 17.01.17.
 */
public class Weather {
    private String city;
    private String currentDate;
    private double windspeed;
    private int humidity;
    private int currentTemperature;
    private int current_code;

    private int pred_temperatureLowyTomorrow;
    private int pred_temperatureHighTomorrow;
    private int pred_codeTomorrow;
    private int pred_temperatureLowyAFTERTomorrow;
    private int pred_temperatureHighAFTERTomorrow;
    private int pred_codeAFTERTomorrow;
    private String pred_data_tomorrow;
    private String pred_data_aftertomorrow;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCurrent_code() {
        return current_code;
    }

    public void setCurrent_code(int current_code) {
        this.current_code = current_code;
    }

    public int getPred_temperatureLowyTomorrow() {
        return pred_temperatureLowyTomorrow;
    }

    public void setPred_temperatureLowyTomorrow(int pred_temperatureLowyTomorrow) {
        this.pred_temperatureLowyTomorrow = pred_temperatureLowyTomorrow;
    }

    public int getPred_temperatureHighTomorrow() {
        return pred_temperatureHighTomorrow;
    }

    public void setPred_temperatureHighTomorrow(int pred_temperatureHighTomorrow) {
        this.pred_temperatureHighTomorrow = pred_temperatureHighTomorrow;
    }

    public int getPred_codeTomorrow() {
        return pred_codeTomorrow;
    }

    public void setPred_codeTomorrow(int pred_codeTomorrow) {
        this.pred_codeTomorrow = pred_codeTomorrow;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getPred_temperatureLowyAFTERTomorrow() {
        return pred_temperatureLowyAFTERTomorrow;
    }

    public void setPred_temperatureLowyAFTERTomorrow(int pred_temperatureLowyAFTERTomorrow) {
        this.pred_temperatureLowyAFTERTomorrow = pred_temperatureLowyAFTERTomorrow;
    }

    public int getPred_temperatureHighAFTERTomorrow() {
        return pred_temperatureHighAFTERTomorrow;
    }

    public void setPred_temperatureHighAFTERTomorrow(int pred_temperatureHighAFTERTomorrow) {
        this.pred_temperatureHighAFTERTomorrow = pred_temperatureHighAFTERTomorrow;
    }

    public int getPred_codeAFTERTomorrow() {
        return pred_codeAFTERTomorrow;
    }

    public void setPred_codeAFTERTomorrow(int pred_codeAFTERTomorrow) {
        this.pred_codeAFTERTomorrow = pred_codeAFTERTomorrow;
    }

    public String getPred_data_tomorrow() {
        return pred_data_tomorrow;
    }

    public void setPred_data_tomorrow(String pred_data_tomorrow) {
        this.pred_data_tomorrow = pred_data_tomorrow;
    }

    public String getPred_data_aftertomorrow() {
        return pred_data_aftertomorrow;
    }

    public void setPred_data_aftertomorrow(String pred_data_aftertomorrow) {
        this.pred_data_aftertomorrow = pred_data_aftertomorrow;
    }
}
