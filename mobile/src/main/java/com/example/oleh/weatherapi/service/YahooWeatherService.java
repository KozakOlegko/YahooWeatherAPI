package com.example.oleh.weatherapi.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.oleh.weatherapi.WeatherActivity;
import com.example.oleh.weatherapi.data.Chanell;

/**
 * Created by Oleh on 03.10.2015.
 */
public class YahooWeatherService {
    private YahooWeatherCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(YahooWeatherCallback callback) {
        this.callback = callback;
    }


    public String getLocation() {
        return location;
    }

    public void refreshWeather(final String location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute(location);

    }
}
