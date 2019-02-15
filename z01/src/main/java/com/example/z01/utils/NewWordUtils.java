package com.example.z01.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewWordUtils  {
    public static String getJson(String urlstring) {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int i = connection.getResponseCode();
            if (i == 200) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String t;
                while ((t =bufferedReader.readLine())!=null){
                    builder.append(t);
                }
                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static boolean iscoon(Context context){
        ConnectivityManager service = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = service.getActiveNetworkInfo();
        if (info!=null && info.isConnected()){

            return true;
        }else {
            return false;
        }
    }
}
