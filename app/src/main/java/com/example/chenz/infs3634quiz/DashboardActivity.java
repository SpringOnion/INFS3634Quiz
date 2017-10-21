package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;

public class DashboardActivity extends AppCompatActivity {

    Spinner mQuizSpinner;
    Button mQuizButton;
    String quizSelection = "empty";
    String demoSelection;
    Spinner mDemoSpinner;
    Button mDemoButton;
    int mPositionCheck;
    ArrayList<String> mQuizList;
    String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mUsername = getIntent().getExtras().getString("Username");
        mQuizSpinner = (Spinner) findViewById(R.id.spinner_quiz);
        mQuizList = getLength();
        ArrayAdapter<String> mQuizAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mQuizList);
        mQuizAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mQuizSpinner.setAdapter(mQuizAdapter);
        mQuizSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quizSelection = parent.getItemAtPosition(position).toString();
                mPositionCheck = (position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //hardcoding here for the case where the user immediately clicks to quiz without clicking spinner
                //otherwise an error is thrown
                quizSelection = "Quiz 1 - A Test Quiz";
            }
        });
        mDemoSpinner = (Spinner) findViewById(R.id.spinner_demo);
        ArrayAdapter demoAdapter = ArrayAdapter.createFromResource(this, R.array.demo_choices, android.R.layout.simple_spinner_item);
        demoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDemoSpinner.setAdapter(demoAdapter);
        mDemoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    Intent intent = new Intent(DashboardActivity.this, QuizActivity.class);
                    intent.putExtra("Quiz", quizSelection);
                intent.putExtra("Username", mUsername);
                    startActivity(intent);
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

    private ArrayList<String> getLength() {
        SQLiteDatabase db = DBHandler.getHandler(DashboardActivity.this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT quiz FROM QUIZ", null);
        cursor.moveToFirst();
        //here a HashSet is used to get unique values (i.e. different quizzes)
        //from StackOverflow
        //https://stackoverflow.com/questions/12719998/how-to-count-unique-values-in-an-arraylist
        HashSet<String> set = new HashSet<String>();
        while (cursor.moveToNext()) {
            set.add(cursor.getString(cursor.getColumnIndex("quiz")));
            cursor.moveToNext();
        }
        ArrayList<String> list = new ArrayList<>(set);
        cursor.close();
        db.close();
        return list;
    }
}
