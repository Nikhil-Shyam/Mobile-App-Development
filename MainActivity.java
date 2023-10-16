package com.example.layoutextrapracticepart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private TextView view1;
    private TextView view2;
    private TextView view3;
    private TextView view4;
    private TextView view5;

    private boolean on1 = false;
    private boolean on2 = false;
    private boolean on3 = false;
    private boolean on4 = false;
    private boolean on5 = false;


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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on1 = !on1;
                if (on1)
                    view1.setText("ON");
                else
                    view1.setText("OFF");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on2 = !on2;
                if (on2)
                    view2.setText("ON");
                else
                    view2.setText("OFF");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on3 = !on3;
                if (on3)
                    view3.setText("ON");
                else
                    view3.setText("OFF");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on4 = !on4;
                if (on4)
                    view4.setText("ON");
                else
                    view4.setText("OFF");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on5 = !on5;
                if (on5)
                    view5.setText("ON");
                else
                    view5.setText("OFF");
            }
        });
    }
}