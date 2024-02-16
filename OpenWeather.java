package com.example.openweathermapproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    URL url;
    URLConnection urlConnection;
    InputStream inputStream;
    BufferedReader bufferedReader;
    String geoInfo, fiveDayInfo = "";
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncThread task = new AsyncThread();
        task.execute();
    }
    public class AsyncThread extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                url = new URL("https://api.openweathermap.org/geo/1.0/zip?zip=08852,US&appid=cfcdedfa1e43c6123cff4f3374adb61a");
                urlConnection = url.openConnection();
                inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String st;
                while ((st = bufferedReader.readLine()) != null)
                    geoInfo += st;

                url = new URL("https://api.openweathermap.org/data/2.5/forecast?zip=08852,US&appid=cfcdedfa1e43c6123cff4f3374adb61a");
                urlConnection = url.openConnection();
                inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                st = "";
                while ((st = bufferedReader.readLine()) != null)
                    fiveDayInfo += st;

                JSONObject geoWeather = new JSONObject(geoInfo);
                JSONObject fiveDayWeather = new JSONObject(fiveDayInfo);
                temp = geoWeather.get("zip").toString();
            }catch(IOException | JSONException e){}
            // This is where you will download your data.
            // You will need to override another method to update the UI
            Log.d("API", fiveDayInfo);
            // Log.d("ZIP", temp);

            return null;
        }
    }
}
