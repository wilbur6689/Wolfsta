package com.teamwolf.canasta;

import com.teamwolf.beans.Game;
import com.teamwolf.daoInterfaces.CardDAOInterface;
import com.teamwolf.daoInterfaces.GameDAOInterface;
import com.teamwolf.daoInterfaces.PlayerDAOInterface;
import com.teamwolf.enums.Card;
import com.teamwolf.enums.MeldAttempt;

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
    public void addPlayer(int gameId){
        PlayerDAOInterface pDao = null; //TODO rig up to implemented dao
        pDao.addPlayer(gameId);
    }

    /**
     * removes a player from the game
     * @param playerid player to be removed
     */
   public void removePlayer(int playerid, int gameId){
       PlayerDAOInterface pDao = null; //TODO rig up to implemented dao
       pDao.removePlayer(gameId,playerid);
   }

    /**
     * adds cards to Database in deck
     * refreshes Player list to ensure all have been added
     * deals hands to players
     * draws a card(or cards)for discard pile
     */
    public void start(int gameId){
        CardDAOInterface cDao = null;//TODO rig up to implemented dao
        PlayerDAOInterface pDao = null; //TODO rig up to implemented dao
        GameDAOInterface gDao = null; //TODO rig up to implemented dao
        this.game = gDao.getGame(gameId);

        //crete deck
        cDao.createDeck(this.game.getGameId());

        //ensure players are in
        players = new  ArrayList<>();
        players = pDao.getPlayers(this.game.getGameId(), this.game.getPlayers());

        //deal cards
        for( CanastaPlayer cp : players){
            for( int i = 0; i < 11; i++){
                cDao.drawFromStock(this.game.getGameId(), cp.getPlayerId());
            }
        }

        //sets the initial discard pile
        while(initialDiscard(cDao));
    }



    /**
     * draws until initial discard pile is valid
     * @param cDao Card Database Access Object
     * @return true if card was valid
     */
    private boolean initialDiscard(CardDAOInterface cDao) {
        Card c;
        //2s, 3s, Jokers are invalid
        c = Card.getCardbyCardId(cDao.drawToTop(this.game.getGameId()));
        if (c.getRank() == 14 || c.getRank() <= 3){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * draws card from stock and places it in a player's hand
     * @param gameId the game
     * @param playerId the player
     */
    public void drawFromStock(int gameId, int playerId){
        CardDAOInterface cDao = null;//TODO rig up to implemented dao
        cDao.drawFromStock(gameId, playerId);
    }

    /**
     * checks to see if a meld is valid and if so completes the meld
     * @param gameId the game
     * @param playerId who's melding
     * @param cards cards being melded
     * @param wildCards ranks assigned to wild cards
     * @param fromDiscard null if Discard top not used
     */
    public MeldAttempt meld(int gameId, int playerId, int[] cards, int[] wildCards, Card fromDiscard){
        CardDAOInterface cDao = null;//TODO rig up to implemented dao
        PlayerDAOInterface pDao = null; //TODO rig up to implemented dao
        GameDAOInterface gDao = null; //TODO rig up to implemented dao
        this.game = gDao.getGame(gameId);

        //get current melds for self and partner
        int[] currentMeld = cDao.getCurrentMeld(gameId, playerId);
        int playerNumber = pDao.getPlayerNumber(gameId, playerId);
        int partnerNumber = ((this.game.getPlayers() / 2) + playerNumber) % this.game.getPlayers();
        int partnerId =  pDao.getPlayerId(gameId, partnerNumber);
        int[] currentPartnerMeld = cDao.getCurrentMeld(gameId, partnerId);
        return null;
    }
}
