package com.stefanapp.weather.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stefanapp.weather.R;
import com.stefanapp.weather.adapters.HoursViewAdapter;
import com.stefanapp.weather.classes.Hours;
import com.stefanapp.weather.classes.WeatherData;
import com.stefanapp.weather.classes.WeatherPresenterImpl;
import com.stefanapp.weather.interfaces.weather.WeatherPresenter;
import com.stefanapp.weather.interfaces.weather.WeatherView;
import com.stefanapp.weather.utils.Const;
import com.stefanapp.weather.utils.MyApplication;
import com.stefanapp.weather.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WeatherView, OnClickListener {

    Context mContext;
    ProgressBar mProgressBar;
    private WeatherPresenter mWeatherPresenter;
    TextView mMinValue, mMaxValue, mWeatherDescription, mCurrentTemp, mRequestedLocation;
    ImageView mWeatherIcon;
    TextView title;
    RelativeLayout mLoadingWrapper;
    RecyclerView mHourlyView;
    HoursViewAdapter adapter;
    ArrayList<Hours> mHourArrayList;
    MyApplication mGlobV;
    ImageView mEditCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mMinValue = (TextView) findViewById(R.id.txtMinWeather);
        mMaxValue = (TextView) findViewById(R.id.txtMaxWeather);
        mWeatherDescription = (TextView) findViewById(R.id.txtWeatherDesc);
        mCurrentTemp = (TextView) findViewById(R.id.txtWeather);
        mRequestedLocation = (TextView) findViewById(R.id.txtCurrentLocation);
        mLoadingWrapper = (RelativeLayout) findViewById(R.id.rlayout_loading);
        mWeatherIcon = (ImageView) findViewById(R.id.imgCurrentWeatherIcon);
        title = (TextView) findViewById(R.id.title);
        mHourlyView = (RecyclerView) findViewById(R.id.hourly_recycler_view);
        mEditCity = (ImageView)findViewById(R.id.img_edit);
        mGlobV = (MyApplication) this.getApplicationContext();


        title.setText("Weather");
        mContext = this;

        mHourArrayList = new ArrayList<Hours>();

        mHourlyView.setLayoutManager(new GridLayoutManager(mContext, 1));
        adapter = new HoursViewAdapter(this, mHourArrayList);
        mHourlyView.setAdapter(adapter);

        mWeatherPresenter = new WeatherPresenterImpl(this);
        mWeatherPresenter.getWeatherAPI(mGlobV.getCacheCity(), Const.REQUEST_FORMAT, Const.NUMBER_OF_DAYS);

        mEditCity.setOnClickListener(this);

    }

    @Override
    public void showProgress() {
        mLoadingWrapper.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingWrapper.setVisibility(View.GONE);
    }

    @Override
    public void setError() {
        mLoadingWrapper.setVisibility(View.GONE);
    }

    @Override
    public void success(final WeatherData result) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                mHourArrayList.clear();
                mHourArrayList.addAll(result.hoursArrayList);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mLoadingWrapper.setVisibility(View.GONE);
                        mMinValue.setText(result.minTempC);
                        mMaxValue.setText(result.maxTempC);
                        mWeatherDescription.setText(result.weatherDesc);
                        mCurrentTemp.setText(result.tempC + " °C");
                        mRequestedLocation.setText(result.currentLocation);
                        mWeatherIcon.setImageBitmap(result.weatherIconUrl);
                    }
                });


            }
        };

        thread.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_edit:
                mGlobV.setCache("n");
                Intent i = new Intent(MainActivity.this, ListCityActivity.class);
                startActivity(i);
                break;
        }
    }
}
