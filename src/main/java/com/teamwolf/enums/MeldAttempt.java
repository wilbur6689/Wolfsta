package com.teamwolf.enums;

import org.apache.log4j.Logger;

public enum MeldAttempt {
    Success(1),
    INITIALMELDVALUEFAIL(2),//didn't reach initial meld value
    INSUFFICIENTCARDS(3), //need at least 3
    INSUFFICIENTNATURALS(4), //need more naturals than wild
    CANTMELDREDTHREES(5), //can't meld red threes
    CANTGOOUT(6);//can't go out yet

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
