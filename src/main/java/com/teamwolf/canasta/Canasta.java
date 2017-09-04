package com.teamwolf.canasta;

import com.teamwolf.beans.Game;
import com.teamwolf.daoInterfaces.CardDAOInterface;
import com.teamwolf.daoInterfaces.GameDAOInterface;
import com.teamwolf.daoInterfaces.PlayerDAOInterface;

import java.util.ArrayList;

/**
 * CORE of CANASTA APP
 */
public class Canasta {

    private Game game;
    private ArrayList<CanastaPlayer> players;

    /**
     * uses dao to create a new game in the database
     * !!First Player Is To Be Added Still!!
     * @param gameName name of game
     * @param gamePassword password to join game
     * @param players number of players
     */
    public Canasta(String gameName, String gamePassword, int players){
        GameDAOInterface gDao = null;//TODO rig up to the implemented dao
        this.game = new Game(gameName, gamePassword, players);
        this.players = new ArrayList<>();
        this.game.setGameId(gDao.newGame());
    }

    /**
     * adds a player to the game
     */
    public void addPlayer(){
        PlayerDAOInterface pDao = null; //TODO rig up to implemented dao
        pDao.addPlayer(this.game.getGameId());
    }

    /**
     * removes a player from the game
     * @param playerid player to be removed
     */
   public void removePlayer(int playerid){
       PlayerDAOInterface pDao = null; //TODO rig up to implemented dao
       pDao.removePlayer(this.game.getGameId(),playerid);
   }

    /**
     * adds cards to Database in deck
     * refreshes Player list to ensure all have been added TODO
     * deals hands to players
     * draws a card(or cards)for discard pile
     */
    public void start(){
        CardDAOInterface cDao = null;//TODO rig up to implemented dao

        cDao.createDeck(this.game.getGameId());

        for( CanastaPlayer cp : players){
            for( int i = 0; i < 11; i++){
                cDao.drawFromStock(cp.getPlayerId());
            }
        }
    }

}
