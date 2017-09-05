package com.teamwolf.enums;

import org.apache.log4j.Logger;

public enum CardState {
    DECK(1),
    HAND(2),
    DISCARD(3),
    TOP(4), //the top of the discard pile
    Meld(5);

    private final int cState;
    protected static Logger log = Logger.getRootLogger();

    CardState(int x){
        this.cState = x;
    }

    public int getState(){
        return this.cState;
    }

    public static CardState getCardStatebyCardStateId(int id){
        for(CardState cs : CardState.values()){
            if(cs.cState == id) return cs;
        }
        log.error("There is no cardState for that id");
        return null;
    }
}
