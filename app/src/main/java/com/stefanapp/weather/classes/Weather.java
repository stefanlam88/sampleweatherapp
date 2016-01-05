package com.stefanapp.weather.classes;

import com.stefanapp.weather.interfaces.weather.OnWeatherFinishedListener;

/**
 * Created by stefanlam88 on 4/1/16.
 */
public class Weather {
    public String query;
    public String format;
    public int numOfDays;
    public OnWeatherFinishedListener listener;

}
