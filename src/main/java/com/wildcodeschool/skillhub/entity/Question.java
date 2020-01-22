package com.wildcodeschool.skillhub.entity;

import java.sql.Date;

public class Question {

    private Long questionId;
    private Long questioner;
    private Date date;
    private String questionText;
    private Long category;
    private String categoryName;
    private String answerText;

    public Question() {
    }

    public Question(Long questionId, Long questioner, Date date, String questionText, Long category, String categoryName, String answerText) {
        this.questionId = questionId;
        this.questioner = questioner;
        this.date = date;
        this.questionText = questionText;
        this.category = category;
        this.categoryName = categoryName;
        this.answerText = answerText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestioner() {
        return questioner;
    }

    public void setQuestioner(Long questioner) {
        this.questioner = questioner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}