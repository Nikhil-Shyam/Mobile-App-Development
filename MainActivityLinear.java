package com.example.layoutextrapracticepart1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityLinear extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRed, buttonBlue, buttonGreen, buttonCyan, buttonGray, buttonMagenta, buttonReset;

    private int originalBackgroundColor;
    private int originalButtonColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        originalBackgroundColor = findViewById(R.id.activity_linear).getSolidColor();
        buttonRed = findViewById(R.id.buttonRed);
        buttonBlue = findViewById(R.id.buttonBlue);
        buttonGreen = findViewById(R.id.buttonGreen);
        buttonCyan = findViewById(R.id.buttonCyan);
        buttonGray = findViewById(R.id.buttonGray);
        buttonMagenta = findViewById(R.id.buttonMagenta);
        buttonReset = findViewById(R.id.buttonReset);
        originalButtonColor = buttonRed.getCurrentTextColor();


        buttonRed.setOnClickListener(new ButtonInterface());
        buttonBlue.setOnClickListener(new ButtonInterface());
        buttonGreen.setOnClickListener(new ButtonInterface());
        buttonCyan.setOnClickListener(new ButtonInterface());
        buttonGray.setOnClickListener(new ButtonInterface());
        buttonMagenta.setOnClickListener(new ButtonInterface());

        buttonReset.setOnClickListener(this);
    }

    public void changeButtonColor(int color){
        buttonCyan.setTextColor(color);
        buttonGray.setTextColor(color);
        buttonMagenta.setTextColor(color);
    }

    public void changeBackgroundColor(int color){
        findViewById(R.id.activity_linear).setBackgroundColor(color);
    }

    @Override
    public void onClick(View view) {
        buttonCyan.setTextColor(originalButtonColor);
        buttonGray.setTextColor(originalButtonColor);
        buttonMagenta.setTextColor(originalButtonColor);
        findViewById(R.id.activity_linear).setBackgroundColor(originalBackgroundColor);
    }

    public class ButtonInterface implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.buttonRed)
                changeButtonColor(Color.RED);
            else if (view.getId() == R.id.buttonBlue)
                changeButtonColor(Color.BLUE);
            else if (view.getId() == R.id.buttonGreen)
                changeButtonColor(Color.GREEN);
            else if (view.getId() == R.id.buttonCyan)
                changeBackgroundColor(Color.CYAN);
            else if (view.getId() == R.id.buttonGray)
                changeBackgroundColor(Color.GRAY);
            else if (view.getId() == R.id.buttonMagenta)
                changeBackgroundColor(Color.MAGENTA);
        }
    }
}