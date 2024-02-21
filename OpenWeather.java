package com.example.openweathermapproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    URL url1, url2;
    URLConnection urlConnection1, urlConnection2;
    InputStream inputStream1, inputStream2;
    BufferedReader bufferedReader1, bufferedReader2;
    String geoInfo = "";
    String fiveDayInfo = "";
    EditText zipcode;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zipcode = findViewById(R.id.editTextZipcode);
        String inp = zipcode.toString();
        AsyncThread task = new AsyncThread();
        task.execute();
    }
    public class AsyncThread extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... voids) {
            try{
                // url = new URL("https://api.openweathermap.org/geo/1.0/zip?zip=" + voids + ",US&appid=cfcdedfa1e43c6123cff4f3374adb61a");
                url1 = new URL("https://api.openweathermap.org/geo/1.0/zip?zip=08824,US&appid=cfcdedfa1e43c6123cff4f3374adb61a");
                urlConnection1 = url1.openConnection();
                inputStream1 = urlConnection1.getInputStream();
                bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));

                Log.d("url", url1 + "");

                String st1;
                while ((st1 = bufferedReader1.readLine()) != null)
                    geoInfo += st1;

                JSONObject geoWeather = new JSONObject(geoInfo);
                String lat = geoWeather.get("lat").toString();
                String lon = geoWeather.get("lon").toString();

                Log.d("LAT", lat);
                Log.d("LON", lon);

                url2 = new URL("api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=cfcdedfa1e43c6123cff4f3374adb61a");
                urlConnection2 = url2.openConnection();
                inputStream2 = urlConnection2.getInputStream();
                bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));

                Log.d("url", url2 + "");

                String st2;
                while ((st2 = bufferedReader2.readLine()) != null) {
                    Log.d("st2", st2);
                    fiveDayInfo += st2;
                }


                // JSONArray fiveDayWeather = new JSONArray(fiveDayInfo);
            }catch(IOException | JSONException e){}

            // This is where you will download your data.
            // You will need to override another method to update the UI
            Log.d("5day", fiveDayInfo);

            return null;
        }
    }
}
