package com.example.layoutextrapracticepart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityRelative extends AppCompatActivity {
    private Button button1, button2, button3, button4, button5;

    private TextView view1, view2, view3, view4, view5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        view1 = findViewById(R.id.textView1);
        view2 = findViewById(R.id.textView2);
        view3 = findViewById(R.id.textView3);
        view4 = findViewById(R.id.textView4);
        view5 = findViewById(R.id.textView5);

        button1.setOnClickListener(new ButtonInterface());
        button2.setOnClickListener(new ButtonInterface());
        button3.setOnClickListener(new ButtonInterface());
        button4.setOnClickListener(new ButtonInterface());
        button5.setOnClickListener(new ButtonInterface());
    }

    public void changeText(View view){
        if (((TextView)view).getText().equals("OFF"))
            ((TextView)view).setText("ON");
        else
            ((TextView)view).setText("OFF");
    }

    public class ButtonInterface implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button1)
                changeText(view1);
            else if (view.getId() == R.id.button2)
                changeText(view2);
            else if (view.getId() == R.id.button3)
                changeText(view3);
            else if (view.getId() == R.id.button4)
                changeText(view4);
            else if (view.getId() == R.id.button5)
                changeText(view5);
        }
    }
}