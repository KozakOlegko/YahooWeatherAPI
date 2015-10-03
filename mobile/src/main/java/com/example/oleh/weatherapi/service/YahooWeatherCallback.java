package com.example.oleh.weatherapi.service;

import com.example.oleh.weatherapi.data.Chanell;

/**
 * Created by Oleh on 03.10.2015.
 */
public interface YahooWeatherCallback {
    void serviceSuccess(Chanell chanell);

    void serviceFailure(Exception exception);
}
