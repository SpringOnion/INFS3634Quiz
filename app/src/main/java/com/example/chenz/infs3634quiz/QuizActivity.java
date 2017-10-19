package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    int mCorrectAnswers = 0;
    int mAnsweredQuestions = 0;
    private Button mButtonA;
    private Button mButtonB;
    private Button mButtonC;
    private Button mButtonD;
    private String mCorrectAnswer;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank;
    private int mCurrentIndex;

    {
        mCurrentIndex = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String quiz = getIntent().getExtras().getString("Quiz");
        mQuestionTextView = (TextView) findViewById(R.id.question_text_id);
        int length = getLength(quiz);
        mQuestionBank = new Question[length];
        for (int i = 0; i < length; i++) {
            mQuestionBank[i] = new Question();
        }
        mQuestionBank = getQuestions(quiz);


        mButtonA = (Button) findViewById(R.id.button_A);
        mButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionBank[mCurrentIndex].getmCorrectAnswer().equals("A")) {
                    mCorrectAnswers++;
                }
                mCurrentIndex++;
                updateQuestion();
            }
        });
        mButtonB = (Button) findViewById(R.id.button_B);
        mButtonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionBank[mCurrentIndex].getmCorrectAnswer().equals("B")) {
                    mCorrectAnswers++;
                }
                mCurrentIndex++;
                updateQuestion();
            }
        });
        mButtonC = (Button) findViewById(R.id.button_C);
        mButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCorrectAnswer.equals("C")) {
                    mCorrectAnswers++;
                }
                mCurrentIndex++;
                updateQuestion();
            }
        });
        mButtonD = (Button) findViewById(R.id.button_D);
        mButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCorrectAnswer.equals("D")) {
                    mCorrectAnswers++;
                }
                mCurrentIndex++;
                updateQuestion();
            }
        });

        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getmQuestionText());
        mButtonA.setText(mQuestionBank[mCurrentIndex].getmAnswerA());
        mButtonB.setText(mQuestionBank[mCurrentIndex].getmAnswerB());
        mButtonC.setText(mQuestionBank[mCurrentIndex].getmAnswerC());
        mButtonD.setText(mQuestionBank[mCurrentIndex].getmAnswerD());
        mCorrectAnswer = mQuestionBank[mCurrentIndex].getmCorrectAnswer();

    }
    private void updateQuestion(){
        mAnsweredQuestions++;
        if (mAnsweredQuestions < mQuestionBank.length) {
            String question = mQuestionBank[mCurrentIndex].getmQuestionText();
            mQuestionTextView.setText(question);
            mButtonA.setText(mQuestionBank[mCurrentIndex].getmAnswerA());
            mButtonB.setText(mQuestionBank[mCurrentIndex].getmAnswerB());
            mButtonC.setText(mQuestionBank[mCurrentIndex].getmAnswerC());
            mButtonD.setText(mQuestionBank[mCurrentIndex].getmAnswerD());
            mCorrectAnswer = mQuestionBank[mCurrentIndex].getmCorrectAnswer();
        } else if (mAnsweredQuestions >= mQuestionBank.length) {
            Intent intent = new Intent(QuizActivity.this, QuizResultActivity.class);
            intent.putExtra("Result", mCorrectAnswers);
            intent.putExtra("Questions", mQuestionBank.length);
            startActivity(intent);
        }
    }

    private int getLength(String quiz) {
        SQLiteDatabase db = DBHandler.getHandler(QuizActivity.this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT quiz FROM QUIZ WHERE quiz='" + quiz + "'; ", null);
        cursor.moveToFirst();
        int length = cursor.getCount();
        cursor.close();
        db.close();
        return length;
    }

    private Question[] getQuestions(String quiz) {
        SQLiteDatabase db = DBHandler.getHandler(QuizActivity.this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM QUIZ WHERE quiz='" + quiz + "'; ", null);
        cursor.moveToFirst();
        int length = cursor.getCount();
        Question[] questions = new Question[length];
        for (int i = 0; i < length; i++) {
            questions[i] = new Question();
            questions[i].setmQuestionText(cursor.getString(cursor.getColumnIndex("question")));
            questions[i].setmAnswerA(cursor.getString(cursor.getColumnIndex("answerA")));
            questions[i].setmAnswerB(cursor.getString(cursor.getColumnIndex("answerB")));
            questions[i].setmAnswerC(cursor.getString(cursor.getColumnIndex("answerC")));
            questions[i].setmAnswerD(cursor.getString(cursor.getColumnIndex("answerD")));
            questions[i].setmCorrectAnswer(cursor.getString(cursor.getColumnIndex("correctanswer")));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return questions;
    }
}
