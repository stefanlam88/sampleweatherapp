package com.stefanapp.weather.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by stefanlam88 on 4/1/16.
 */
public class MyApplication extends Application {
    private SharedPreferences prefs;
    private static MyApplication singleton;

    public static final String CACHE_CITY = "CACHE_CITY";
    public static final String IS_CACHE = "IS_CACHE";

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        singleton = this;
    }

    public SharedPreferences getApplicationPreferences() {
        return prefs;
    }

    public void setCacheCity(String city) {
        SharedPreferences prefs = ((MyApplication) getApplicationContext()).getApplicationPreferences();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(MyApplication.CACHE_CITY, city);
        editor.commit();
    }

    public String getCacheCity() {
        String city = prefs.getString(CACHE_CITY, null);
        return city;
    }

    public void setCache(String city) {
        SharedPreferences prefs = ((MyApplication) getApplicationContext()).getApplicationPreferences();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(MyApplication.IS_CACHE, city);
        editor.commit();
    }

    public String getCache() {
        String city = prefs.getString(IS_CACHE, "n");
        return city;
    }
}
