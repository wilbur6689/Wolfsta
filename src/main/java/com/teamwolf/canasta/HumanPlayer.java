package com.teamwolf.canasta;

import com.teamwolf.enums.Card;

import java.util.ArrayList;

public class HumanPlayer extends CanastaPlayer{

    public HumanPlayer(int playerId, int playerNumber, int user_id, int game_id, int team_team_id, int team_number){
        this.setPlayerId(playerId);
        this.setPlayerNumber(playerNumber);
        this.setScore(0);
        this.setUser_id(user_id);
        this.setGame_id(game_id);
        this.setTeam_team_id(team_team_id);
        this.setTeam_number(team_number);
    }

    @Override
    protected void draw(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard) {
        //Handled elsewhere for humans
    }

    @Override
    protected void meld(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard) {
        //Handled elsewhere for humans
    }

    @Override
    protected void discard(ArrayList<Card> hand, ArrayList<Card> meld, ArrayList<Card> opponentMeld, Card discard) {
        //Handled elsewhere for humans
    }
}
