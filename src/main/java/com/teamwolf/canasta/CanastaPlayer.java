package com.teamwolf.canasta;

import com.teamwolf.beans.Player;
import com.teamwolf.enums.CardEnum;

import java.util.ArrayList;

public abstract class CanastaPlayer extends Player{


    public void makeMove(ArrayList<CardEnum> hand, ArrayList<CardEnum> meld, ArrayList<CardEnum> opponentMeld, CardEnum discard){
        if(isStock()){
            draw(hand, meld, opponentMeld, discard);
            meld( hand,  meld,  opponentMeld, discard);
            discard( hand, meld,  opponentMeld, discard);
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
    protected abstract void draw(ArrayList<CardEnum> hand, ArrayList<CardEnum> meld, ArrayList<CardEnum> opponentMeld, CardEnum discard);

    /**
     *decide whether or not to make melds
     */
    protected abstract void meld(ArrayList<CardEnum> hand, ArrayList<CardEnum> meld, ArrayList<CardEnum> opponentMeld, CardEnum discard);

    /**
     *decide what to discard
     */
    protected abstract void discard(ArrayList<CardEnum> hand, ArrayList<CardEnum> meld, ArrayList<CardEnum> opponentMeld, CardEnum discard);

}
