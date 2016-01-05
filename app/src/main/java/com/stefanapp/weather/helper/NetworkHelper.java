package com.stefanapp.weather.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stefanlam88 on 4/1/16.
 */
public class NetworkHelper {

    public static String httpGet(String url) throws IOException {

        URL c = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) c.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(false);
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }
}
