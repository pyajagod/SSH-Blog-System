package com.qwy.blogs.bean;

public class UserBean {
    private int id;
    private String userId;
    private String userName;
    private String userPwd;
    private String userSex;
    private String userProsition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserProsition() {
        return userProsition;
    }

    public void setUserProsition(String userProsition) {
        this.userProsition = userProsition;
    }

    public UserBean() {
    }

    public UserBean(String userId, String userName, String userPwd, String userSex, String userProsition) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userSex = userSex;
        this.userProsition = userProsition;
    }

    @Override
    public String toString() {
        return "[ id = " +  id + ", userId = " + userId + ", userName = " + userName + ", userPwd = "+ userPwd + ", userSex = " + userSex + ", userProsition = " + userProsition + "]";
    }
}
