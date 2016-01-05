package com.stefanapp.weather.interfaces.city;

import com.stefanapp.weather.classes.City;

import java.util.ArrayList;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface CityView {

    public void setError();

    public void setSuccess(ArrayList<City> cityArrayList);
}
