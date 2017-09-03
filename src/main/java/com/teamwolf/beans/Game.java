package com.teamwolf.beans;

public class Game {
    private int gameId = 0;
    private String gameName = "";
    private int gamePassword = 0 ;
    private int players = 0;
    private int turn = 0;
//    private int timeLimit = 0;
//    private int gameStartDate = 0; Talk to phillip about the game start date.
//    private int game_resolved;
    private int gameWon = 0;
    private int gameLost = 0;
    // protected Logger log = Logger.getRootLogger();

    /**
     * Constructor
     */
    public Game() {

    }// public Game()

    public int getGameId() {
        return gameId;
    }// public int getGameId()

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }// public void setGameId(int gameId)

    public String getGameName() {
        return gameName;
    }// public String getGameName()

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }// public void setGameName(String gameName)

    public int getGamePassword() {
        return gamePassword;
    }// public int getGamePassword()

    public void setGamePassword(int gamePassword) {
        this.gamePassword = gamePassword;
    }// public void setGamePassword(int gamePassword)

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

    public int getGameWon() {
        return gameWon;
    }// public int getGameWon()

    public void setGameWon(int gameWon) {
        this.gameWon = gameWon;
    }// public void setGameWon(int gameWon)

    public int getGameLost() {
        return gameLost;
    }// public int getGameLost()

    public void setGameLost(int gameLost) {
        this.gameLost = gameLost;
    }// public void setGameLost(int gameLost)
}// public class Game
