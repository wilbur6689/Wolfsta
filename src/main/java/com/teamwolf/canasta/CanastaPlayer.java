package com.teamwolf.canasta;

import com.teamwolf.beans.Player;

public abstract class CanastaPlayer extends Player{

    public void makeMove(){
        if( draw()){
            meld();
            discard();
        }
        else{
            Canasta ref = new Canasta();
            ref.endRound(ref.getGame(this.getGame_id()));
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
     * returns true if a draw occured
     */
    protected abstract boolean draw();

    /**
     *decide whether or not to make melds
     */
    protected abstract void meld();

    /**
     *decide what to discard
     */
    protected abstract void discard();

}
