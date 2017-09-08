package com.teamwolf.canasta;

import com.teamwolf.beans.CardLookup;
import com.teamwolf.enums.Card;

import java.util.ArrayList;

public class SimpleAi extends CanastaPlayer{

    //TODO
    @Override
    protected boolean draw() {
        Canasta ref = new Canasta();
        ArrayList<CardLookup> hand = new ArrayList<>();
        hand.addAll(ref.getHand(this));
        //ref.g
        return true;
    }

    //TODO
    @Override
    protected void meld() {

    }

    //TODO
    @Override
    protected void discard() {

    }
}
