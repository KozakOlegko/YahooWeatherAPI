package com.example.oleh.weatherapi;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oleh.weatherapi.data.Chanell;
import com.example.oleh.weatherapi.data.Item;
import com.example.oleh.weatherapi.service.YahooWeatherCallback;
import com.example.oleh.weatherapi.service.YahooWeatherService;

public class WeatherActivity extends ActionBarActivity implements YahooWeatherCallback {

    private ImageView weatherImageView;
    private TextView temparatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherImageView = (ImageView)findViewById(R.id.WeatherImageView);
        temparatureTextView = (TextView)findViewById(R.id.TemperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.ConditionTextView);
        locationTextView = (TextView)findViewById(R.id.LocationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading");
        dialog.show();

        service.refreshWeather("Austin, TX");
    }
      @Override
    public void serviceSuccess(Chanell chanell) {
        dialog.hide();

          Item item = chanell.getItem();
          int resourceID = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

          Drawable weatherIconDrawable = getResources().getDrawable(resourceID);
          weatherImageView.setImageDrawable(weatherIconDrawable);

          temparatureTextView.setText(item.getCondition().getTemparature()+ "\u00B0 "+chanell.getUnits().getTemperature());
          conditionTextView.setText(item.getCondition().getDescription());
          locationTextView.setText(service.getLocation());


    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }



}
