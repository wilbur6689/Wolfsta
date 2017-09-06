package com.teamwolf.canasta;

import com.teamwolf.beans.Game;
import com.teamwolf.daoInterfaces.CardDAOInterface;
import com.teamwolf.daoInterfaces.GameDAOInterface;
import com.teamwolf.daoInterfaces.PlayerDAOInterface;
import com.teamwolf.enums.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * CORE of CANASTA APP
 */
public class Canasta {

    private Game game;
    private ArrayList<CanastaPlayer> players;
    private CardDAOInterface cDao;
    private PlayerDAOInterface pDao;
    private GameDAOInterface gDao;
    protected static Logger log = Logger.getRootLogger();

    /**
     * uses dao to create a new game in the database
     * !!First Player Is To Be Added Still!!
     * @param gameName name of game
     * @param gamePassword password to join game
     * @param players number of players
     */
    public Canasta(String gameName, String gamePassword, int players){
        this.cDao = null;//TODO rig up to implemented dao
        this.pDao = null; //TODO rig up to implemented dao
        this.gDao = null; //TODO rig up to implemented dao
        this.game = new Game(gameName, gamePassword, players);
        this.players = new ArrayList<>();
        this.game.setGameId(gDao.newGame());
    }

    /**
     * constructor that requires existing game object
     * @param g the game
     */
    public Canasta(Game g){
        this.game = g;
        this.cDao = null;//TODO rig up to implemented dao
        this.pDao = null; //TODO rig up to implemented dao
        this.gDao = null; //TODO rig up to implemented dao
    }

    /**
     * adds a player to the game
     */
    public void addPlayer(int gameId){
        pDao.addPlayer(gameId);
    }

    /**
     * removes a player from the game
     * @param playerid player to be removed
     */
   public void removePlayer(int playerid, int gameId){
       pDao.removePlayer(gameId,playerid);
   }

    /**
     * adds cards to Database in deck
     * refreshes Player list to ensure all have been added
     * deals hands to players
     * draws a card(or cards)for discard pile
     */
    public void start(int gameId){
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
        cDao.drawFromStock(gameId, playerId);
    }

    /**
     * checks to see if a meld is valid and if so completes the meld TODO
     * @param required the ammount of points required (applicable for initial meld otherwise 0)
     * @param gameId the game
     * @param playerId who's melding
     * @param cards cards being melded
     * @param wildCards ranks assigned to wild cards
     * @param fromDiscard null if Discard top not used
     */
    public MeldAttempt meld(int required, int gameId, int playerId,  ArrayList<Card> cards,  int[] wildCards, Card fromDiscard){
        this.game = gDao.getGame(gameId);

        //add fromDiscard to cards if not in there already
        if(fromDiscard != null){
            boolean included = false;
            for(Card c : cards){
                if(c.getId() == fromDiscard.getId()){
                    included = true;
                }
            }
            if(!included){
                cards.add(fromDiscard);
            }
        }

        //*******************
        //MELD CRITERIA CHECK
        //*******************

        //get current melds for self and partner
        ArrayList<Card> teamMeld = determineTeamMeld(gameId, playerId);

        int[] rankCount = new int[13];//jokers ignored because they're wild anyway, index 1 will be left blank for 2s

        //determine ranks to be added to
        for(Card c : cards){
            if(c.getRank() != 2 && c.getRank() != 14){//ignore jokers and 2s
                rankCount[c.getRank() -1] += 1;
            }
            //do check for red threes( just in case
            if(c.getId() <= 24 && c.getId() >= 21){
                log.error("1 or more red threes were tried to meld. This should not happen");
                return MeldAttempt.CANTMELDREDTHREES;
            }
        }
        for(int c : wildCards){
            rankCount[c -1] += 1;
    }

        //check that the meld can be successful
        for(int i = 0; i < rankCount.length; i++){

            //check if at least 3 of the rank are being added
            if(rankCount[i] < 3){
                log.trace("Trying to meld less than 3 of a kind. Checking for existing rank in meld");
                //if the rank has already been melded its still ok
                if(!(hasMeld(i+1, teamMeld))){
                    log.debug("insufficient cards to meld");
                    return MeldAttempt.INSUFFICIENTCARDS;
                }
            }

            //check that there are more naturals than wilds
            int diff = determineExistingWildDifference(i + 1, teamMeld);
            diff += rankCount[i];
            for(Card c : cards){
                if(c.getRank() == (i+1)){
                    diff++;
                }
            }
            for(int j = 0; j<wildCards.length; j++){
                if(wildCards[j] == (i+1)){
                    diff--;
                }
            }
            if(diff<=0){
                return MeldAttempt.INSUFFICIENTNATURALS;
            }
        }

        //check if point requirement met
        int points = 0;
        for(Card c : cards){
            points += c.getValue();
        }
        if(points < required){
            return MeldAttempt.INITIALMELDVALUEFAIL;
        }

        //passed tests
        log.trace("Meld passed the tests");

        //**************
        //DOING THE MELD
        //**************

        //NOTE player won't be able to choose which specific wild goes on which meld
        //NOTE this may want to be changed

        // create a stack of wilds to pop off
        Stack<Card> wilds = new Stack<>();
        for(Card c : cards){
            if(c.getRank() == 2 || c.getRank() == 14){
                wilds.add(c);
            }
        }

        //make a meld for each rank
        for(int i = 1; i <= 14; i++){
            ArrayList<Card> meld = new ArrayList<>();
            if(i != 2 && i != 14){
                for (Card c : cards){
                    if(c.getRank() == i){
                        meld.add(c);
                    }
                }
                for (int j = 0; j<wildCards.length; j++){
                    if(wildCards[j] == i){
                        meld.add(wilds.pop());
                    }
                }
            }
            int[] meldArray = new int[meld.size()];
            for (int k = 0; k<meldArray.length; k++){
                meldArray[k] = meld.get(k).getId();
            }
            cDao.addMeld(gameId, playerId, meldArray);
        }
        //All Good
        return MeldAttempt.Success;
    }

