package com.wildcodeschool.skillhub.entity;

public class Expert
{
    private Long userid;
    private Long categoryid;
    private String name;
    private String firstname;
    private String nickname;
    private String categoryname;

    public Expert() { }

    public Expert(
    Long userid,
    Long categoryid,
    String name,
    String firstname,
    String nickname,
    String categoryname
    )

    {
        this.userid = userid;
        this.categoryid = categoryid;
        this.name = name;
        this.firstname = firstname;
        this.nickname = nickname;
        this.categoryname = categoryname;
    }

    public Long getExpertId() {
        return userid;
    }
    public void setExpertId(Long userid) {
        this.userid = userid;
    }

    public Long getExpertCategoryId() {
        return categoryid;
    }
    public void setExpertCategoryId(Long categoryid) {
        this.categoryid = categoryid;
    }
    
    public String getExpertName() {
        return name;
    }
    public void setExpertName(String name) {
        this.name = name;
    }

    public String getExpertFirstName() {
        return firstname;
    }
    public void setExpertFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getExpertNickName() {
        return nickname;
    }
    public void setExpertNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getExpertCategoryName() {
        return categoryname;
    }
    public void setExpertCategoryName(String categoryname) {
        this.categoryname = categoryname;
    }
}
   

