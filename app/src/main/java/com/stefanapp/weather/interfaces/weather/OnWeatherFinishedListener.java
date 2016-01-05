package com.stefanapp.weather.interfaces.weather;

import com.stefanapp.weather.classes.WeatherData;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface OnWeatherFinishedListener {

    public void onError();

    public void onFail();

    public void onSuccess(WeatherData result);
}
