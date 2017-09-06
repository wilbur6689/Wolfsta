package com.teamwolf.canasta;

import com.teamwolf.beans.*;
import com.teamwolf.daoInterfaces.CardDAOInterface;
import com.teamwolf.daoInterfaces.GameDAOInterface;
import com.teamwolf.daoInterfaces.PlayerDAOInterface;
import com.teamwolf.enums.*;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * CORE of CANASTA APP
 */
public class Canasta {

    private CardDAOInterface cDao;
    private PlayerDAOInterface pDao;
    private GameDAOInterface gDao;
    protected static Logger log = Logger.getRootLogger();

    /**
     * constructor
     */
    public Canasta(){
        this.cDao = null;//TODO rig up to implemented dao
        this.pDao = null; //TODO rig up to implemented dao
        this.gDao = null; //TODO rig up to implemented dao
    }

    /**
     * adds a player to the game
     */
    public void addPlayer(Player p){
        pDao.add(p);
    }

    /**
     * removes a player from the game
     * @param p player to be removed
     */
   public void removePlayer(Player p){
       pDao.delete(p);
   }

    /**
     * adds a game to the database
     * not started yet waiting for players
     * @param g the game to be added
     */
   public void addGame(Game g){gDao.add(g);}

    /**
     * adds cards to Database in deck
     * refreshes Player list to ensure all have been added
     * deals hands to players
     * draws a card(or cards)for discard pile
     */
    public void start(Game g){

        //create deck
        for(Card c : Card.values()){
            CardLookup card = new CardLookup();
            card.setCardId(-1);//should be changed by trigger
            card.setCard(c);
            card.setGameId(g.getGameId());
            card.setOwner(null); // no one owns it as it is in deck
            card.setState(CardState.DECK);
            card.setMeldId(-1);// not in a meld
            cDao.add(card);
        }

        //ensure players are in TODO I think we want a game state to denote lobby,in progress and resolved
        //players = new  ArrayList<>();
        //players = pDao.getPlayers(this.game.getGameId(), this.game.getPlayers());

        //deal cards
        for(int i = 1; i <= g.getPlayers(); i++){
            for( int j = 0; j < 11; j++){
                draw(g, i);
            }
        }

        //sets the initial discard pile
        while(initialDiscard(g));
    }

    //public call to draw
    public void draw(Game g){draw(g, g.getTurn());}
    /**
     * draws a card from the deck and puts it in the players hand
     * if red 3 places in meld and recalls draw
     * @param g the game
     * @param playerNumber the player that is drawing (== g.getTurn)
     */
    private CardLookup draw(Game g, int playerNumber) {
        //Get cards in stack
        Map<String, Object> constraints = new HashMap<>();
        constraints.put("game_id", g.getGameId());
        constraints.put("state", CardState.DECK);
        Collection<CardLookup> stack = cDao.getByCompositeMap(constraints);

        if(stack.size()> 0) {
            //draw the card at random
            int selectionIndex = (int) Math.floor(Math.random() * stack.size());
            CardLookup drew = (CardLookup) stack.toArray()[selectionIndex];

            //get the Player
            Map<String, Object> playerConstraints = new HashMap<>();
            constraints.put("game_id", g.getGameId());
            constraints.put("player_number", playerNumber);
            Player p = pDao.getByCompositeKey(playerConstraints);
            drew.setOwner(p);

            //meld if 3 else put in hand
            if (drew.getCard().isRedThree()){
                drew.setState(CardState.Meld);
                cDao.update(drew);
                draw(g, playerNumber);
            }
            else{
                drew.setState(CardState.HAND);
                cDao.update(drew);
            }
            return drew;
        }
        else{
            return null;
        }
    }


    /**
     * draws until initial discard pile is valid
     * @param g the game
     * @return true if card was valid
     */
    private boolean initialDiscard(Game g) {
        //Get cards in stack
        Map<String, Object> constraints = new HashMap<>();
        constraints.put("game_id", g.getGameId());
        constraints.put("state", CardState.DECK);
        Collection<CardLookup> stack = cDao.getByCompositeMap(constraints);

        //draw the card at random
        int selectionIndex = (int) Math.floor(Math.random() * stack.size());
        CardLookup drew = (CardLookup) stack.toArray()[selectionIndex];

        //invalid
        if(drew.getCard().getRank() == 2 || drew.getCard().getRank() == 3 || drew.getCard().getRank() == 14){
            drew.setState(CardState.DISCARD);
            cDao.update(drew);
            return false;
        }
        //valid
        else {
            drew.setState(CardState.TOP);
            cDao.update(drew);
            return true;
        }
    }

