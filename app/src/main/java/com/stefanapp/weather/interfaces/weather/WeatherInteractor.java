package com.stefanapp.weather.interfaces.weather;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface WeatherInteractor {
    public void getWeather(String query, String format, int numOfDays, OnWeatherFinishedListener listener);
}
