package com.teamwolf.beans;


import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.io.*;

@Component
@Scope(value = "prototype")
@Entity
@Table(name="USER_TABLE")
public class User {
    private Integer userid;
    private String username;
    private String password;
    private int gamesPlayed;
    private int gamesWon;
    // protected Logger log = Logger.getRootLogger();

    public User () {} // needed for hibernate

    /**
     * This constructor is for creating a new user
     * @param username desired username
     * @param password desired password
     */
    public User(String username, String password) {
        this.userid = -1; // determined on insert into database
        this.username = username;
        this.password = password;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    /**
     * full constructor
     */
    public User(int userid, String username, String password, int gamesPlayed, int gamesWon) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }

    //*******************
    //Getters and Setters
    //*******************

    @Id
    @Column(name="USER_ID")
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Column(name="USERNAME")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="USER_GAMES_PLAYED")
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Column(name="USER_GAMES_WON")
    public int getGamesWon() {
        return gamesWon;
    }
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }


    @Transient
    public int getRating(){
        if (this.gamesPlayed == 0){
            return 0;
        }
        else{
            int rating = this.gamesWon * this.gamesWon / this.gamesPlayed;
            return rating;
        }

    }


}
