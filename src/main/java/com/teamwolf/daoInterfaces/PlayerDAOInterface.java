package com.teamwolf.daoInterfaces;

import com.teamwolf.beans.Player;
import com.teamwolf.canasta.CanastaPlayer;

import java.util.ArrayList;
import java.util.Map;

public interface PlayerDAOInterface {

    /**
     * adds player to the database given a current game
     * @param p player to add
     */
    Player add(Player p);

    /**
     * removes a player from the game
     * @param p player to be deleted
     */
    void delete(Player p);

    /**
     * gets a player given criteria
     * @param compKey constraints
     * @return the player
     */
    Player getByCompositeKey(Map<String, Object> compKey);
}