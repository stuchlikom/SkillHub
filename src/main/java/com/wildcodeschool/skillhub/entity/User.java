package com.wildcodeschool.skillhub.entity;

public class User
{
    private Long userid;
    private String name;
    private String firstname;
    private String nickname;
    private String avatar;
    private boolean expert;
    private String role;
    private String mailadress;
    private String password;
    private int category;

    public User() { }

    public User(
    Long userid,
    String name,
    String firstname,
    String nickname,
    String avatar,
    boolean expert,
    String role,
    String mailadress,
    String password,
    int category)

    {
        this.userid = userid;
        this.name = name;
        this.firstname = firstname;
        this.nickname = nickname;
        this.avatar = avatar;
        this.expert = expert;
        this.role = role;
        this.mailadress = mailadress;
        this.password = password;
        this.category = category;
    }

    public Long getUserId() {
        return userid;
    }
    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstname;
    }
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getNickName() {
        return nickname;
    }
    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isExpert() {
        return expert;
    }
    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getMailAdress() {
        return mailadress;
    }
    public void setMailAdress(String mailadress) {
        this.mailadress = mailadress;
    }

    public String getPassWord() {
        return password;
    }
    public void setPassWord(String password) {
        this.password = password;
    }

    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
}

