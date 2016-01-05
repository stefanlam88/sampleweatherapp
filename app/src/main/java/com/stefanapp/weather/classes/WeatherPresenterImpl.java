package com.stefanapp.weather.classes;

import com.stefanapp.weather.interfaces.weather.OnWeatherFinishedListener;
import com.stefanapp.weather.interfaces.weather.WeatherInteractor;
import com.stefanapp.weather.interfaces.weather.WeatherPresenter;
import com.stefanapp.weather.interfaces.weather.WeatherView;

/**
 * Created by stefanlam88 on 29/12/15.
 */
public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherFinishedListener {

    private WeatherView mWeatherView;
    private WeatherInteractor weatherInteractor;

    public WeatherPresenterImpl(WeatherView weatherView) {
        this.mWeatherView = weatherView;
        this.weatherInteractor = new WeatherInteractorImpl();
    }

    @Override
    public void getWeatherAPI(String query, String format, int numOfDays) {
        mWeatherView.showProgress();
        weatherInteractor.getWeather(query, format, numOfDays, this);
    }

    @Override
    public void onError() {
        mWeatherView.hideProgress();
    }

    @Override
    public void onFail() {
        mWeatherView.hideProgress();
    }

    @Override
    public void onSuccess(WeatherData result) {
        mWeatherView.success(result);
    }
}
