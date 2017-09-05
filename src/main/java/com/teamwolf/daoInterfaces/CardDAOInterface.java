package com.teamwolf.daoInterfaces;

import com.teamwolf.beans.Card;
import com.teamwolf.enums.CardEnum;

import java.util.ArrayList;

public interface CardDAOInterface {

    /**
     * initializes a deck of 108 cards (player id for cards in deck is 0?)
     * @param gameId id of games the cards are for
     */
    void createDeck(int gameId);

    /**
     * changes a card in the stock(CardState 1) to in the hand(CardState 2) of a given player
     * !!if it is a red 3 it is added to the meld instead and the method is recalled!!
     * @param playerId player that is drawing
     * @return true except when a 3 was drawn and the stock is now empty(or if called when stock was empty which shouldn't happen)
     */
    boolean drawFromStock(int gameId, int playerId);

    /**
     * Removes a card from the stock(1) and places it on the top(4) of the discard pile
     * !!if there is a card on top already it is moved to discard(3)
     * @return int referring to the Card
     */
    int drawToTop(int gameId);

    /**
     * gets a list of cards in a given players meld
     * @param gameId the game
     * @param playerId the player who owns the meld
     * @return an arrayList of Cards that have the corresponding playerid and are in a meld
     */
    ArrayList<Card> getCurrentMeld(int gameId, int playerId);

    /**
     * updates cards to reflect they've been melded
     * @param gameId the game
     * @param playerId the player that is melding
     * @param meld an array of the cardIds that are in this meld
     */
    void addMeld(int gameId, int playerId, int[] meld);
}
