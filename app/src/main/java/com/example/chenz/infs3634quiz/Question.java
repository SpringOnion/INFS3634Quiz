package com.example.chenz.infs3634quiz;

/**
 * Created by chenz on 16/08/2017.
 */

public class Question {
    private int mTextResId;
    private String mQuestionText;
    private String mAnswerA;
    private String mAnswerB;
    private String mAnswerC;
    private String mAnswerD;
    private String mCorrectAnswer;
    private String mQuizName;

    public Question() {
    }

    public Question(String mQuestionText, String mAnswerA, String mAnswerB, String mAnswerC, String mAnswerD, String mCorrectAnswer, String mQuizName) {
        this.mQuestionText = mQuestionText;
        this.mAnswerA = mAnswerA;
        this.mAnswerB = mAnswerB;
        this.mAnswerC = mAnswerC;
        this.mAnswerD = mAnswerD;
        this.mCorrectAnswer = mCorrectAnswer;
        this.mQuizName = mQuizName;

    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public String getmQuestionText() {
        return mQuestionText;
    }

    public void setmQuestionText(String mQuestionText) {
        this.mQuestionText = mQuestionText;
    }

    public String getmAnswerA() {
        return mAnswerA;
    }

    public void setmAnswerA(String mAnswerA) {
        this.mAnswerA = mAnswerA;
    }

    public String getmAnswerB() {
        return mAnswerB;
    }

    public void setmAnswerB(String mAnswerB) {
        this.mAnswerB = mAnswerB;
    }

    public String getmAnswerC() {
        return mAnswerC;
    }

    public void setmAnswerC(String mAnswerC) {
        this.mAnswerC = mAnswerC;
    }

    public String getmAnswerD() {
        return mAnswerD;
    }

    public void setmAnswerD(String mAnswerD) {
        this.mAnswerD = mAnswerD;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setmCorrectAnswer(String mCorrectAnswer) {
        this.mCorrectAnswer = mCorrectAnswer;
    }

    public String getmQuizName() {
        return mQuizName;
    }

    public void setmQuizName(String mQuizName) {
        this.mQuizName = mQuizName;
    }
}