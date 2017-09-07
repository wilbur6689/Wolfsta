package com.teamwolf.daoInterfaces;

import com.teamwolf.beans.*;
import com.teamwolf.enums.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface CardDAOInterface {

    /**
     * adds a card
     * @param c the card
     * @return the card
     */
    CardLookup add(CardLookup c);

    /**
     * gets a card from cardLookup given certain constraints
     * @param constraints the criteria
     * @return the card desired
     */
    CardLookup getByCompositeKey(Map<String, Object> constraints);

    /**
     * gets multiple cards from cardLookup given certain constraints
     * @param constraints the criteria
     * @return the cards desired
     */
    Collection<CardLookup> getByCompositeMap(Map<String, Object> constraints);

    /**
     *updates a card in database
     * @param card updated card
     */
    void update(CardLookup card);
}
