package com.example.polynomialcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    // w/ 10 padding:
    // 336 dp across
    // 570 dp down
    Button zero, one, two, three, four, five, six, seven, eight, nine, plus, multiplication, minus, clear, variable, equal, carrot, delete;
    TextView inp;
    boolean calculated = false;

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
        delete = findViewById(R.id.delete);
        inp = findViewById(R.id.textView);

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
        clear.setOnClickListener(new Buttons());
        delete.setOnClickListener(new Buttons());
        equal.setOnClickListener(new Buttons());
    }

    public class Buttons implements View.OnClickListener{
        String input;
        boolean error;
        ArrayList<String> exponents;
        ArrayList<String> eSigns;
        int eSignCounter;
        String multiplyInput;
        ArrayList<String> addSubTerms;
        ArrayList<String> multiplicationOutput;
        ArrayList<String> signs;
        int signCounter;
        String addSubInput;
        ArrayList<String> addAndSub;
        ArrayList<Integer> descendingVariableOrder;
        ArrayList<String> outputArray;
        String output;

        public void addText(Button button){
            if (calculated) {
                inp.setText("");
                calculated = false;
            }
            if (!inp.getText().toString().equals("ERROR"))
                inp.append(button.getText());
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
                inp.append("-");
            else if (view.getId() == R.id.multiplication)
                addText(multiplication);
            else if (view.getId() == R.id.carrot)
                addText(carrot);
            else if (view.getId() == R.id.variable)
                addText(variable);
            else if (view.getId() == R.id.clear) {
                inp.setText("");
            }
            else if (view.getId() == R.id.delete) {
                if (inp.length() == 0) {
                    inp.setText("ERROR");
                }
                if (!inp.getText().toString().equals("ERROR"))
                    inp.setText(inp.getText().toString().substring(0, inp.getText().length() - 1));
            }
            else if (view.getId() == R.id.equal) {
                calculate();
                calculated = true;
            }
        }

        public void calculate(){
            input = inp.getText().toString();
            error = false;
            exponents = new ArrayList<String>();
            eSigns = new ArrayList<String>();
            eSignCounter = 0;
            multiplyInput = "";
            addSubTerms = new ArrayList<String>();
            multiplicationOutput = new ArrayList<String>();
            signs = new ArrayList<String>();
            signCounter = 0;
            addSubInput = "";
            addAndSub = new ArrayList<String>();
            descendingVariableOrder = new ArrayList<Integer>();
            outputArray = new ArrayList<String>();
            output = "";

            checkForErrors();

            if (error){
                inp.setText("ERROR");
            }else{
                // breaking inp into terms (add/sub/multi) and adding to arraylist
                StringTokenizer st3 = new StringTokenizer(input, "+-*");
                while (st3.hasMoreTokens()){
                    exponents.add(st3.nextToken());
                }

                exponents();

                // breaking inp into terms (add/sub) and adding to arraylist
                StringTokenizer st1 = new StringTokenizer(multiplyInput, "+-");
                while (st1.hasMoreTokens()){
                    addSubTerms.add(st1.nextToken());
                }

                multiply();

                // determining signs
                determineAddSubSigns();

                // putting back into a string for addition/subtraction
                addSubString();

                // breaking up addition and subtraction
                addSubTerms = new ArrayList<String>();
                StringTokenizer st2 = new StringTokenizer(addSubInput, "+-");
                signCounter = 0;

                while(st2.hasMoreTokens()){
                    addSubTerms.add(st2.nextToken());
                }

                addSubTermSigns();

                // addition and subtraction
                additionAndSubtraction();

                descendingOrder();

                for(int i = 0; i < outputArray.size(); i++)
                    output += outputArray.get(i);

                if (output.length() == 0)
                    output = "0";
                else if (output.charAt(0) == '+')
                    output = output.substring(1);

                inp.setText(output);
            }
        }

        public void exponents(){
            for (int i = 0; i < exponents.size(); i++){
                String temp = exponents.get(i);
                if (temp.contains("^") && !temp.contains("x")){
                    int base = Integer.parseInt(temp.substring(0, temp.indexOf("^")));
                    int power = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
                    exponents.set(i, Integer.toString((int) Math.pow(base, power)));
                }
            }

            for (int i = 0; i < input.length(); i++){
                if (input.charAt(i) == '+')
                    eSigns.add("+");
                else if (input.charAt(i) == '-')
                    eSigns.add("-");
                else if (input.charAt(i) == '*')
                    eSigns.add("*");
            }

            for (int i = 0; i < exponents.size(); i++){
                if (input.charAt(0) == '-'){
                    if (eSignCounter < eSigns.size()){
                        multiplyInput += eSigns.get(eSignCounter);
                        multiplyInput += exponents.get(i);
                    }
                }else{
                    multiplyInput += exponents.get(i);
                    if (eSignCounter < eSigns.size())
                        multiplyInput += eSigns.get(eSignCounter);
                }
                eSignCounter++;
            }
        }
        public void multiply(){
            for (int i = 0; i < addSubTerms.size(); i++){
                // breaking up multiplication for each term and adding to arraylist
                ArrayList<String> multiplicationTerms = new ArrayList<String>();
                StringTokenizer st2 = new StringTokenizer(addSubTerms.get(i), "*");
                int constants = 1;
                int variableCounter = 0;

                while(st2.hasMoreTokens()){
                    multiplicationTerms.add(st2.nextToken());
                }

                for (int j = 0; j < multiplicationTerms.size(); j++){
                    String temp = multiplicationTerms.get(j);

                    //multiplying constants together
                    if (temp.contains("x")){
                        try{
                            constants *= Integer.parseInt(temp.substring(0, temp.indexOf("x")));
                        }catch (NumberFormatException e){
                            constants*=1;
                        }
                    }else
                        constants *= Integer.parseInt(temp);

                    //multiplying variables together
                    if (temp.contains("^"))
                        variableCounter += Integer.parseInt(temp.substring(temp.indexOf("^")+1));
                    else if (temp.contains("x"))
                        variableCounter++;
                }

                if (variableCounter != 0 && variableCounter != 1)
                    multiplicationOutput.add(constants + "x^" + variableCounter);
                else if(variableCounter == 1)
                    multiplicationOutput.add(constants + "x");
                else
                    multiplicationOutput.add(Integer.toString(constants));
            }
        }
        public void determineAddSubSigns(){
            for (int i = 0; i < input.length(); i++){
                if (input.charAt(i) == '+')
                    signs.add("+");
                else if (input.charAt(i) == '-')
                    signs.add("-");
            }
        }
        public void addSubString(){
            for (int i = 0; i < multiplicationOutput.size(); i++){
                if (input.charAt(0) == '-'){
                    if (signCounter < signs.size()){
                        addSubInput += signs.get(signCounter);
                        addSubInput += multiplicationOutput.get(i);
                    }
                }else{
                    addSubInput += multiplicationOutput.get(i);
                    if (signCounter < signs.size())
                        addSubInput += signs.get(signCounter);
                }
                signCounter++;
            }
        }
        public void addSubTermSigns(){
            if (input.charAt(0) == '-'){
                for (int i = 0; i < addSubTerms.size(); i++){
                    if (signCounter < signs.size()){
                        if (signs.get(signCounter).equals("-")){
                            addSubTerms.set(i, "-" + addSubTerms.get(i));
                        }
                    }
                    signCounter++;
                }
            }else{
                for (int i = 1; i < addSubTerms.size(); i++){
                    if (signCounter < signs.size()){
                        if (signs.get(signCounter).equals("-")){
                            addSubTerms.set(i, "-" + addSubTerms.get(i));
                        }
                    }
                    signCounter++;
                }
            }
        }
        public void additionAndSubtraction(){
            for (int i = 0; i < addSubTerms.size(); i++){
                int variable = 0;
                String temp = addSubTerms.get(i);
                if (temp.contains("^"))
                    variable = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
                else if (temp.contains("x"))
                    variable = 1;
                else
                    variable = 0;

                int cons = 0;
                if (variable >= 1)
                    cons = Integer.parseInt(temp.substring(0, temp.indexOf("x")));
                else
                    cons = Integer.parseInt(temp);

                int tempSize = addSubTerms.size();
                for (int j = addSubTerms.size()-1; j > i; j--){
                    int variable2 = 0;
                    String temp2 = addSubTerms.get(j);
                    if (temp2.contains("^"))
                        variable2 = Integer.parseInt(temp2.substring(temp2.indexOf("^")+1));
                    else if (temp2.contains("x"))
                        variable2 = 1;
                    else
                        variable2 = 0;

                    if (variable == variable2){
                        int cons2 = 0;
                        if (variable >= 1)
                            cons2 = Integer.parseInt(temp2.substring(0, temp2.indexOf("x")));
                        else
                            cons2 = Integer.parseInt(temp2);

                        cons += cons2;

                        addSubTerms.remove(j);
                    }
                }

                if (tempSize == addSubTerms.size()){
                    if (temp.charAt(0) == '-')
                        addAndSub.add(addSubTerms.get(i));
                    else
                        addAndSub.add("+" + addSubTerms.get(i));
                }else if (variable > 1){
                    if (cons > 0)
                        addAndSub.add("+" + (cons)+"x^"+variable);
                    else if (cons < 0)
                        addAndSub.add((cons)+"x^"+variable);
                }else if (variable == 1){
                    if (cons > 0)
                        addAndSub.add("+" + (cons)+"x");
                    else if (cons < 0)
                        addAndSub.add((cons)+"x");
                }else if (variable == 0){
                    if (cons > 0)
                        addAndSub.add("+" + (cons));
                    else if (cons < 0)
                        addAndSub.add(Integer.toString(cons));
                }
            }

            for (int i = 0; i < addAndSub.size(); i++){
                String temp = addAndSub.get(i);
                if (temp.contains("x")){
                    if (temp.substring(0, temp.indexOf("x")).equals("+1"))
                        addAndSub.set(i, "+" + temp.substring(temp.indexOf("x")));
                    if (temp.substring(0, temp.indexOf("x")).equals("-1"))
                        addAndSub.set(i, "-" + temp.substring(temp.indexOf("x")));
                }
            }
        }
        public void descendingOrder(){
            for (int i = 0; i < addAndSub.size(); i++){
                String temp = addAndSub.get(i);
                if (temp.contains("^")){
                    int tempI = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
                    descendingVariableOrder.add(tempI);
                }else if (temp.contains("x")){
                    descendingVariableOrder.add(1);
                }else{
                    descendingVariableOrder.add(0);
                }
            }
            Collections.sort(descendingVariableOrder, Collections.reverseOrder());

            for(int i = 0; i < descendingVariableOrder.size(); i++){
                int tempD = descendingVariableOrder.get(i);
                for (int j = 0; j < addAndSub.size(); j++){
                    String temp = addAndSub.get(j);
                    int tempF = -1;
                    if(temp.contains("^"))
                        tempF = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
                    else if (temp.contains("x"))
                        tempF = 1;
                    else
                        tempF = 0;
                    if (tempF == tempD)
                        outputArray.add(temp);
                }
            }
        }
        public void checkForErrors(){
            char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

            char last = input.charAt(input.length()-1);
            if(last == '+' || last == '*' || last == '^' || last == '-')
                error = true;

            if(input.length() == 0)
                error = true;

            for (int i = 0; i < input.length()-1; i++){
                char temp = input.charAt(i);
                if (temp == '+' || temp == '^' || temp == 'x' || temp == '-' || temp == '*'){
                    if (temp == input.charAt(i+1))
                        error = true;
                }
                if (temp == '^'){
                    char temp2 = input.charAt(i+1);
                    if (temp2 == 'x' || temp2 == '+' || temp2 == '-' || temp2 == '*')
                        error = true;
                }
                if (temp == '-'){
                    if (input.charAt(i+1) == '+')
                        error = true;
                }
                if (temp == '+'){
                    if (input.charAt(i+1) == '-')
                        error = true;
                }
                if (i == 0){
                    char first = input.charAt(i);
                    if(first == '+' || first == '*' || first == '^')
                        error = true;
                }
                if (temp == 'x'){
                    for (int j = 0; j < nums.length; j++){
                        if (input.charAt(i+1) == nums[j])
                            error = true;
                    }
                }
                for (int i = 1; i < input.length()-1; i++){
                    for (int j = 0; j < nums.length; j++){
                        if (input.charAt(i) == nums[j] && input.charAt(i+1) == 'x' && input.charAt(i-1) == '^')
                            error = true;
                    }
                }
            }
        }
    }
}
