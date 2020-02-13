package com.wildcodeschool.skillhub.entity;

import java.util.ArrayList;
import java.util.List;

public class Expert
{
    private Long userId;
    private String name;
    private String firstName;
    private String nickName;
    private List <Category> categoryList;

    public Expert() { }

    public Expert(
    Long userId,
    String name,
    String firstName,
    String nickName,
    List <Category> categoryList // id und Name
    )

    {
        this.userId = userId;
        this.name = name;
        this.firstName = firstName;
        this.nickName = nickName;
        this.categoryList = categoryList;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFirstName() {
        return firstName;
    }
        public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List <Category> getCategoryList() {
        return categoryList;
    }
    public void setCategoryList(List <Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getDummyList(){
        return new ArrayList<>();
    }
}
   

