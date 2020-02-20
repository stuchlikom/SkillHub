package com.wildcodeschool.skillhub.entity;

public class UserCategoryVektor{

    private Long userId;
    private Long categoryId;
    private Boolean hasCategory;

    public UserCategoryVektor(Long userId, Long categoryId,Boolean hasCategory) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.hasCategory = hasCategory;
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

    public Boolean isHasCategory() {
        return hasCategory;
    }
    public void setHasCategory(Boolean hasCategory) {
        this.hasCategory = hasCategory;
    }

}
