package com.teamwolf.beans;

public class Game {
    private int gameId = 0;
    private String gameName = "";
    private String gamePassword;
    private int players = 0;
    private int turn = 0;
    //TODO add to database
    private int round; //round number (used for determining who dealer is
    //TODO time limit
    //private int timeLimit = 0;
    private String gameStartDate;
    private String gameResolved;
    private int gameWon = 0;//TODO what are these for?
    private int gameLost = 0;
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
    }

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
