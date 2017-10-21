package com.example.chenz.infs3634quiz;

import android.content.ContentValues;
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
    String mUsername;
    Result[] mResultsBank;
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
        mUsername = getIntent().getExtras().getString("Username");
        mResultsBank = new Result[length];
        for (int i = 0; i < length; i++) {
            mResultsBank[i] = new Result();
            mResultsBank[i].setQuiz(quiz);
            mResultsBank[i].setStudent(mUsername);
        }
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
                mResultsBank[mCurrentIndex].setCorrectAnswer(mQuestionBank[mCurrentIndex].getmCorrectAnswer());
                mResultsBank[mCurrentIndex].setQuestion(mQuestionBank[mCurrentIndex].getmQuestionText());
                mResultsBank[mCurrentIndex].setResult("A");
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
                mResultsBank[mCurrentIndex].setCorrectAnswer(mQuestionBank[mCurrentIndex].getmCorrectAnswer());
                mResultsBank[mCurrentIndex].setQuestion(mQuestionBank[mCurrentIndex].getmQuestionText());
                mResultsBank[mCurrentIndex].setResult("B");
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
                mResultsBank[mCurrentIndex].setCorrectAnswer(mQuestionBank[mCurrentIndex].getmCorrectAnswer());
                mResultsBank[mCurrentIndex].setQuestion(mQuestionBank[mCurrentIndex].getmQuestionText());
                mResultsBank[mCurrentIndex].setResult("C");
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
                mResultsBank[mCurrentIndex].setCorrectAnswer(mQuestionBank[mCurrentIndex].getmCorrectAnswer());
                mResultsBank[mCurrentIndex].setQuestion(mQuestionBank[mCurrentIndex].getmQuestionText());
                mResultsBank[mCurrentIndex].setResult("D");
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

    private void uploadResults(Result result) {
        SQLiteDatabase db = DBHandler.getHandler(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", result.getQuestion());
        values.put("correctanswer", result.getCorrectAnswer());
        values.put("result", result.getResult());
        values.put("student", result.getStudent());
        values.put("quiz", result.getQuiz());
        db.insert("RESULTS", null, values);
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
            for (Result r : mResultsBank) {
                uploadResults(r);
            }
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
