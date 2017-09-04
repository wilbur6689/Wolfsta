package com.teamwolf.beans;


import com.teamwolf.dataAccess.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name="USER_TABLE")
public class User implements DataObject{
    private int userid;
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
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
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
    @Transient
    @Override
    public Serializable getID()
    {
        return this.getUserid();
    }

}
