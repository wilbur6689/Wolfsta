package com.teamwolf.enums;

import org.apache.log4j.Logger;

public enum MeldAttempt {
    Success(1),
    HAND(2),
    DISCARD(3),
    TOP(4), //the top of the discard pile
    Meld(5);

    private final int mAttempt;
    protected static Logger log = Logger.getRootLogger();

    MeldAttempt(int x){
        this.mAttempt = x;
    }

    public int getState(){
        return this.mAttempt;
    }

    public static MeldAttempt getMeldAttemptbyMeldAttemptId(int id){
        for(MeldAttempt  ma : MeldAttempt .values()){
            if(ma.mAttempt == id) return ma;
        }
        log.error("There is no meldAttempt for that id");
        return null;
    }
}
