package com.wildcodeschool.skillhub.entity;

import java.sql.Date;

public class Answer {

    private Long answerId;
    private Long question;
    private Long expert;
    private Date date;
    private String answerText;

    public Answer() {
    }

    public Answer(Long answerId, Long question, Long expert, Date date, String answerText) {
        this.answerId = answerId;
        this.question = question;
        this.expert = expert;
        this.date = date;
        this.answerText = answerText;
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

}