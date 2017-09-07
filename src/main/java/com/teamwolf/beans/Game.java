package com.teamwolf.beans;

import com.teamwolf.dataAccess.*;

import javax.persistence.*;
import java.io.*;
import java.sql.*;

@Entity
@Table(name="GAME")
public class Game implements DataObject{
    private int gameId = 0;
    private String gameName = "";
    private String gamePassword;
    private int players = 0;
    private int turn = 0;
    private int round; //round number (used for determining who dealer is
  private int timeLimit = 0;
    private Timestamp gameStartDate;
    private Timestamp gameResolved;
    private int gameWon = 0;
    private int gameLost = 0;
    private int gameState;
    // protected Logger log = Logger.getRootLogger();

    /**
     * Constructor
     */
    public Game() {

    }// public Game()

    /**
     * Basic New Game Constructor
     * @param gameName identifier to other users
     * @param gamePassword can be null
     * @param players number of players
     */
    public Game(String gameName, String gamePassword, int players) {
        this.gameName = gameName;
        this.gamePassword = gamePassword;
        this.players = players;
        this.turn = 1;
        this.round = 1;
        this.gameState = 1;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    @Id
    @Column(name = "GAME_ID")
    public int getGameId() {
        return gameId;
    }// public int getGameId()
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }// public void setGameId(int gameId)

    @Column(name = "GAME_NAME")
    public String getGameName() {
        return gameName;
    }// public String getGameName()
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }// public void setGameName(String gameName)

    @Column(name = "GAME_PASSWORD")
    public String getGamePassword() {
        return gamePassword;
    }// public int getGamePassword()
    public void setGamePassword(String gamePassword) {
        this.gamePassword = gamePassword;
    }

    public int getPlayers() {
        return players;
    }// public int getPlayers()
    public void setPlayers(int players) {
        this.players = players;
    }// public void setPlayers(int players)

    public int getTurn() {
        return turn;
    }// public int getTurn()
    public void setTurn(int turn) {
        this.turn = turn;
    }// public void setTurn(int turn)

    @Column(name = "GAME_WON")
    public int getGameWon() {
        return gameWon;
    }// public int getGameWon()
    public void setGameWon(int gameWon) {
        this.gameWon = gameWon;
    }// public void setGameWon(int gameWon)

    @Column(name = "GAME_LOST")
    public int getGameLost() {
        return gameLost;
    }// public int getGameLost()
    public void setGameLost(int gameLost) {
        this.gameLost = gameLost;
    }// public void setGameLost(int gameLost)


    public int getRound() { return round; }
    public void setRound(int round) { this.round = round; }

    @Column(name = "GAME_START_DATE")
    public Timestamp getGameStartDate()
    {
        return gameStartDate;
    }
    public void setGameStartDate(Timestamp gameStartDate)
    {
        this.gameStartDate = gameStartDate;
    }

    @Column(name = "GAME_RESOLVED")
    public Timestamp getGameResolved()
    {
        return gameResolved;
    }
    public void setGameResolved(Timestamp gameResolved)
    {
        this.gameResolved = gameResolved;
    }

    @Transient
    @Override
    public Serializable getID()
    {
        return null;
    }
}// public class Game
