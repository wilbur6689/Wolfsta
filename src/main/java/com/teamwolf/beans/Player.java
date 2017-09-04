package com.teamwolf.beans;

// import org.apache.log4j.Logger;

public class Player {

    private int playerId = 0;
    private int playerNumber = 0;
    private int score = 0;
    private int user_id = 0;
    private int game_id = 0;
    private int team_team_id = 0;
    private int team_number = 0;
    // protected Logger log = Logger.getRootLogger();

    /**
     * Constructor
     */
    public Player() {

    }// public Player()

    public int getPlayerId() {
        return playerId;
    }// public int getPlayerId()

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }// public void setPlayerId(int playerId)

    public int getPlayerNumber() {
        return playerNumber;
    }// public int getPlayerNumber()

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }// public void setPlayerNumber(int playerNumber)

    public int getScore() {
        return score;
    }// public int getScore()

    public void setScore(int score) {
        this.score = score;
    }// public void setScore(int score)

    public int getUser_id() {
        return user_id;
    }// public int getUser_id()

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }// public void setUser_id(int user_id)

    public int getGame_id() {
        return game_id;
    }// public int getGame_id()

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }// public void setGame_id(int game_id)

    public int getTeam_team_id() {
        return team_team_id;
    }// public int getTeam_team_id()

    public void setTeam_team_id(int team_team_id) {
        this.team_team_id = team_team_id;
    }// public void setTeam_team_id(int team_team_id)

    public int getTeam_number() {
        return team_number;
    }// public int getTeam_number()

    public void setTeam_number(int team_number) {
        this.team_number = team_number;
    }// public void setTeam_number(int team_number)
}// public class Player
