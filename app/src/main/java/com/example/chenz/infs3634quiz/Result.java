package com.example.chenz.infs3634quiz;

/*
    Result class - contains question text, the quiz, the student, the correct answer, and the student's response.
 */

public class Result {
    String question;
    String correctAnswer;
    String result;
    String student;
    String quiz;

    public Result() {
    }

    public Result(String question, String correctAnswer, String result, String student, String quiz) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.result = result;
        this.student = student;
        this.quiz = quiz;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
