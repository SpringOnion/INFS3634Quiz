package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    int mCorrectAnswers = 0;
    int mAnsweredQuestions = 0;
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
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
        mAnsweredQuestions++;
        if (mAnsweredQuestions <= mQuestionBank.length) {
            mQuestionTextView.setText(question);
        } else if (mAnsweredQuestions > mQuestionBank.length) {
            Intent intent = new Intent(QuizActivity.this, QuizResultActivity.class);
            intent.putExtra("Result", mCorrectAnswers);
            intent.putExtra("Questions", mQuestionBank.length);
            startActivity(intent);
        }
    }

    private void checkAnswer(Boolean b){
        if(b == mQuestionBank[mCurrentIndex].ismAnswerTrue()){
            Toast toast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
            toast.show();
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            mCorrectAnswers++;
            updateQuestion();
        } else {
            Toast toast = Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
            toast.show();
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        }
    }
}
