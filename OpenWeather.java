package com.example.openweathermapproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText zipcode;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipcode = findViewById(R.id.editTextZipcode);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // string sent is zipcode (editText) text
            public void onClick(View v) {
                new AsyncThread().execute(zipcode.getText().toString());
            }
        });
    }
    public class AsyncThread extends AsyncTask<String, Void, Void> {
        URL url1, url2;
        URLConnection urlConnection1, urlConnection2;
        InputStream inputStream1, inputStream2;
        BufferedReader bufferedReader1, bufferedReader2;
        String geoInfo = "";
        String fiveDayInfo = "";
        // ****************************
        // use on post execute
        // ****************************
        @Override
        protected Void doInBackground(String... voids) {
            try{
                // string used is voids[0]
                url1 = new URL("https://api.openweathermap.org/geo/1.0/zip?zip=" + voids[0] + ",US&appid=cfcdedfa1e43c6123cff4f3374adb61a");
                urlConnection1 = url1.openConnection();
                inputStream1 = urlConnection1.getInputStream();
                bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));

                String st1;
                while ((st1 = bufferedReader1.readLine()) != null)
                    geoInfo += st1;

                JSONObject geoWeather = new JSONObject(geoInfo);
                String lat = geoWeather.get("lat").toString();
                String lon = geoWeather.get("lon").toString();

                url2 = new URL("https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=cfcdedfa1e43c6123cff4f3374adb61a&units=imperial");
                urlConnection2 = url2.openConnection();
                inputStream2 = urlConnection2.getInputStream();
                bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));

                String st2;
                while ((st2 = bufferedReader2.readLine()) != null)
                    fiveDayInfo += st2;

                JSONObject fiveDayWeather = new JSONObject(fiveDayInfo);

                Log.d("temp max", fiveDayWeather.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp_max").toString());
                Log.d("temp max", fiveDayWeather.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp_min").toString());
                Log.d("temp", fiveDayWeather.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp").toString());
                Log.d("desc", fiveDayWeather.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("description").toString());
                Log.d("date", fiveDayWeather.getJSONArray("list").getJSONObject(0).get("dt_txt").toString().substring(5, 10));
            }catch(IOException | JSONException e){}
            // This is where you will download your data.
            // You will need to override another method to update the UI
            Log.d("5day", fiveDayInfo);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }
}
