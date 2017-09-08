package com.teamwolf.canasta;

import com.teamwolf.beans.CardLookup;
import com.teamwolf.beans.Player;
import com.teamwolf.dataAccess.TAOClass;

public abstract class CanastaPlayer extends Player{

    public void makeMove(){
        CardLookup discard = null;
        if( draw()){
            meld(discard);
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
     * @return true if a draw occured
     */
    protected abstract boolean draw();

    /**
     *decide whether or not to make melds
     * @param discard the card from the discard pile (null) if not used
     */
    protected abstract void meld(CardLookup discard);

    /**
     *decide what to discard
     */
    protected abstract void discard();

}
