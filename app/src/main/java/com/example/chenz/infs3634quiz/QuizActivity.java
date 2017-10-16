package com.example.chenz.infs3634quiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private Button mNextButton;
    private Button mPreviousButton;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_api, true),
            new Question(R.string.question_activity, true),
            new Question(R.string.question_webapps, true),
            new Question(R.string.question_hardcode, false),
            new Question(R.string.question_privilege, false),
            new Question(R.string.question_store, true),
    };
    private int mCurrentIndex;
    {
        mCurrentIndex = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_id);
        updateQuestion();

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex - 1 < 0){
                    mCurrentIndex = mQuestionBank.length - 1;
                } else {
                    mCurrentIndex = mCurrentIndex - 1;
                }
                updateQuestion();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });
    }
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(Boolean b){
        if(b == mQuestionBank[mCurrentIndex].ismAnswerTrue()){
            Toast toast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
            toast.show();
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        } else {
            Toast toast = Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
