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
    private String questionNick;
    private String answerNick;
    private Long expert;

    public Question() {
    }

    public Question(Long questionId, Long questioner, Date questionDate, Date answerDate, String questionText, Long category, String categoryName, String answerText, String questionNick, String answerNick, Long expert) {
        this.questionId = questionId;
        this.questioner = questioner;
        this.questionDate = questionDate;
        this.answerDate = answerDate;
        this.questionText = questionText;
        this.category = category;
        this.categoryName = categoryName;
        this.answerText = answerText;
        this.questionNick = questionNick;
        this.answerNick = answerNick;
        this.expert = expert;
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

    public String getQuestionNick() {
        return questionNick;
    }

    public void setQuestionNick(String questionNick) {
        this.questionNick = questionNick;
    }

    public String getAnswerNick() {
        return answerNick;
    }

    public void setAnswerNick(String answerNick) {
        this.answerNick = answerNick;
    }

    public Long getExpert() {
        return expert;
    }

    public void setExpert(Long expert) {
        this.expert = expert;
    }

}