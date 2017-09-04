package com.teamwolf.enums;

public enum CardState {
    DECK(1),
    HAND(2),
    DISCARD(3),
    TOP(4), //the top of the discard pile
    Meld(5);

    private final int cState;

    CardState(int x){
        this.cState = x;
    }

    public int getState(){
        return this.cState;
    }
}
