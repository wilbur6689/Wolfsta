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
    boolean drawFromStock(int gameId, int playerId);

    /**
     * Removes a card from the stock(1) and places it on the top(4) of the discard pile
     * !!if there is a card on top already it is moved to discard(3)
     * @return int referring to the Card
     */
    int drawToTop(int gameId);

    /**
     *
     * @param gameId the game
     * @param playerId the player wh
     * @return
     */
    int[] getCurrentMeld(int gameId, int playerId);
}
