package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView sumTextView;
    TextView timerTextView;
    TextView answerTextView;
    TextView resultTextView;
    CountDownTimer timer;
    ConstraintLayout constraint;
    ArrayList<Integer> arr=new ArrayList<Integer>();
    int correctAnswer;
    int totalCount=0;
    int currentCount=0;
    boolean gameActive=true;

    public void choseAnswer(View view) {
//        Log.i("Info",(view.getTag()).toString());
        if (gameActive) {
            totalCount++;
            int tag = Integer.parseInt(view.getTag().toString());

            if (arr.get(tag) == correctAnswer) {
                answerTextView.setText("Correct!");
                currentCount++;

            } else {
                answerTextView.setText("Wrong :)");
            }
            resultTextView.setText(Integer.toString(currentCount) + "/" + Integer.toString(totalCount));
            questions();
        }
    }

    public void start(View view){
        button.setVisibility(View.INVISIBLE);
        constraint.setVisibility(View.VISIBLE);
        playAgain(resultTextView);

    }

    public void playAgain(View view){
        totalCount=0;
        currentCount=0;
        gameActive=true;
        playAgain.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        timerTextView.setText("30s");
        resultTextView.setText(Integer.toString(currentCount)+"/"+Integer.toString(totalCount));

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Log.i("Info","Time is up!");
                answerTextView.setText("Over!");
                gameActive=false;
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        questions();

    }

    public void questions() {
        if (gameActive) {
            Random rand = new Random();
            int a = rand.nextInt(21);
            int b = rand.nextInt(21);
            String sign = "";

            int maxRand = 0;
            int operation = rand.nextInt(4);
            if (operation == 0) {
                maxRand = rand.nextInt(41);
                correctAnswer = a + b;
                sign = "+";
            } else if (operation == 1) {
                maxRand = rand.nextInt(21);
                correctAnswer = a - b;
                sign = "-";
            } else if (operation == 2) {
                maxRand = rand.nextInt(442);
                correctAnswer = a * b;
                sign = "*";
            }
        else if (operation==3){
            maxRand=rand.nextInt(12);
            int nextNum=rand.nextInt(21);
            while (b==0 || nextNum==0){
                b=rand.nextInt(21);
                nextNum=rand.nextInt(21);
            }
            correctAnswer=(b*nextNum)/b;
            a=(b*nextNum);
            sign="/";
        }
            int locationOfCorrectAnswer = rand.nextInt(4);

            arr.clear();


            for (int i = 0; i < 4; i++) {
                if (locationOfCorrectAnswer == i) {
                    arr.add(correctAnswer);
                } else {
                    Log.i("Info IS",String.valueOf(maxRand));
                    while (maxRand==0){
                        maxRand=rand.nextInt(21);
                    }
                    int element = rand.nextInt(maxRand);
                    while ((abs(correctAnswer)) == element) {
                        element = rand.nextInt(maxRand);
                    }
                    arr.add(element);
                }
            }

            sumTextView.setText(Integer.toString(a) + sign + Integer.toString(b));
            button0.setText(Integer.toString(arr.get(0)));
            button1.setText(Integer.toString(arr.get(1)));
            button2.setText(Integer.toString(arr.get(2)));
            button3.setText(Integer.toString(arr.get(3)));

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.startId);
        button0=(Button) findViewById(R.id.button0);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        playAgain=(Button) findViewById(R.id.playAgain);
        sumTextView=(TextView) findViewById(R.id.sumTextView);
        answerTextView=(TextView) findViewById(R.id.answerTextView);
        resultTextView =(TextView) findViewById(R.id.resultTextView);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        constraint=(ConstraintLayout) findViewById(R.id.constraintLayout);

        constraint.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);





    }
}
