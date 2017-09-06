package com.teamwolf.daoInterfaces;

import com.teamwolf.canasta.CanastaPlayer;

import java.util.ArrayList;

public interface PlayerDAOInterface {

    /**
     * adds player to the database given a current game
     * @param gameId game to add to
     */
    void addPlayer(int gameId);

    /**
     * removes a player from the game
     * @param gameId game to be removed from
     * @param playerId player to be removed
     */
    void removePlayer(int gameId,int playerId);

    /**
     * returns an arraylist with all the players in the game
     * TO?DO substitute human players for ai ones if less than player count
     * @param gameId the game
     * @param playerCount number of players in the game
     * @return Arraylist of Canasta Players
     */
    ArrayList<CanastaPlayer> getPlayers(int gameId, int playerCount);

    /**
     * gets the player number (1-8)
     * @param gameId the game
     * @param playerId the player's id
     * @return player number
     */
    int getPlayerNumber(int gameId, int playerId);

    /**
     * gets the playerId of a player in a game based on their position in the game
     * @param gameId the game
     * @param playerNumber the position of the player
     * @return playerId of player in a certain position
     */
    int getPlayerId(int gameId, int playerNumber);

    /**
     * fetches the current score of the player
     * @param gameid the game
     * @param playerid the player
     * @return score of the player
     */
    int getScore(int gameid, int playerid);
}
