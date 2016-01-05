package com.stefanapp.weather.classes;

import android.content.Context;

import com.stefanapp.weather.interfaces.city.CityInteractor;
import com.stefanapp.weather.interfaces.city.CityPresenter;
import com.stefanapp.weather.interfaces.city.CityView;
import com.stefanapp.weather.interfaces.city.OnCityFinishedListener;
import com.stefanapp.weather.interfaces.weather.WeatherInteractor;
import com.stefanapp.weather.interfaces.weather.WeatherView;

import java.util.ArrayList;

/**
 * Created by stefanlam88 on 5/1/16.
 */
public class CityPresenterImpl implements CityPresenter, OnCityFinishedListener {

    private CityView mCityView;
    private CityInteractor cityInteractor;

    public CityPresenterImpl(CityView cityView, Context mContext){
        this.mCityView = cityView;
        this.cityInteractor = new CityInteractorImpl(mContext);
    }

    @Override
    public void getCity(String cities) {
        cityInteractor.getCity(cities, this);
    }

    @Override
    public void onError() {
        mCityView.setError();
    }

    @Override
    public void onSuccess(ArrayList<City> cityArrayList) {
        mCityView.setSuccess(cityArrayList);
    }
}
