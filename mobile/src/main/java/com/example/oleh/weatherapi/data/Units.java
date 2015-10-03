package com.example.oleh.weatherapi.data;

import org.json.JSONObject;

/**
 * Created by Oleh on 03.10.2015.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {

        temperature = data.optString("temperature");

    }
}

