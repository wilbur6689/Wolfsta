package com.teamwolf.daoInterfaces;

import com.teamwolf.beans.Game;

public interface GameDAOInterface {

    /**
     * initializes a new game in database
     * @return game id of a new game
     */
    public int newGame();

    /**
     * gets a game of a given Id
     * @param gameId the game's id
     * @return the game
     */
    public Game getGame(int gameId);
}