    /**
     * Determines the difference between naturals and wilds in the meld of a certain rank
     * @param rank the rank to determine
     * @param teamMeld the existing meld of the partnership
     * @return difference diff between naturals and wilds
     */
    private int determineExistingWildDifference(int rank, ArrayList<Card> teamMeld) {
        int diff = 0;
        HashSet<Card> wildsOfRank = new HashSet<>();

        for( Card c : teamMeld){
            if(c.getRank() == rank){

                diff++;//found another natural of that rank

                //TODO int meld = c.getMeldId();//get meldId that might have wilds
                //TODO this for loop
                for(Card c2 : teamMeld){//include the wilds of that meld
                    if( (c2.getRank() == 2) || (c2.getRank() == 14)){
                        wildsOfRank.add(c2);
                    }
                }
            }
        }

        diff-= wildsOfRank.size();//subtract number of wilds

        return diff;
    }

    /**
     * Determines if a particular rank exists in the meld
     * @param rank the rank to check for
     * @param teamMeld the meld to look in
     * @return true if the rank has been melded
     */
    private boolean hasMeld(int rank, ArrayList<Card> teamMeld) {
        for( Card c : teamMeld){
            if(c.getRank() == rank){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines the meld shared between a player and their partner
     * @param gameId the game
     * @param playerId the current player
     * @return array of card ids
     */
    private ArrayList<Card> determineTeamMeld(int gameId, int playerId) {

        ArrayList<Card> currentMeld = cDao.getCurrentMeld(gameId, playerId);
        int partnerId = getPartner(gameId,playerId);
        ArrayList<Card> currentPartnerMeld = cDao.getCurrentMeld(gameId, partnerId);

        ArrayList<Card> teamMeld = new ArrayList<>();
        teamMeld.addAll(currentMeld);
        teamMeld.addAll(currentPartnerMeld);
        return teamMeld;
    }

    /**
     * determines the playerid of a player's partner
     * @param gameId the game
     * @param playerId the player whose partner we don't know
     * @return the playerId of the partner
     */
    public int getPartner(int gameId, int playerId) {
        int playerNumber = pDao.getPlayerNumber(gameId, playerId);
        int partnerNumber = ((this.game.getPlayers() / 2) + playerNumber) % this.game.getPlayers();
        int partnerId =  pDao.getPlayerId(gameId, partnerNumber);
        return partnerId;
    }

    /**
     * determines total score for the parnership
     * @param gameid the game
     * @param playerid one of the players in the partnership
     * @return the combined total score
     */
    public int getTotalScore(int gameid, int playerid){
        int partnerId = getPartner(gameid, playerid);
        int playerScore = getPlayerScore(gameid,playerid);
        int partnerScore = getPlayerScore(gameid, partnerId);
        return (playerScore + partnerScore);
    }

    /**
     * Calculates the value of a player's current meld //TODO Canastas
     * @param gameid the game
     * @param playerId the player
     * @return the value of the player's meld
     */
    public int getMeldScore(int gameid, int playerId) {
        int score = 0;
        ArrayList<Card> meld = cDao.getCurrentMeld(gameid, playerId);
        for(Card c : meld){
            score += c.getValue();
        }
        return score;
    }

    /**
     * calculates the total value of the current meld between the partners
     * @param gameid the game
     * @param playerid one of the partners
     * @return total meld score
     */
    public int getTotalMeldScore(int gameid, int playerid){
        int playerScore = getMeldScore(gameid, playerid);
        int partnerScore = getMeldScore(gameid, getPartner(gameid,playerid));
        return playerScore+partnerScore;
    }

    /**
     * determines what initial meld value must be surpassed
     * @param gameId
     * @param playerId
     * @return
     */
    public int getInitialMeldValueConstraint(int gameId, int playerId){
        if(getTotalMeldScore(gameId, playerId) > 0){
            return 0; //already melded
        }
        int score = getTotalScore(gameId, playerId);
        if (score < 0){
            return 15;
        }
        else if(score < 1500){
            return 50;
        }
        else if(score < 3000){
            return 90;
        }
        else{
            return 120;
        }
    }

    /**
     * Determines a players total contribution thus far
     * @param gameid the game
     * @param playerid the player
     * @return currentMeld worth + sum of scores from previous rounds
     */
    public int getPlayerScore(int gameid, int playerid) {
        int playerScore = pDao.getScore(gameid, playerid);
        int playerMeldScore = getMeldScore(gameid, playerid);
        return (playerScore + playerMeldScore);
    }
}
