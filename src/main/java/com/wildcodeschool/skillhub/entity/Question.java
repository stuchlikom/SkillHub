package com.wildcodeschool.skillhub.entity;

import java.sql.Date;

public class Question {

    private Long questionId;
    private Long questioner;
    private Date questionDate;
    private Date answerDate;
    private String questionText;
    private Long category;
    private String categoryName;
    private String answerText;

    public Question() {
    }

    public Question(Long questionId, Long questioner, Date questionDate, Date answerDate, String questionText, Long category, String categoryName, String answerText) {
        this.questionId = questionId;
        this.questioner = questioner;
        this.questionDate = questionDate;
        this.answerDate = answerDate;
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

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
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