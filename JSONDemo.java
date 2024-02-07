package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Part 1 - Creating the schoolInfo Object
        JSONObject schoolInfo = new JSONObject();
        try {
            schoolInfo.put("name", "Nikhil");
            schoolInfo.put("grade", 12);
            schoolInfo.put("id", 1234567);
        }catch(JSONException e){ e.printStackTrace(); }

        Log.d("school", schoolInfo.toString());
        try {
            textView.setText(schoolInfo.get("name").toString());
        }catch(JSONException e){ e.printStackTrace(); }

        // Part 2 - Creating the JSONObject to store inside the schoolInfo Object
        try {
            JSONObject compSci = new JSONObject();
            compSci.put("grade", "A");
            compSci.put("percentage", 96);
            schoolInfo.put("Computer Science", compSci);
        }catch(JSONException e){ e.printStackTrace(); }
        Log.d("school", schoolInfo.toString());

        try {
            JSONObject findCourse;
            findCourse = schoolInfo.getJSONObject("Computer Science");
            textView.setText(findCourse.get("grade").toString());
        }catch(JSONException e){ e.printStackTrace(); }

        // Part 3 - JSONArray
        JSONArray clubs;
        clubs = new JSONArray();
        clubs.put("Computer Science");
        clubs.put("GWC");
        clubs.put("CS Honor Society");

        try{
            JSONObject robotics = new JSONObject();
            robotics.put("Robotics", "ROBOT TEAM");
            clubs.put(robotics);
            schoolInfo.put("clubs", clubs);
        }catch(JSONException e){ e.printStackTrace(); }

        Log.d("school", clubs.toString());
        Log.d("school", schoolInfo.toString());
    }
}
