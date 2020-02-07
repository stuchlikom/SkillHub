package com.wildcodeschool.skillhub.entity;

import java.sql.Blob;

public class User
{
    private Long userid;
    private String name;
    private String firstname;
    private String nickname;
    private Blob avatar;

    private String role;
    private String mailadress;
    private String password;

    public User() { }

    public User(
    Long userid,
    String name,
    String firstname,
    String nickname,
    Blob avatar,
    String role,
    String mailadress,
    String password)

    {
        this.userid = userid;
        this.name = name;
        this.firstname = firstname;
        this.nickname = nickname;
        this.avatar = avatar;
        this.role = role;
        this.mailadress = mailadress;
        this.password = password;
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

    public Blob getAvatar() {
        return avatar;
    }
    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
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
}

