package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    Spinner mQuizSpinner;
    Button mQuizButton;
    String quizSelection = "empty";
    String demoSelection;
    Spinner mDemoSpinner;
    Button mDemoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mQuizSpinner = (Spinner) findViewById(R.id.spinner_quiz);
        ArrayAdapter quizAdapter = ArrayAdapter.createFromResource(this, R.array.quiz_choices, android.R.layout.simple_spinner_item);
        quizAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQuizSpinner.setAdapter(quizAdapter);
        mQuizSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quizSelection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mDemoSpinner = (Spinner) findViewById(R.id.spinner_demo);
        ArrayAdapter demoAdapter = ArrayAdapter.createFromResource(this, R.array.demo_choices, android.R.layout.simple_spinner_item);
        demoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDemoSpinner.setAdapter(demoAdapter);
        mQuizSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                demoSelection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mQuizButton = (Button) findViewById(R.id.button_quiz);
        mQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizSelection.equals("empty")) {
                    Toast.makeText(DashboardActivity.this, "Please select a quiz", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivity.this, QuizActivity.class);
                    intent.putExtra("Quiz", quizSelection);
                    startActivity(intent);
                }
            }
        });
        mDemoButton = (Button) findViewById(R.id.button_demo);
        mDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DemoActivity.class);
                intent.putExtra("Demo", demoSelection);
                startActivity(intent);
            }
        });
    }
}
