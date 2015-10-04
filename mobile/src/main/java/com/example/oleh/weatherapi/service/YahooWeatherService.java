package com.example.oleh.weatherapi.service;

import android.net.Uri;
import android.os.AsyncTask;
import com.example.oleh.weatherapi.data.Chanell;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

    public void refreshWeather(String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputstream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!=null) {
                        result.append(line);
                    }

                    return result.toString();

                } catch (MalformedURLException e) {
                    error =e;
                } catch (IOException e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null){
                    callback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResult = data.optJSONObject("query");
                    int count = queryResult.optInt("count");

                    if (count ==0)
                    {
                        callback.serviceFailure(new LocalWeatherException("No weather details for this location"+location));
                        return;
                    }

                    Chanell chanell = new Chanell();
                    chanell.populate(queryResult.optJSONObject("result").optJSONObject("chanell"));

                    callback.serviceSuccess(chanell);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(location);


    }
    public class LocalWeatherException extends Exception
    {
        public LocalWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
