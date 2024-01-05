package com.example.calculatingasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kotlin.random.URandomKt;

public class MainActivity extends AppCompatActivity {
    private int nextFirstNum;
    private int nextSecNum;
    private int result;
    String signValue;
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

        nextFirstNum = getFirstRandomNumber();
        nextSecNum = getRandomNumber();
        signValue = sign();

        question.setText(taskSentence(nextFirstNum, nextSecNum, sign()));
        result = getResult(nextFirstNum, nextSecNum, sign());

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editTextAnswer.getText().toString();
                int number = Integer.parseInt(text);

                result = getResult(nextFirstNum, nextSecNum, signValue);
                Log.e(question.getText().toString(), "На экране: " + taskSentence(nextFirstNum, nextSecNum,signValue));

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

                nextFirstNum = getFirstRandomNumber();
                nextSecNum = getRandomNumber();

                signValue = sign();

                question.setText(taskSentence(nextFirstNum, nextSecNum, signValue));
                result = getResult(nextFirstNum, nextSecNum, signValue);
                Log.e(question.getText().toString(), "На экране: " + taskSentence(nextFirstNum, nextSecNum, signValue));

                editTextAnswer.getText().clear();

            }
        });
    }
    public static int getResult ( int nextFirstNum, int nextSecNum, String sign){
        switch (sign){
            case "+":
                return nextFirstNum + nextSecNum;
            case "-":
                return nextFirstNum - nextSecNum;
//            case "*":
//                return nextFirstNum * nextSecNum;
//            case "/":
//                return nextFirstNum / nextSecNum;
            default:
                return 0;
        }
    }
    public static StringBuilder taskSentence ( int nextFirstNum, int nextSecNum, String sign){
        StringBuilder task = new StringBuilder(String.format("%s %s %s = ", nextFirstNum, sign, nextSecNum));
        return task;
    }

    public static String sign(){
        Random rand = new Random();
        List<String> givenList = Arrays.asList("+", "-");
        return givenList.get(rand.nextInt(givenList.size()));
    }

    public static int getFirstRandomNumber()
    {
        int max = 50;
        return (int) (Math.random() * ++max) + 10;
    }
    public static int getRandomNumber()
    {
        return (int) (Math.random() * 10) + 1;
    }


}