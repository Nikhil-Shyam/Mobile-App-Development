package com.example.widgetpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Switch switch1, switch2, switch3;
    TextView color;
    EditText email;
    Button verify;
    SeekBar size;
    boolean s1, s2, s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        color = findViewById(R.id.textViewColor);
        email = findViewById(R.id.editTextTextEmailAddress);
        verify = findViewById(R.id.verify);
        size = findViewById(R.id.seekBar);

        ArrayList<String> emails = new ArrayList<>();
        emails.add("example1@example.com");
        emails.add("example2@example.com");
        emails.add("example3@example.com");

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s1 = isChecked;
                updateColor();
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s2 = isChecked;
                updateColor();
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s3 = isChecked;
                updateColor();
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                if (validate(e))
                    Toast.makeText(MainActivity.this, "VALID", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "INVALID", Toast.LENGTH_SHORT).show();

                if (emails.contains(e))
                    color.setText("Verified");
                else
                    color.setText("Not In Database");
            }
        });
        size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color.setTextSize(color.getMinEms() + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void updateColor(){
        if (s1 && s2 && s3)
            color.setTextColor(Color.BLUE);
        else if (s1 && !s2 && s3)
            color.setTextColor(Color.RED);
        else if (!s1 && !s2 && s3)
            color.setTextColor(Color.GREEN);
        else
            color.setTextColor(Color.BLACK);
    }
    public boolean validate(String e){
        return e.contains("@") && e.endsWith(".com") && e.indexOf("@") < e.indexOf(".com");
    }
}