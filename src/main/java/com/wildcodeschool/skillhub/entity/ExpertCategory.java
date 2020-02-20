package com.wildcodeschool.skillhub.entity;

public class ExpertCategory {

    private Long userId;
    private Long categoryId;

    public ExpertCategory() {
    }

    public ExpertCategory (Long userId, Long categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
