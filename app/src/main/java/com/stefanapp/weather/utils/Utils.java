package com.stefanapp.weather.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by stefanlam88 on 1/1/16.
 */
public class Utils {

    public static String loadJSONFromAsset(Context mContext) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("city.list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
