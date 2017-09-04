package com.teamwolf.daoInterfaces;

public interface PlayerDAOInterface {

    /**
     * adds player to the database given a current game
     * @param gameId
     */
    void addPlayer(int gameId);
}
