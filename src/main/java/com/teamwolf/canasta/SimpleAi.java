package com.teamwolf.canasta;

import com.teamwolf.beans.CardLookup;
import com.teamwolf.enums.Card;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class SimpleAi extends CanastaPlayer{

    //TODO
    @Override
    @Autowired
    protected boolean draw() {
        ArrayList<CardLookup> hand = new ArrayList<>();
        Canasta ref = new Canasta();
        hand.addAll(ref.getHand(this));
        //TODO
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
