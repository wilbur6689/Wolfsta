package com.teamwolf.beans;


import javax.persistence.*;
import java.io.*;
import java.sql.*;

@Entity
@Table(name="USER_SESSION")
public class UserSession
{
    private String token;
    private int userId = 0;
    private Timestamp startTime;

    /**
     * Constructor
     */
    public UserSession() {

    }// public UserSession()

    @Id
    public String getToken() {
        return token;
    }// public int getToken()

    public void setToken(String token) {
        this.token = token;
    }// public void setToken(int token)

    @Column(name="USER_ID")
    public int getUserId() {
        return userId;
    }// public int getUserId()

    public void setUserId(int userId) {
        this.userId = userId;
    }// public void setUserId(int userId)

    @Column(name = "START_TIME")
    public Timestamp getStartTime() {
        return startTime;
    }// public Date getStartTime()

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }// public void setStartTime(Date startTime)


}// public class UserSession
