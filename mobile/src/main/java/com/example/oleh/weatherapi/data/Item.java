package com.example.oleh.weatherapi.data;

import org.json.JSONObject;

/**
 * Created by Oleh on 03.10.2015.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("Condition"));

    }
}
