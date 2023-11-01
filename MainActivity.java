package com.example.polynomialcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // w/ 10 padding:
    // 336 dp across
    // 570 dp down
    Button zero, one, two, three, four, five, six, seven, eight, nine, plus, multiplication, minus, clear, variable, equal, carrot;
    TextView input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        plus = findViewById(R.id.plus);
        multiplication = findViewById(R.id.multiplication);
        minus = findViewById(R.id.minus);
        clear = findViewById(R.id.clear);
        variable = findViewById(R.id.variable);
        equal = findViewById(R.id.equal);
        carrot = findViewById(R.id.carrot);
        input = findViewById(R.id.textView);

        zero.setOnClickListener(new Buttons());
        one.setOnClickListener(new Buttons());
        two.setOnClickListener(new Buttons());
        three.setOnClickListener(new Buttons());
        four.setOnClickListener(new Buttons());
        five.setOnClickListener(new Buttons());
        six.setOnClickListener(new Buttons());
        seven.setOnClickListener(new Buttons());
        eight.setOnClickListener(new Buttons());
        nine.setOnClickListener(new Buttons());
        plus.setOnClickListener(new Buttons());
        minus.setOnClickListener(new Buttons());
        multiplication.setOnClickListener(new Buttons());
        carrot.setOnClickListener(new Buttons());
        variable.setOnClickListener(new Buttons());
    }

    public class Buttons implements View.OnClickListener{
        public void addText(Button button){
            input.append(button.getText());
        }
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.zero)
                addText(zero);
            else if (view.getId() == R.id.one)
                addText(one);
            else if (view.getId() == R.id.two)
                addText(two);
            else if (view.getId() == R.id.three)
                addText(three);
            else if (view.getId() == R.id.four)
                addText(four);
            else if (view.getId() == R.id.five)
                addText(five);
            else if (view.getId() == R.id.six)
                addText(six);
            else if (view.getId() == R.id.seven)
                addText(seven);
            else if (view.getId() == R.id.eight)
                addText(eight);
            else if (view.getId() == R.id.nine)
                addText(nine);
            else if (view.getId() == R.id.plus)
                addText(plus);
            else if (view.getId() == R.id.minus)
                addText(minus);
            else if (view.getId() == R.id.multiplication)
                addText(multiplication);
            else if (view.getId() == R.id.carrot)
                addText(carrot);
            else if (view.getId() == R.id.variable)
                addText(variable);
            else if (view.getId() == R.id.clear)
                input.setText("");
            else if (view.getId() == R.id.delete){
                input.setText(((String) input.getText()).substring(0, input.getText().length()-1));
            }
        }
    }
}