    /**
     * checks to see if a meld is valid and if so completes the meld
     * @param required the ammount of points required (applicable for initial meld otherwise 0)
     * @param g the game
     * @param p who's melding
     * @param cards cards being melded (place wilds in here in order of rank they're being assigned to(not super important))
     * @param wildCards ranks assigned to wild cards
     * @param fromDiscard null if Discard top not used
     */
//    public MeldAttempt meld(int required, Game g, Player p,  ArrayList<CardLookup> cards,  int[] wildCards, CardLookup fromDiscard){
//
//        //add fromDiscard to cards if not in there already
//        if(fromDiscard != null){
//            boolean included = false;
//            for(CardLookup c : cards){
//                if(c.getCard().getId() == fromDiscard.getCard().getId()){
//                    included = true;
//                }
//            }
//            if(!included){
//                cards.add(fromDiscard);
//            }
//        }
//
//        MeldAttempt check = meldCriteriaCheck(required, g, p, cards, wildCards, fromDiscard);
//
//        if(check.equals(MeldAttempt.Success)){
//            makeMeld(required, g, p, cards, wildCards, fromDiscard);
//        }
//        //**************
//        //DOING THE MELD
//        //**************
//
//        //NOTE player won't be able to choose which specific wild goes on which meld. Can be determined by ordering.
//
//
//        // create a stack of wilds to pop off
//        Stack<Card> wilds = new Stack<>();
//        for(Card c : cards){
//            if(c.getRank() == 2 || c.getRank() == 14){
//                wilds.add(c);
//            }
//        }
//
//        //make a meld for each rank
//        for(int i = 1; i <= 14; i++){
//            ArrayList<Card> meld = new ArrayList<>();
//            if(i != 2 && i != 14){
//                for (Card c : cards){
//                    if(c.getRank() == i){
//                        meld.add(c);
//                    }
//                }
//                for (int j = 0; j<wildCards.length; j++){
//                    if(wildCards[j] == i){
//                        meld.add(wilds.pop());
//                    }
//                }
//            }
//            int[] meldArray = new int[meld.size()];
//            for (int k = 0; k<meldArray.length; k++){
//                meldArray[k] = meld.get(k).getId();
//            }
//            cDao.addMeld(gameId, playerId, meldArray);
//        }
//        //All Good
//        return MeldAttempt.Success;
//    }
//
//    private void makeMeld(int required, Game g, Player p, ArrayList<CardLookup> cards, int[] wildCards, CardLookup fromDiscard) {
//    }
//
//    /**
//     * checks if criteria of Melds are met
//     * @param required the points required to make the initital meld
//     * @param g the game
//     * @param p the melder
//     * @param cards the cards to be melded
//     * @param wildCards the assignment of wild cards
//     * @param fromDiscard null if not using discard
//     * @return MeldAttempt Status
//     */
//    private MeldAttempt meldCriteriaCheck(int required, Game g, Player p, ArrayList<CardLookup> cards, int[] wildCards, CardLookup fromDiscard) {
//        //get current melds for self and partner
//        ArrayList<Card> teamMeld = determineTeamMeld(gameId, playerId);
//
//        int[] rankCount = new int[13];//jokers ignored because they're wild anyway, index 1 will be left blank for 2s
//
//        //determine ranks to be added to
//        for(Card c : cards){
//            if(c.getRank() != 2 && c.getRank() != 14){//ignore jokers and 2s
//                rankCount[c.getRank() -1] += 1;
//            }
//            //do check for red threes( just in case
//            if(c.getId() <= 24 && c.getId() >= 21){
//                log.error("1 or more red threes were tried to meld. This should not happen");
//                return MeldAttempt.CANTMELDREDTHREES;
//            }
//        }
//        for(int c : wildCards){
//            rankCount[c -1] += 1;
//        }
//
//        //check that the meld can be successful
//        for(int i = 0; i < rankCount.length; i++){
//
//            //check if at least 3 of the rank are being added
//            if(rankCount[i] < 3){
//                log.trace("Trying to meld less than 3 of a kind. Checking for existing rank in meld");
//                //if the rank has already been melded its still ok
//                if(!(hasMeld(i+1, teamMeld))){
//                    log.debug("insufficient cards to meld");
//                    return MeldAttempt.INSUFFICIENTCARDS;
//                }
//            }
//
//            //check that there are more naturals than wilds
//            int diff = determineExistingWildDifference(i + 1, teamMeld);
//            diff += rankCount[i];
//            for(Card c : cards){
//                if(c.getRank() == (i+1)){
//                    diff++;
//                }
//            }
//            for(int j = 0; j<wildCards.length; j++){
//                if(wildCards[j] == (i+1)){
//                    diff--;
//                }
//            }
//            if(diff<=0){
//                return MeldAttempt.INSUFFICIENTNATURALS;
//            }
//        }
//
//        //check if point requirement met
//        int points = 0;
//        for(Card c : cards){
//            points += c.getValue();
//        }
//        if(points < required){
//            return MeldAttempt.INITIALMELDVALUEFAIL;
//        }
//
//        //passed tests
//        log.trace("Meld passed the tests");
//        return MeldAttempt.Success;
//    }
//
//    /**
//     * Determines the difference between naturals and wilds in the meld of a certain rank
//     * @param rank the rank to determine
//     * @param teamMeld the existing meld of the partnership
//     * @return difference diff between naturals and wilds
//     */
//    private int determineExistingWildDifference(int rank, ArrayList<Card> teamMeld) {
//        int diff = 0;
//        HashSet<Card> wildsOfRank = new HashSet<>();
//
//        for( Card c : teamMeld){
//            if(c.getRank() == rank){
//
//                diff++;//found another natural of that rank
//
//                //TODO int meld = c.getMeldId();//get meldId that might have wilds
//                //TODO this for loop
//                for(Card c2 : teamMeld){//include the wilds of that meld
//                    if( (c2.getRank() == 2) || (c2.getRank() == 14)){
//                        wildsOfRank.add(c2);
//                    }
//                }
//            }
//        }
//
//        diff-= wildsOfRank.size();//subtract number of wilds
//
//        return diff;
//    }
//
//    /**
//     * Determines if a particular rank exists in the meld
//     * @param rank the rank to check for
//     * @param teamMeld the meld to look in
//     * @return true if the rank has been melded
//     */
//    private boolean hasMeld(int rank, ArrayList<Card> teamMeld) {
//        for( Card c : teamMeld){
//            if(c.getRank() == rank){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Determines the meld shared between a player and their partner
//     * @param gameId the game
//     * @param playerId the current player
//     * @return array of card ids
//     */
//    private ArrayList<Card> determineTeamMeld(int gameId, int playerId) {
//
//        ArrayList<Card> currentMeld = cDao.getCurrentMeld(gameId, playerId);
//        int partnerId = getPartner(gameId,playerId);
//        ArrayList<Card> currentPartnerMeld = cDao.getCurrentMeld(gameId, partnerId);
//
//        ArrayList<Card> teamMeld = new ArrayList<>();
//        teamMeld.addAll(currentMeld);
//        teamMeld.addAll(currentPartnerMeld);
//        return teamMeld;
//    }
//
//    /**
//     * determines the playerid of a player's partner
//     * @param gameId the game
//     * @param playerId the player whose partner we don't know
//     * @return the playerId of the partner
//     */
//    public int getPartner(int gameId, int playerId) {
//        int playerNumber = pDao.getPlayerNumber(gameId, playerId);
//        int partnerNumber = ((this.game.getPlayers() / 2) + playerNumber) % this.game.getPlayers();
//        int partnerId =  pDao.getPlayerId(gameId, partnerNumber);
//        return partnerId;
//    }
//
//    /**
//     * determines total score for the parnership
//     * @param gameid the game
//     * @param playerid one of the players in the partnership
//     * @return the combined total score
//     */
//    public int getTotalScore(int gameid, int playerid){
//        int partnerId = getPartner(gameid, playerid);
//        int playerScore = getPlayerScore(gameid,playerid);
//        int partnerScore = getPlayerScore(gameid, partnerId);
//        return (playerScore + partnerScore);
//    }
//
//    /**
//     * Calculates the value of a player's current meld //TODO Canastas
//     * @param gameid the game
//     * @param playerId the player
//     * @return the value of the player's meld
//     */
//    public int getMeldScore(int gameid, int playerId) {
//        int score = 0;
//        ArrayList<Card> meld = cDao.getCurrentMeld(gameid, playerId);
//        for(Card c : meld){
//            score += c.getValue();
//        }
//        return score;
//    }
//
//    /**
//     * calculates the total value of the current meld between the partners
//     * @param gameid the game
//     * @param playerid one of the partners
//     * @return total meld score
//     */
//    public int getTotalMeldScore(int gameid, int playerid){
//        int playerScore = getMeldScore(gameid, playerid);
//        int partnerScore = getMeldScore(gameid, getPartner(gameid,playerid));
//        return playerScore+partnerScore;
//    }
//
//    /**
//     * determines what initial meld value must be surpassed
//     * @param gameId
//     * @param playerId
//     * @return
//     */
//    public int getInitialMeldValueConstraint(int gameId, int playerId){
//        if(getTotalMeldScore(gameId, playerId) > 0){
//            return 0; //already melded
//        }
//        int score = getTotalScore(gameId, playerId);
//        if (score < 0){
//            return 15;
//        }
//        else if(score < 1500){
//            return 50;
//        }
//        else if(score < 3000){
//            return 90;
//        }
//        else{
//            return 120;
//        }
//    }
//
//    /**
//     * Determines a players total contribution thus far
//     * @param gameid the game
//     * @param playerid the player
//     * @return currentMeld worth + sum of scores from previous rounds
//     */
//    public int getPlayerScore(int gameid, int playerid) {
//        int playerScore = pDao.getScore(gameid, playerid);
//        int playerMeldScore = getMeldScore(gameid, playerid);
//        return (playerScore + playerMeldScore);
//    }
}
