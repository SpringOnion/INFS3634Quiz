package com.example.chenz.infs3634quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
    //Database handling class
    private static String SQL_CREATE_QUIZ = "CREATE TABLE QUIZ ( _id integer primary key autoincrement, question text not null, " +
            "answerA text, answerB text, answerC text, answerD text, correctanswer text, quiz text not null); ";
    private static String SQL_CREATE_LOGIN = "CREATE TABLE LOGIN ( _id integer primary key autoincrement, username text, password text, userType text); ";
    private static String SQL_CREATE_RESULTS = "CREATE TABLE RESULTS ( _id integer primary key autoincrement, question text, correctanswer text, result text, " +
            "student text, quiz text); ";
    private static DBHandler instance;
    private DBHandler dbHandler;
    private SQLiteDatabase db;

    private DBHandler(Context context) {
        super(context, "Quiz.db", null, 1);
    }

    public static synchronized DBHandler getHandler(Context context) {
        //method to retrieve static instance of DBHandler from across application
        //based on
        //http://www.androiddesignpatterns.com/2012/05/correctly-managing-your-sqlite-database.html
        if (instance == null) {
            instance = new DBHandler(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LOGIN);
        db.execSQL(SQL_CREATE_QUIZ);
        db.execSQL(SQL_CREATE_RESULTS);
        dbHandler = this;
        this.db = db;
        createBaseLogins();
        createBaseQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //below hardcoded values are added for demonstration and testing purposes

    private void createBaseLogins() {
        ContentValues student = new ContentValues();
        student.put("username", "z1234567");
        student.put("password", "student1");
        student.put("usertype", "student");

        ContentValues teacher = new ContentValues();
        teacher.put("username", "z1111111");
        teacher.put("password", "teacher1");
        teacher.put("usertype", "teacher");

        db.insert("LOGIN", null, student);
        db.insert("LOGIN", null, teacher);
    }

    private void createBaseQuestions() {
        //5 base questions for week 1 and demonstration purposes
        Question[] questions = new Question[5];
        for (int i = 0; i < 5; i++) {
            questions[i] = new Question();
            questions[i].setmQuizName("Quiz 1 - A Test Quiz");
        }
        questions[1].setmQuestionText("What is the current Android API level?");
        questions[1].setmAnswerA("25");
        questions[1].setmAnswerB("26");
        questions[1].setmAnswerC("24");
        questions[1].setmAnswerD("27");
        questions[1].setmCorrectAnswer("B");

        questions[2].setmQuestionText("Which of these is the best way to access an email function?");
        questions[2].setmAnswerA("Explicit Intent");
        questions[2].setmAnswerB("Implicit Intent");
        questions[2].setmAnswerC("Fragment");
        questions[2].setmAnswerD("Task");
        questions[2].setmCorrectAnswer("B");

        questions[3].setmQuestionText("Which of these is NOT a way to import a library for Android?");
        questions[3].setmAnswerA("Import a Jar file");
        questions[3].setmAnswerB("Configure build scripts");
        questions[3].setmAnswerC("Import Android Module");
        questions[3].setmAnswerD("Import executable file");
        questions[3].setmCorrectAnswer("D");

        questions[4].setmQuestionText("What is the current Android API level?");
        questions[4].setmAnswerA("25");
        questions[4].setmAnswerB("26");
        questions[4].setmAnswerC("24");
        questions[4].setmAnswerD("27");
        questions[4].setmCorrectAnswer("B");

        questions[0].setmQuestionText("What is the current Android API level?");
        questions[0].setmAnswerA("25");
        questions[0].setmAnswerB("26");
        questions[0].setmAnswerC("24");
        questions[0].setmAnswerD("27");
        questions[0].setmCorrectAnswer("B");

        for (Question q : questions) {
            ContentValues values = new ContentValues();
            values.put("question", q.getmQuestionText());
            values.put("answerA", q.getmAnswerA());
            values.put("answerB", q.getmAnswerB());
            values.put("answerC", q.getmAnswerC());
            values.put("answerD", q.getmAnswerD());
            values.put("correctanswer", q.getmCorrectAnswer());
            values.put("quiz", q.getmQuizName());
            db.insert("QUIZ", null, values);
        }
    }
}