package com.example.calculatingasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.awt.font.NumericShaper;
import java.util.Random;

import kotlin.random.URandomKt;

public class MainActivity extends AppCompatActivity {
    private int nextFirstNum;
    private int nextSecNum;
    private int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextAnswer = findViewById(R.id.editTextNumber);

        Button answerButton = findViewById(R.id.button);
        Button nextButton = findViewById(R.id.nextButton);

        TextView successView = findViewById(R.id.textViewCorrectAnswer);
        TextView notSuccessView = findViewById(R.id.textViewIncorrectAnswer);
        TextView question = findViewById(R.id.textViewExample);

        question.setText(taskSentence(nextFirstNum, nextSecNum));
        result = getResult(nextFirstNum, nextSecNum);

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editTextAnswer.getText().toString();
                int number = Integer.parseInt(text);

                result = getResult(nextFirstNum, nextSecNum);
                Log.e(question.getText().toString(), "На экране: " + taskSentence(nextFirstNum, nextSecNum));

                if (number == result) {
                    successView.setVisibility(View.VISIBLE);
                    notSuccessView.setVisibility(View.GONE);
                }
                else if (text.equals("")){
                    successView.setVisibility(View.GONE);
                    notSuccessView.setVisibility(View.VISIBLE);
                }
                else {
                    successView.setVisibility(View.GONE);
                    notSuccessView.setVisibility(View.VISIBLE);
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successView.setVisibility(View.GONE);
                notSuccessView.setVisibility(View.GONE);

                nextFirstNum = getRandomNumber();
                nextSecNum = getRandomNumber();

                question.setText(taskSentence(nextFirstNum, nextSecNum));
                result = getResult(nextFirstNum, nextSecNum);
                Log.e(question.getText().toString(), "На экране: " + taskSentence(nextFirstNum, nextSecNum));

                editTextAnswer.getText().clear();

            }
        });


    }
    public static int getResult ( int nextFirstNum, int nextSecNum){
        return nextFirstNum + nextSecNum;
    }
    public static StringBuilder taskSentence ( int nextFirstNum, int nextSecNum){


        StringBuilder task = new StringBuilder(String.format("%s + %s = ", nextFirstNum, nextSecNum));
        return task;
    }

    public static int getRandomNumber()
    {
        return (int) (Math.random() * 50) + 1;
    }


}