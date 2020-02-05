package com.wildcodeschool.skillhub.entity;

import java.sql.Date;

public class Answer {

    private Long answerId;
    private Long question;
    private Long expert;
    private Date date;
    private String answerText;
    private Long category;

    public Answer() {
    }

    public Answer(Long answerId, Long question, Long expert, Date date, String answerText, Long category) {
        this.answerId = answerId;
        this.question = question;
        this.expert = expert;
        this.date = date;
        this.answerText = answerText;
        this.category = category;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public Long getExpert() {
        return expert;
    }

    public void setExpert(Long expert) {
        this.expert = expert;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}