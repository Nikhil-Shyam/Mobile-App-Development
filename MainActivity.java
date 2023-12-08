package com.example.morewidgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton walter, jesse, saul;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        image = findViewById(R.id.imageView);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton) {
                    image.setImageResource(R.drawable.walterwhite);
                    Toast.makeText(MainActivity.this, "You have selected Walter!", Toast.LENGTH_SHORT).show();
                }
                if (i == R.id.radioButton2) {
                    image.setImageResource(R.drawable.jessepinkman);
                    Toast.makeText(MainActivity.this, "You have selected Jesse!", Toast.LENGTH_SHORT).show();
                }
                if (i == R.id.radioButton3) {
                    image.setImageResource(R.drawable.saulgoodman);
                    Toast.makeText(MainActivity.this, "You have selected Saul!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}