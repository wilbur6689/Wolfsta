package com.teamwolf.daoInterfaces;

import com.teamwolf.beans.Game;

public interface GameDAOInterface {

    /**
     * adds a game to the database
     * @param g the game to be added
     * @return the game that was added
     */
    public Game add(Game g);

    /**
     * gets a game of a given Id
     * @param gameId the game's id
     * @return the game
     */
    public Game getGame(int gameId);
}
