package com.teamwolf.canasta;

import com.teamwolf.beans.CardLookup;
import com.teamwolf.beans.Game;
import com.teamwolf.beans.Player;
import com.teamwolf.enums.Card;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class SimpleAi extends CanastaPlayer{

    @Override
    @Autowired
    protected boolean draw() {
        Canasta ref = new Canasta();
        Game g = ref.getGame(this);

        ArrayList<CardLookup> hand = new ArrayList<>();
        hand.addAll(ref.getHand(this));
        CardLookup top = ref.getTop(g);

        //decision might be made for you
        if (ref.isDiscardFrozen(g)) {//welp you're not drawing from there
            if(ref.getStackSize(g) <= 0){//There's a problem if its less than 0 but its not the AI's problem
                return false;//can't draw so round ends
            }
            else{
                if(ref.draw(g, this) == null){
                    return false; //drew but it was only one or more red 3s and then the stack was exhausted
                }
                else {
                    return true;//draw was executed in the if statement
                }
            }
        }
        //will take discard if possible
        else if(ref.getStackSize(g) <= 0) {
            if(takeDiscardPossible(ref, g, this, hand, top)){
                //NOTE LEFT OFF HERE
            }
        }
        //Strategy : take discard if possible and:
        //        it would allow a canasta to be made or one away
        //   or   there are more than 3 cards in it (increases hand size)
        //   or   there is more than one and a new meld would be formed (allows partner to play on it
        else{
            //if
        }
        return true;
    }

    /**
     * determines if a meld using the discard is posiible
     * @param ref Canasta middle
     * @param g the game
     * @param p this AI or player
     * @param hand Ai's hand
     * @param top the top card of the discard pile
     * @return true if it can be taken
     */
    private boolean takeDiscardPossible(Canasta ref, Game g, Player p, ArrayList<CardLookup> hand, CardLookup top) {
        ArrayList<CardLookup> teamMeld = ref.getTeamMeld(g, p);

        //do you have the meld already?
        if (ref.hasMeld(top.getCard().getRank(), teamMeld)) {
            return true;
        }
        //can you make that meld? //Todo going out check
        else {
            int wilds = 0;
            int natural = 0;
            for (CardLookup c : hand) {
                if (c.getCard().isWild()) {
                    wilds++;
                }
                else if(c.getCard().getRank() == top.getCard().getRank()){
                    natural++;
                }
            }
            if(natural >= 2 || (wilds > 0 && natural > 0)){
                return true;
            }
            else {
                return false;
            }
        }
    }

    //TODO
    @Override
    protected void meld(CardLookup discard) {

    }

    //TODO
    @Override
    protected void discard() {

    }

    private void killAllHumans(){
        //intentionally left unimplemented
    }
}