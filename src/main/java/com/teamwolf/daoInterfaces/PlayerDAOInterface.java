package com.teamwolf.daoInterfaces;

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
}
