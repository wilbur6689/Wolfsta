package com.teamwolf.daoInterfaces;

public interface CardDAOInterface {

    /**
     * initializes a deck of 108 cards (player id for cards in deck is 0?)
     * @param gameId id of games the cards are for
     */
    public void createDeck(int gameId);
}
