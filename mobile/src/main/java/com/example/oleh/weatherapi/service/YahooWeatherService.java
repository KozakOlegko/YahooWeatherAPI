package com.example.oleh.weatherapi.service;

import android.os.AsyncTask;

import com.example.oleh.weatherapi.WeatherActivity;
import com.example.oleh.weatherapi.data.Chanell;

/**
 * Created by Oleh on 03.10.2015.
 */
public class YahooWeatherService {
    private YahooWeatherCallback callback;
    private String location;

    public YahooWeatherService(YahooWeatherCallback callback) {
        this.callback = callback;
    }

    public YahooWeatherService(WeatherActivity weatherActivity) {
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute(location);

    }
}
