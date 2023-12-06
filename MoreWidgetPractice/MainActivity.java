package com.example.morewidgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton walter, jesse, saul;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        walter = findViewById(R.id.radioButton);
        jesse = findViewById(R.id.radioButton2);
        saul = findViewById(R.id.radioButton3);
        image = findViewById(R.id.imageView);
    }
}