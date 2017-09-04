package com.teamwolf.beans;


import com.teamwolf.dataAccess.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name="TEAM")
public class Team implements DataObject
{
    private int teamId = 0;
    private int teamGamesWon = 0;
    private int teamGamesPlayed = 0;
    private int tourId = 0;
    private User playerOne;
    private User playerTwo;
    // protected Logger log = Logger.getRootLogger();

    /**
     * Constructor
     */
    public Team() {

    }// public Team()

    @Id
    @Column(name = "TEAM_ID")
    public int getTeamId() {
        return teamId;
    }// public int getTeamId()
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }// public void setTeamId(int teamId)

    @Column(name = "TEAM_GAMES_WON")
    public int getTeamGamesWon() {
        return teamGamesWon;
    }// public int getTeamGamesWon()
    public void setTeamGamesWon(int teamGamesWon) {
        this.teamGamesWon = teamGamesWon;
    }// public void setTeamGamesWon(int teamGamesWon)

    @Column(name = "TEAM_GAMES_PLAYED")
    public int getTeamGamesPlayed() {
        return teamGamesPlayed;
    }// public int getTeamGamesPlayed()
    public void setTeamGamesPlayed(int teamGamesPlayed) {
        this.teamGamesPlayed = teamGamesPlayed;
    }// public void setTeamGamesPlayed(int teamGamesPlayed)

    @Column(name = "TOUR_ID")
    public int getTourId() {
        return tourId;
    }// public int getTourId()
    public void setTourId(int tourId) {
        this.tourId = tourId;
    }// public void setTourId(int tourId)

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID1", referencedColumnName="USER_ID")
    public User getPlayerOne() {
        return playerOne;
    }// public Player getPlayerOne()
    public void setPlayerOne(User playerOne) {
        this.playerOne = playerOne;
    }// public void setPlayerOne(Player playerOne)

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID2", referencedColumnName="USER_ID")
    public User getPlayerTwo() {
        return playerTwo;
    }// public Player getPlayerTwo()
    public void setPlayerTwo(User playerTwo) {
        this.playerTwo = playerTwo;
    }// public void setPlayerTwo(Player playerTwo)

    @Transient
    @Override
    public Serializable getID()
    {
        return this.getTeamId();
    }
}// public class Team
