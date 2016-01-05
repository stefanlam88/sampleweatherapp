package com.stefanapp.weather.interfaces.city;

import com.stefanapp.weather.classes.City;

import java.util.ArrayList;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface OnCityFinishedListener {

    public void onError();

    public void onSuccess(ArrayList<City> cityArrayList);
}
