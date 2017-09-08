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

        if (ref.isDiscardFrozen(g)) {//welp you're not drawing from there
            if(ref.getStackSize(g) <= 0){//There's a problem if its less than 0 but its not the AI's problem
                return false;//can't draw so round ends
            }
            else{
                if(ref.draw(g, this) == null){
                    return false; //drew but it was a red 3(s)  and then the stack was exhausted
                }
                else {
                    return true;//draw was executed in the if statement
                }
            }
        }
        else{
            ArrayList<CardLookup> hand = new ArrayList<>();
            hand.addAll(ref.getHand(this));
        }
        return true;
    }

    //TODO
    @Override
    protected void meld() {

    }

    //TODO
    @Override
    protected void discard() {

    }

    private void killAllHumans(){

    }
}
