package com.teamwolf.daoInterfaces;

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
    boolean drawFromStock(int playerId);
}
