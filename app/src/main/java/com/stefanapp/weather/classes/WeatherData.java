package com.stefanapp.weather.classes;

import android.graphics.Bitmap;

import com.stefanapp.weather.interfaces.weather.OnWeatherFinishedListener;

import java.util.ArrayList;

/**
 * Created by stefanlam88 on 4/1/16.
 */
public class WeatherData {
    public Bitmap weatherIconUrl;
    public String weatherDesc;
    public String tempC;
    public String minTempC;
    public String maxTempC;
    public String currentLocation;
    public ArrayList<Hours> hoursArrayList;

    public WeatherData(){
        this.hoursArrayList = new ArrayList<Hours>();
    }

}
