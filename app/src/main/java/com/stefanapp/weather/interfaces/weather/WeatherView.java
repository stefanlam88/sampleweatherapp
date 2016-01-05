package com.stefanapp.weather.interfaces.weather;

import com.stefanapp.weather.classes.WeatherData;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public interface WeatherView {

    public void showProgress();

    public void hideProgress();

    public void setError();

    public void success(WeatherData result);
}
