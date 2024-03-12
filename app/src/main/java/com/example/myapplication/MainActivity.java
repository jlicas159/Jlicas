package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView questionTextView;
    RadioGroup optionsRadioGroup;
    Button submitButton;

    String[][] quizData = {
            {"What is the capital of France?", "Paris", "London", "Berlin"},
            {"Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter"},
            {"What is the largest mammal?", "Blue Whale", "Elephant", "Giraffe"}
    };

    int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        showQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    void showQuestion() {
        String[] currentQuestion = quizData[currentQuestionIndex];
        questionTextView.setText(currentQuestion[0]);
        for (int i = 1; i < currentQuestion.length; i++) {
            RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i - 1);
            radioButton.setText(currentQuestion[i]);
        }
    }

    void checkAnswer() {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = quizData[currentQuestionIndex][1];
            if (selectedAnswer.equals(correctAnswer)) {
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Incorrect. The correct answer is " + correctAnswer, Toast.LENGTH_SHORT).show();
            }
            if (currentQuestionIndex < quizData.length - 1) {
                currentQuestionIndex++;
                showQuestion();
            } else {
                finish();
            }
        } else {
            Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }
}
