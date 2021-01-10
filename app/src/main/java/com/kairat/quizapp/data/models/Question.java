package com.kairat.quizapp.data.models;

import java.util.List;

public class Question {
    private String type;
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswer;
    private String trueAnswer;
    private String falseAnswer;

    public Question(String type, String question, String correctAnswer, List<String> incorrectAnswer) {
        this.type = type;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }

    public Question(String type, String question,String trueAnswer, String falseAnswer) {
        this.type = type;
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.falseAnswer = falseAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<String> getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public void setIncorrectAnswer(List<String> incorrectAnswer) {
        this.incorrectAnswer = incorrectAnswer;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getFalseAnswer() {
        return falseAnswer;
    }

    public void setFalseAnswer(String falseAnswer) {
        this.falseAnswer = falseAnswer;
    }
}
