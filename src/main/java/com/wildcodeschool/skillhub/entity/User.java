package com.wildcodeschool.skillhub.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nickname"))
public class User
{
    private Long userid;
    private String name;
    private String firstname;
    private String nickname;
    private String role;
    private String mailadress;
    private String password;

    public User() { }

    public User(
    Long userid,
    String name,
    String firstname,
    String nickname,
    String role,
    String mailadress,
    String password)

    {
        this.userid = userid;
        this.name = name;
        this.firstname = firstname;
        this.nickname = nickname;
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

    @NotNull
    @Size(min=1, max=45, message = "Der Nachname muss mindestens ein und höchstens 45 Zeichen lang sein.")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min=1, max=45, message = "Der Vorname muss mindestens ein und höchstens 45 Zeichen lang sein.")
    public String getFirstName() {
        return firstname;
    }

     
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    @NotNull
	@Size(min=3, max=45, message = "Der Nickname muss mindestens drei und höchstens 45 Zeichen lang sein.")
    @Column(unique = true)
    public String getNickName() {
        return nickname;
    }
    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @NotNull
    @Size(max=45, message="Die Mailadresse darf höchstens 45 Zeichen lang sein.")
    @Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Falsches Mailformat")
    public String getMailAdress() {
        return mailadress;
    }
    public void setMailAdress(String mailadress) {
        this.mailadress = mailadress;
    }

    @NotNull
	@Size(min=3, max=45, message = "Das Passwort muss mindestens drei und höchstens 45 Zeichen lang sein.")
    public String getPassWord() {
        return password;
    }
    public void setPassWord(String password) {
        this.password = password;
    }
}

