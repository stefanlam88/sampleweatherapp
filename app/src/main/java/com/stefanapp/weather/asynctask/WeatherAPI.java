package com.stefanapp.weather.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.stefanapp.weather.classes.Hours;
import com.stefanapp.weather.classes.Weather;
import com.stefanapp.weather.classes.WeatherData;
import com.stefanapp.weather.helper.NetworkHelper;
import com.stefanapp.weather.interfaces.weather.OnWeatherFinishedListener;
import com.stefanapp.weather.interfaces.weather.WeatherView;
import com.stefanapp.weather.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by stefanlam88 on 4/1/16.
 */
public class WeatherAPI extends AsyncTask<Weather, Void, String> {

    private OnWeatherFinishedListener onWeatherFinishedListener;
    private ImageDownloadAPI mImageDownloadAPI;

    @Override
    protected void onPreExecute() {
        mImageDownloadAPI = new ImageDownloadAPI();
    }

    @Override
    protected String doInBackground(Weather... params) {

        String result = null;
        try {
            String param = "?q=" + Uri.encode(params[0].query) + "&format=" + params[0].format + "&num_of_days=" +
                    params[0].numOfDays + "&key=" + Const.API_WEATHER_KEY;

            result = NetworkHelper.httpGet(Const.API_SERVER_PREFIX + Const.API_WEATHER + param);

        } catch (IOException e) {
            e.printStackTrace();
        }

        onWeatherFinishedListener = params[0].listener;
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        WeatherData weatherData = new WeatherData();
        //store data into weather data object then pass to view.
        try {
            JSONObject jObject = new JSONObject(result);
            JSONObject response = jObject.getJSONObject("data");
            JSONArray dataArray = response.getJSONArray("current_condition");
            JSONArray otherArray = response.getJSONArray("weather");
            JSONArray requestArray = response.getJSONArray("request");

            if (dataArray.length() > 0) {
                //get temperature of current weather
                weatherData.tempC = dataArray.getJSONObject(0).getString("temp_C");
                //get image

                JSONArray jsonArray = dataArray.getJSONObject(0).getJSONArray("weatherIconUrl");
                if (jsonArray.length() > 0){
                    Bitmap icon = mImageDownloadAPI.execute(jsonArray.getJSONObject(0).getString("value")).get();
                    weatherData.weatherIconUrl = icon;
                }

                //get description
                JSONArray descArray = dataArray.getJSONObject(0).getJSONArray("weatherDesc");
                if (descArray.length() > 0)
                    weatherData.weatherDesc = descArray.getJSONObject(0).getString("value");

                //get min & max temperature of current weather
                weatherData.minTempC = otherArray.getJSONObject(0).getString("mintempC");
                weatherData.maxTempC = otherArray.getJSONObject(0).getString("maxtempC");
                //requested query
                weatherData.currentLocation = requestArray.getJSONObject(0).getString("query");
                //get hourly of current weather
                JSONArray jsonHourlyArray = otherArray.getJSONObject(0).getJSONArray("hourly");

                for(int i = 0; i < jsonHourlyArray.length(); i++){
                    Hours hr = new Hours();
                    hr.tempC =  jsonHourlyArray.getJSONObject(i).getString("tempC");
                    hr.time =  jsonHourlyArray.getJSONObject(i).getString("time");

                    JSONArray weatherArray = dataArray.getJSONObject(0).getJSONArray("weatherDesc");
                    hr.weatherDesc =  weatherArray.getJSONObject(0).getString("value");

                    JSONArray weatherIconArray = dataArray.getJSONObject(0).getJSONArray("weatherIconUrl");
                    hr.weatherIconUrl =  weatherIconArray.getJSONObject(0).getString("value");

                    weatherData.hoursArrayList.add(hr);
                }


            }

            onWeatherFinishedListener.onSuccess(weatherData);
            weatherData = null; // remove reference & clear object

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
