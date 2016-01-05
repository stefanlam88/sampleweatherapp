package com.stefanapp.weather.classes;

import android.os.AsyncTask;

import com.stefanapp.weather.asynctask.WeatherAPI;
import com.stefanapp.weather.interfaces.weather.OnWeatherFinishedListener;
import com.stefanapp.weather.interfaces.weather.WeatherInteractor;


/**
 * Created by stefanlam88 on 29/12/15.
 */
public class WeatherInteractorImpl implements WeatherInteractor {

    private WeatherAPI mWeatherAPI;
    private Weather mWeather;

    public WeatherInteractorImpl(){
        mWeatherAPI = new WeatherAPI();
        mWeather = new Weather();
    }

    @Override
    public void getWeather(String query, String format, int numOfDays, OnWeatherFinishedListener listener) {

        mWeather.query = query;
        mWeather.format = format;
        mWeather.numOfDays = numOfDays;
        mWeather.listener = listener;

        mWeatherAPI.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mWeather);
    }
}
