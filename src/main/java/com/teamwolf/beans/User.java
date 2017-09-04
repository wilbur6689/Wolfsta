package com.teamwolf.beans;

public class User {
    private int userid;
    private String username;
    private String password;
    private int gamesPlayed;
    private int gamesWon;
    // protected Logger log = Logger.getRootLogger();

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
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

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
