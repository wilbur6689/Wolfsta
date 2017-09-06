package com.teamwolf.canasta;

import com.teamwolf.enums.CardEnum;

import java.util.ArrayList;

public class HumanPlayer extends CanastaPlayer{

    public HumanPlayer(int playerId, int playerNumber, int user_id, int game_id, int team_team_id, int team_number, ArrayList<CardEnum> hand, ArrayList<CardEnum> meld, ArrayList<CardEnum> opponentMeld, CardEnum discard){
        this.setPlayerId(playerId);
        this.setPlayerNumber(playerNumber);
        this.setScore(0);
        this.setUser_id(user_id);
        this.setGame_id(game_id);
        this.setTeam_team_id(team_team_id);
        this.setTeam_number(team_number);
        this.hand = hand;
        this.meld = meld;
        this.opponentMeld = opponentMeld;
        this.discard = discard;
    }

    @Override
    protected void draw() {
        //Handled elsewhere for humans
    }

    @Override
    protected void meld() {
        //Handled elsewhere for humans
    }

    @Override
    protected void discard() {
        //Handled elsewhere for humans
    }
}
