package com.stefanapp.weather.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stefanapp.weather.R;
import com.stefanapp.weather.adapters.CityViewAdapter;
import com.stefanapp.weather.classes.City;
import com.stefanapp.weather.classes.CityInteractorImpl;
import com.stefanapp.weather.classes.CityPresenterImpl;
import com.stefanapp.weather.interfaces.city.CityInteractor;
import com.stefanapp.weather.interfaces.city.CityPresenter;
import com.stefanapp.weather.interfaces.city.CityView;
import com.stefanapp.weather.utils.MyApplication;
import com.stefanapp.weather.utils.Utils;

import java.util.ArrayList;

public class ListCityActivity extends AppCompatActivity implements CityView, View.OnClickListener {

    Context mContext;
    private CityPresenter mCityInterator;
    private CityViewAdapter mCityViewAdapter;
    RecyclerView mRecyclerView;
    private ArrayList<City> list;
    TextView mTitle;
    ImageView mSaveButton;
    MyApplication mGlobV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mTitle = (TextView) findViewById(R.id.title);
        mSaveButton = (ImageView) findViewById(R.id.img_save);
        mContext = this;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mGlobV = (MyApplication) this.getApplicationContext();

        list = new ArrayList<City>();
        String json = Utils.loadJSONFromAsset(mContext);
        mCityInterator = new CityPresenterImpl(this, mContext);
        mCityInterator.getCity(json);

        mTitle.setText("List of Cities");
        mSaveButton.setOnClickListener(this);

        if (mGlobV.getCacheCity() != null && mGlobV.getCache().equalsIgnoreCase("y")) {
            Intent i = new Intent(ListCityActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    @Override
    public void setError() {

    }

    @Override
    public void setSuccess(ArrayList<City> listing) {
        list.clear();
        list.addAll(listing);

        mCityViewAdapter = new CityViewAdapter(this, list);
        mRecyclerView.setAdapter(mCityViewAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_save:
                boolean selected = checkListing();

                if (selected) {
                    mGlobV.setCache("y");
                    Intent i = new Intent(ListCityActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                    builder1.setMessage("Please select at least one city");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                break;
        }
    }

    private boolean checkListing() {

        boolean IsSelected = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).selected) {
                mGlobV.setCacheCity(list.get(i).city + ", " + list.get(i).country);
                IsSelected = true;
                break;
            }
        }

        return IsSelected;
    }
}
