package com.example.morewidgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    RadioGroup radioGroupVolume, radioGroupRPS;
    Button play;
    TextView selection, score;
    ImageView image;
    int rps, playerScore, computerScore = 0;
    boolean playerWin, tie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        radioGroupVolume = findViewById(R.id.radioGroupVolume);
        radioGroupRPS = findViewById(R.id.radioGroupRPS);
        play = findViewById(R.id.buttonPlay);
        selection = findViewById(R.id.textViewSelection);
        score = findViewById(R.id.textViewScore);
        image = findViewById(R.id.imageViewRPS);

        radioGroupVolume.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton100)
                    Toast.makeText(MainActivity2.this, "High Volume Could Damage Your Hearing", Toast.LENGTH_LONG).show();
            }
        });

        radioGroupRPS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonRock)
                    rps = 1;
                if (checkedId == R.id.radioButtonPaper)
                    rps = 2;
                if (checkedId == R.id.radioButtonScissor)
                    rps = 3;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rps == 0)
                    selection.setText("Make a selection");
                else{
                    int ran = (int)(Math.random()*3)+1;
                    if (ran == 1)
                        image.setImageResource(R.drawable.rock);
                    else if (ran == 2)
                        image.setImageResource(R.drawable.paper);
                    else
                        image.setImageResource(R.drawable.scissors);

                    if (ran == 1 && rps == 2) {
                        playerScore++;
                        playerWin = true;
                    }else if (ran == 1 && rps == 1)
                        tie = true;
                    else if (ran == 1 && rps == 3){
                        computerScore++;
                        playerWin = false;
                    }else if (ran == 2 && rps == 1) {
                        computerScore++;
                        playerWin = false;
                    }else if (ran == 2 && rps == 2)
                        tie = true;
                    else if (ran == 2 && rps == 3) {
                        playerScore++;
                        playerWin = true;
                    }else if (ran == 3 && rps == 2) {
                        computerScore++;
                        playerWin = false;
                    }else if (ran == 3 && rps == 3)
                        tie = true;
                    else if (ran == 3 && rps == 1) {
                        playerScore++;
                        playerWin = true;
                    }
                }

                if (rps == 0) {
                    selection.setText("Make a selection");
                }else {
                    if (tie)
                        selection.setText("Tie!");
                    else if (playerWin)
                        selection.setText("You Win!");
                    else if (!playerWin)
                        selection.setText("You Lose!");

                    tie = false;

                    score.setText("Player: " + playerScore + " CPU: " + computerScore);
                }
            }
        });
    }
}