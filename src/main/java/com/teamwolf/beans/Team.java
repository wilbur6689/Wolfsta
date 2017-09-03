package com.teamwolf.beans;

public class Team {
    private int teamId = 0;
    private int teamGamesWon = 0;
    private int teamGamesPlayed = 0;
    private int tourId = 0;
    private Player playerOne;
    private Player playerTwo;

    /**
     * Constructor
     */
    public Team() {

    }// public Team()

    public int getTeamId() {
        return teamId;
    }// public int getTeamId()

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }// public void setTeamId(int teamId)

    public int getTeamGamesWon() {
        return teamGamesWon;
    }// public int getTeamGamesWon()

    public void setTeamGamesWon(int teamGamesWon) {
        this.teamGamesWon = teamGamesWon;
    }// public void setTeamGamesWon(int teamGamesWon)

    public int getTeamGamesPlayed() {
        return teamGamesPlayed;
    }// public int getTeamGamesPlayed()

    public void setTeamGamesPlayed(int teamGamesPlayed) {
        this.teamGamesPlayed = teamGamesPlayed;
    }// public void setTeamGamesPlayed(int teamGamesPlayed)

    public int getTourId() {
        return tourId;
    }// public int getTourId()

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }// public void setTourId(int tourId)

    public Player getPlayerOne() {
        return playerOne;
    }// public Player getPlayerOne()

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }// public void setPlayerOne(Player playerOne)

    public Player getPlayerTwo() {
        return playerTwo;
    }// public Player getPlayerTwo()

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }// public void setPlayerTwo(Player playerTwo)
}// public class Team
