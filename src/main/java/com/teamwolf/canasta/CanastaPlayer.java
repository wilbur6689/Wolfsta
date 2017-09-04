package com.teamwolf.canasta;

import com.teamwolf.enums.Card;

import java.util.ArrayList;

public abstract class CanastaPlayer {

    public void makeMove(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard){
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
    protected abstract void draw(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard);

    /**
     *decide whether or not to make melds
     */
    protected abstract void meld(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard);

    /**
     *decide what to discard
     */
    protected abstract void discard(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard);

}
