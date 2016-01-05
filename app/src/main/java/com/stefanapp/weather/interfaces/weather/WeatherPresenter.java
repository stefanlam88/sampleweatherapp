package com.stefanapp.weather.interfaces.weather;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface WeatherPresenter {

    public void getWeatherAPI(String query, String format, int numOfDays);
}
