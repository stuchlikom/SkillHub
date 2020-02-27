package com.wildcodeschool.skillhub.entity;

public class Category implements Comparable<Category> {

    private Long categoryId;
    private String categoryName;

    public Category() {
    }

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;

    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int compareTo(Category otherCategory) {
        return this.categoryName.compareTo(otherCategory.categoryName);
    }

    




}