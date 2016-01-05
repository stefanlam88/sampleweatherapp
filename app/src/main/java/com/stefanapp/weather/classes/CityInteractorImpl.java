package com.stefanapp.weather.classes;

import android.content.Context;

import com.stefanapp.weather.interfaces.city.CityInteractor;
import com.stefanapp.weather.interfaces.city.OnCityFinishedListener;
import com.stefanapp.weather.utils.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stefanlam88 on 5/1/16.
 */
public class CityInteractorImpl implements CityInteractor {

    MyApplication mGlobV;
    ArrayList<City> cityArrayList;

    public CityInteractorImpl(Context mContext){
        mGlobV = (MyApplication)mContext.getApplicationContext();
    }
    @Override
    public void getCity(String cities, OnCityFinishedListener listener) {
        try {
            JSONArray obj = new JSONArray(cities);
            cityArrayList = new ArrayList<City>();

            for (int i = 0; i < obj.length(); i++) {
                JSONObject jo_inside = obj.getJSONObject(i);

                City city = new City();
                city.city =  jo_inside.getString("name");
                city.country = jo_inside.getString("country");
                if(mGlobV.getCacheCity() != null){
                    if(mGlobV.getCacheCity().contains(city.city) && mGlobV.getCacheCity().contains(city.country))
                        city.selected = true;
                    else
                        city.selected = false;
                }
                else{
                    city.selected = false;
                }

                cityArrayList.add(city);
                city = null;
            }

            listener.onSuccess(cityArrayList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
