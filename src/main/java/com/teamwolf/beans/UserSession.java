package com.teamwolf.beans;

import java.util.Date;

public class UserSession {
    private int token = 0;
    private int userId = 0;
    private Date startTime;

    /**
     * Constructor
     */
    public UserSession() {

    }// public UserSession()

    public int getToken() {
        return token;
    }// public int getToken()

    public void setToken(int token) {
        this.token = token;
    }// public void setToken(int token)

    public int getUserId() {
        return userId;
    }// public int getUserId()

    public void setUserId(int userId) {
        this.userId = userId;
    }// public void setUserId(int userId)

    public Date getStartTime() {
        return startTime;
    }// public Date getStartTime()

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }// public void setStartTime(Date startTime)
}// public class UserSession
