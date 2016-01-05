package com.stefanapp.weather.interfaces.city;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface CityInteractor {
    public void getCity(String cities, OnCityFinishedListener listener);
}
