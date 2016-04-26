package com.example.dell.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.dell.weather.Details.WeatherDetails;

public class weatherActivity extends AppCompatActivity  {

    public static interface parsingCallBack{
        public void resultFetched();
    }
    public TextView Latitude;
    private TextView Longitude;
    private TextView Temperature;
    private TextView Temp_max;
    private TextView Temp_min;
    private TextView Country;
    private String LOG_TAG = weatherActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Latitude = (TextView) findViewById(R.id.latitude);
        Longitude = (TextView) findViewById(R.id.longitude);
        Temperature = (TextView) findViewById(R.id.temperature);
        Temp_max = (TextView) findViewById(R.id.temp_max);
        Temp_min = (TextView) findViewById(R.id.temp_min);
        Country = (TextView) findViewById(R.id.country);

        WeatherJsonParsing jsonParsing = new WeatherJsonParsing();
        jsonParsing.getWeatherDetails();
        jsonParsing.execute();



    }

    public class DownloadWeather extends AsyncTask<String, Void, WeatherDetails > {
        @Override
        protected WeatherDetails doInBackground(String... params) {
            WeatherDetails weatherDetails = new WeatherDetails();
            WeatherJsonParsing jsonParsing = new WeatherJsonParsing();
            jsonParsing.execute();
            return weatherDetails;
        }

        @Override
        protected void onPostExecute(WeatherDetails weatherDetails) {
            super.onPostExecute(weatherDetails);
            Latitude.setText( weatherDetails.getLat());
            Longitude.setText( weatherDetails.getLat());
            Temperature.setText("Temperature " + weatherDetails.getTemp());
            Temp_max.setText("Temp_max " + weatherDetails.getTemp_max());
            Temp_min.setText("Temp_min " + weatherDetails.getTemp_min());
            Country.setText("Country " + weatherDetails.getCountry());
        }



    }











}
