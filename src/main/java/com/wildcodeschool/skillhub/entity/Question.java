package com.wildcodeschool.skillhub.entity;

import java.sql.Date;

public class Question {

    private Long questionId;
    private Long questioner;
    private Date date;
    private String text;
    private Long category;
    private String categoryName;

    public Question() {
    }

    public Question(Long questionId, Long questioner, Date date, String text, Long category, String categoryName) {
        this.questionId = questionId;
        this.questioner = questioner;
        this.date = date;
        this.text = text;
        this.category = category;
        this.categoryName = categoryName;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}