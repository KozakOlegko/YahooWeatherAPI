package com.example.oleh.weatherapi.data;

import org.json.JSONObject;

/**
 * Created by Oleh on 03.10.2015.
 */
public class Condition implements JSONPopulator {

    private int code;
    private int temparature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemparature() {
        return temparature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temparature = data.optInt("temp");
        description = data.optString("description");
    }
}
