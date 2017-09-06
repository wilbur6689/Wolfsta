package com.teamwolf.canasta;

import com.teamwolf.beans.Player;
import com.teamwolf.enums.CardEnum;

import java.util.ArrayList;

public abstract class CanastaPlayer extends Player{
    ArrayList<CardEnum> hand;
    ArrayList<CardEnum> meld;
    ArrayList<CardEnum> opponentMeld;
    CardEnum discard;

    public void makeMove(){
        if(isStock()){
            draw();
            meld();
            discard();
        }
        else{
            //TODO
        }
    }

    /**
     * checks for case when stock has run out
     * @return true if there are cards in stock
     */
    protected boolean isStock(){
        //TODO
        return true;
    }

    /**
     * decide whether to draw from discard
     */
    protected abstract void draw();

    /**
     *decide whether or not to make melds
     */
    protected abstract void meld();

    /**
     *decide what to discard
     */
    protected abstract void discard();

}
