package com.example.widgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Switch switchy;
    EditText colorInput;
    TextView displayColor;
    SeekBar bar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchy = findViewById(R.id.switchSeekBar);
        colorInput = findViewById(R.id.editTextColor);
        displayColor = findViewById(R.id.textViewDisplayColor);
        bar = findViewById(R.id.seekBar);
        button = findViewById(R.id.button);

        bar.setEnabled(false);
        switchy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    buttonView.setText("ON");
                    bar.setEnabled(true);
                }
                if (!isChecked){
                    buttonView.setText("OFF");
                    bar.setEnabled(false);
                }
            }
        });

        colorInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inp = charSequence.toString();
                if (inp.equalsIgnoreCase("red"))
                    displayColor.setTextColor(Color.RED);
                else if(inp.equalsIgnoreCase("blue"))
                    displayColor.setTextColor(Color.BLUE);
                else if(inp.equalsIgnoreCase("green"))
                    displayColor.setTextColor(Color.GREEN);
                else
                    displayColor.setTextColor(Color.BLACK);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // this doesn't work
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) button.getLayoutParams();
                params.width = (int)(145 * getResources().getDisplayMetrics().scaledDensity) + progress*3;
                params.height = (int)(50 * getResources().getDisplayMetrics().scaledDensity) + progress*3;
                button.setLayoutParams(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}