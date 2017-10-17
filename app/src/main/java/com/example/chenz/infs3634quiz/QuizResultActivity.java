package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    int questions;
    int results;
    TextView mResultText;
    Button mHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        questions = getIntent().getExtras().getInt("Questions");
        results = getIntent().getExtras().getInt("Result");

        mResultText = (TextView) findViewById(R.id.text_result);
        mResultText.setText("You got " + results + " questions out of " + questions + " correct!");

        mHomeButton = (Button) findViewById(R.id.button_dashboard);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResultActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
