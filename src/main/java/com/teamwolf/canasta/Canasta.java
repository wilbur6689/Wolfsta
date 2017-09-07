package com.teamwolf.canasta;

import com.teamwolf.beans.*;
import com.teamwolf.daoInterfaces.CardDAOInterface;
import com.teamwolf.daoInterfaces.GameDAOInterface;
import com.teamwolf.daoInterfaces.PlayerDAOInterface;
import com.teamwolf.dataAccess.TAO;
import com.teamwolf.dataAccess.TAOClass;
import com.teamwolf.enums.*;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * CORE of CANASTA APP
 */
public class Canasta {

    private TAO<CardLookup> cDao;
    private TAO<Player> pDao;
    private TAO<Game> gDao;
    protected static Logger log = Logger.getRootLogger();

    /**
     * constructor
     */
    public Canasta() {

        try {
            this.cDao = new TAOClass<CardLookup>(CardLookup.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            this.pDao = new TAOClass<Player>(Player.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            this.gDao = new TAOClass<Game>(Game.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a player to the game
     */
    public void addPlayer(Player p) {
        pDao.add(p);
    }

    /**
     * removes a player from the game
     *
     * @param p player to be removed
     */
    public void removePlayer(Player p) {
        pDao.delete(p);
    }

    /**
     * adds a game to the database
     * not started yet waiting for players
     *
     * @param g the game to be added
     */
    public void addGame(Game g) {
        gDao.add(g);
    }

    /**
     * adds cards to Database in deck
     * refreshes Player list to ensure all have been added
     * deals hands to players
     * draws a card(or cards)for discard pile
     */
    public void start(Game g) {

        //create deck
        for (Card c : Card.values()) {
            CardLookup card = new CardLookup();
            card.setCardId(-1);//should be changed by trigger
            card.setCard(c);
            card.setGameId(g.getGameId());
            card.setOwner(null); // no one owns it as it is in deck
            card.setState(CardState.DECK);
            card.setMeldId(-1);// not in a meld
            cDao.add(card);
        }

        g.setGameState(2);
        g = gDao.update(g);

        //deal cards
        for (int i = 1; i <= g.getPlayers(); i++) {
            for (int j = 0; j < 11; j++) {
                draw(g, i);
            }
        }

        //sets the initial discard pile
        while (initialDiscard(g)) ;
    }

    //public call to draw
    public CardLookup draw(Game g) {
        return draw(g, g.getTurn());
    }

    /**
     * draws a card from the deck and puts it in the players hand
     * if red 3 places in meld and recalls draw
     *
     * @param g            the game
     * @param playerNumber the player that is drawing (== g.getTurn)
     */
    public CardLookup draw(Game g, int playerNumber) {
        //Get cards in stack
        Map<String, Object> constraints = new HashMap<>();
        constraints.put("game_id", g.getGameId());
        constraints.put("state", CardState.DECK);
        Collection<CardLookup> stack = cDao.getByCompositeMap(constraints);

        if (stack.size() > 0) {
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
            if (drew.getCard().isRedThree()) {
                drew.setState(CardState.Meld);
                cDao.update(drew);
                draw(g, playerNumber);
            } else {
                drew.setState(CardState.HAND);
                cDao.update(drew);
            }
            return drew;
        } else {
            return null;
        }
    }


    /**
     * draws until initial discard pile is valid
     *
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
        if (drew.getCard().getRank() == 2 || drew.getCard().getRank() == 3 || drew.getCard().getRank() == 14) {
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
     *
     * @param required    the ammount of points required (applicable for initial meld otherwise 0)
     * @param g           the game
     * @param p           who's melding
     * @param cards       cards being melded (place wilds in here in order of rank they're being assigned to(not super important))
     * @param wildCards   ranks assigned to wild cards
     * @param fromDiscard null if Discard top not used
     */
    public MeldAttempt meld(int required, Game g, Player p, ArrayList<CardLookup> cards, int[] wildCards, CardLookup fromDiscard) {

        //add fromDiscard to cards if not in there already
        if (fromDiscard != null) {
            boolean included = false;
            for (CardLookup c : cards) {
                if (c.getCard().getId() == fromDiscard.getCard().getId()) {
                    included = true;
                }
            }
            if (!included) {
                cards.add(fromDiscard);
            }
        }

        MeldAttempt check = meldCriteriaCheck(required, g, p, cards, wildCards, fromDiscard);

        if (check.equals(MeldAttempt.Success)) {
            makeMeld( g, p, cards, wildCards);
        }
        return check;
    }

    /**
     * makes a meld
     * check should/will have already been preformed
     * @param g           the game
     * @param p           the melder
     * @param cards       the cards to be melded
     * @param wildCards   the assignment of wild cards
     */
    private void makeMeld(Game g, Player p, ArrayList<CardLookup> cards, int[] wildCards) {
        //NOTE In order to ensure correct wild assignment wilds in cards must be in opposite order of rank they are to be assigned to
        //NOTE may want to be changed

        // create a stack of wilds to pop off
        Stack<CardLookup> wilds = new Stack<>();
        for (CardLookup c : cards) {
            if (c.getCard().getRank() == 2 || c.getCard().getRank() == 14) {
                wilds.add(c);
            }
        }

        //make a meld for each rank
        for (int i = 1; i <= 14; i++) {
            ArrayList<CardLookup> meld = new ArrayList<>();
            int meldID = 0;
            if (hasMeld(i, getTeamMeld(g, p))) {//use existing meldId
                for (CardLookup c : getTeamMeld(g, p)) {
                    if (c.getCard().getRank() == i && !c.isWild()) {
                        meldID = c.getMeldId();
                        break;//don't need to continue
                    }
                }
            } else {
                meldID = getNextMeldId(g);
            }

            if (i != 2 && i != 14) {//don't make melds of wilds
                for (CardLookup c : cards) {
                    if (c.getCard().getRank() == i) {
                        meld.add(c);
                    }
                }
                for (int j = 0; j < wildCards.length; j++) {
                    if (wildCards[j] == i) {
                        CardLookup wild = wilds.pop();
                        for (CardLookup c : getTeamMeld(g, p)) {
                            if (c.getCard().getRank() == j && !c.isWild()) {
                                meldID = c.getMeldId();
                                break;//don't need to continue
                            }
                        }
                        wild.setMeldId(meldID);
                        wild.setOwner(p);
                        wild.setState(CardState.Meld);
                        cDao.update(wild);
                    }
                }
            }

            for (int k = 0; k < meld.size(); k++) {
                CardLookup card = meld.get(k);
                card.setMeldId(meldID);
                card.setOwner(p);
                card.setState(CardState.Meld);
                cDao.update(card);
            }
        }
    }

    /**
     * gets the next meld id
     * FIXME not the best implementation (see note)
     * @param g the game
     * @return one more than the current highest meld Id
     */
    private int getNextMeldId(Game g) {
        //NOTE this implementation has to fetch all the cards in all the games
        Map<String, Object> constraint = new HashMap<>();
        constraint.put("game_id", g.getGameId());
        Collection<CardLookup> allCards = cDao.getByCompositeMap(constraint);
        int largest = 0;
        for (CardLookup c : allCards) {
            if (c.getMeldId() > largest) {
                largest = c.getMeldId();
            }
        }
        return largest + 1;
    }

    /**
     * checks if criteria of Meld is met
     * @param required    the points required to make the initital meld
     * @param g           the game
     * @param p           the melder
     * @param cards       the cards to be melded
     * @param wildCards   the assignment of wild cards
     * @param fromDiscard null if not using discard
     * @return MeldAttempt Status
     */
    public MeldAttempt meldCriteriaCheck(int required, Game g, Player p, ArrayList<CardLookup> cards, int[] wildCards, CardLookup fromDiscard) {
        //get current melds for self and partner
        ArrayList<CardLookup> teamMeld = getTeamMeld(g, p);

        //add fromDiscard to cards if not in there already
        if (fromDiscard != null) {
            boolean included = false;
            for (CardLookup c : cards) {
                if (c.getCard().getId() == fromDiscard.getCard().getId()) {
                    included = true;
                }
            }
            if (!included) {
                cards.add(fromDiscard);
            }
        }

        //check if they are trying to go out
        int handSize = getHandSize(g, p);
        if (fromDiscard != null) {
            handSize--; //1 card came from the discard
        }
        //if trying to go out
        if (!(handSize - 1 >= cards.size())) {
            int[] canastaCounts = getCanastaCount(teamMeld);
            int natural = canastaCounts[0];
            int unnatural = canastaCounts[1];
            if (natural < 1 && unnatural < 1) {
                return MeldAttempt.CANTGOOUT;
            }
        }

        int[] rankCount = new int[13];//jokers ignored because they're wild anyway, index 1 will be left blank for 2s

        //determine ranks to be added to
        for (CardLookup c : cards) {
            if (c.getCard().getRank() != 2 && c.getCard().getRank() != 14) {//ignore jokers and 2s
                rankCount[c.getCard().getRank() - 1] += 1;
            }
            //do check for red threes (just in case)
            if (c.getCard().getId() <= 24 && c.getCard().getId() >= 21) {
                log.error("There was an attempt to meld 1 or more red threes. This should have never happened");
                return MeldAttempt.CANTMELDREDTHREES;
            }
        }
        for (int c : wildCards) {
            rankCount[c - 1] += 1;
        }

        //check that the meld can be successful
        for (int i = 0; i < rankCount.length; i++) {

            //check if at least 3 of the rank are being added
            if (rankCount[i] < 3) {
                log.trace("Trying to meld less than 3 of a kind. Checking for existing rank in meld");
                //if the rank has already been melded its still ok
                if (!(hasMeld(i + 1, teamMeld))) {
                    log.debug("insufficient cards to meld");
                    return MeldAttempt.INSUFFICIENTCARDS;
                }
            }

            //check that there are more naturals than wilds
            int diff = determineExistingWildDifference(i + 1, teamMeld);
            diff += rankCount[i];
            for (int wildCard : wildCards) {
                if (wildCard == (i + 1)) {
                    diff--;
                }
            }
            if (diff <= 0) {
                return MeldAttempt.INSUFFICIENTNATURALS;
            }
        }

        //check if point requirement met
        int points = 0;
        for (CardLookup c : cards) {
            points += c.getCard().getValue();
        }
        if (points < required) {
            return MeldAttempt.INITIALMELDVALUEFAIL;
        }

        //passed tests
        log.trace("Meld passed the tests");
        return MeldAttempt.Success;
    }

    /**
     * gets the number of canastas in a teams meld
     * {natural count, unnatural count}
     * @param teamMeld a meld (for a team)
     * @return canastaCounts[0] = natural s, canastaCounts[1] = unnaturals
     */
    public int[] getCanastaCount(ArrayList<CardLookup> teamMeld) {
        int[] canastaCounts = new int[2];
        canastaCounts[0] = 0;
        canastaCounts[1] = 0;
        int[] rankCount = new int[13]; //-value will reflect unnatural
        ArrayList<CardLookup> wilds = new ArrayList<>();
        //count naturals
        for (CardLookup c : teamMeld) {
            if (c.isWild()) {//handle wilds later
                wilds.add(c);
            } else {
                rankCount[c.getCard().getRank() - 1]++;
            }
        }
        //count wilds in melds
        for (CardLookup w : wilds) {
            for (CardLookup c : teamMeld) {
                if (c.getMeldId().intValue() == w.getMeldId().intValue()) {//found which meld the wild belongs to
                    if (rankCount[c.getCard().getRank() - 1] > 0) {//need to mark negative
                        rankCount[c.getCard().getRank() - 1] = rankCount[c.getCard().getRank() - 1] * -1;
                    }
                    rankCount[c.getCard().getRank() - 1]--;
                }
            }
        }
        for (int aRankCount : rankCount) {
            if (aRankCount <= -7) {
                canastaCounts[1]++;
            } else if (aRankCount >= 7) {
                canastaCounts[0]++;
            }
        }
        return canastaCounts;
    }

    /**
     * gets a count of the cards in a players hand
     *
     * @param p the player
     * @param g the game
     * @return cards in hand
     */
    public int getHandSize(Game g, Player p) {
        Collection<CardLookup> hand = getHand(g, p);
        return hand.size();
    }

    /**
     * gets a player's hand
     *
     * @param g the game
     * @param p the player
     * @return player's hand
     */
    public Collection<CardLookup> getHand(Game g, Player p) {
        Map<String, Object> constraints = new HashMap<>();
        constraints.put("game_id", g.getGameId());
        constraints.put("player_id", p.getPlayerId());
        constraints.put("state", CardState.HAND);
        return cDao.getByCompositeMap(constraints);
    }

    public Collection<CardLookup> getHand(Player p) {
        return getHand(getGame(p), p);
    }

    /**
     * gets the game the player is in
     * @param p the player
     * @return the game
     */
    public Game getGame(Player p) {
        return gDao.getById(p.getGame_id());
    }

    /**
     * gets game associated with an id
     * @param game_id game id
     * @return the game
     */
    public Game getGame(int game_id) {
        return gDao.getById(game_id);
    }

    /**
     * Determines the difference between naturals and wilds in the meld of a certain rank
     *
     * @param rank     the rank to determine
     * @param teamMeld the existing meld of the partnership
     * @return difference diff between naturals and wilds
     */
    private int determineExistingWildDifference(int rank, ArrayList<CardLookup> teamMeld) {
        int diff = 0;
        ArrayList<CardLookup> wilds = new ArrayList<>();
        for (CardLookup c : teamMeld) {
            //find naturals of that rank
            if (c.getCard().getRank() == rank) {
                diff++;
            }
            //populate wilds while we're at it
            if (c.isWild()) {
                wilds.add(c);
            }
        }
        for (CardLookup w : wilds) {
            for (CardLookup c : teamMeld) {
                if (c.getCard().getRank() == rank && c.getMeldId() == w.getMeldId()) {//found a wild pertaining to rank
                    diff--;
                }
            }
        }

        return diff;
    }

    /**
     * Determines if a particular rank exists in the meld
     *
     * @param rank     the rank to check for
     * @param teamMeld the meld to look in
     * @return true if the rank has been melded
     */
    private boolean hasMeld(int rank, ArrayList<CardLookup> teamMeld) {
        for (CardLookup c : teamMeld) {
            if (c.getCard().getRank() == rank) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines the meld shared between a player and their partner
     *
     * @param g game
     * @param p the current player
     * @return array of card ids
     */
    public ArrayList<CardLookup> getTeamMeld(Game g, Player p) {
        Map<String, Object> constraint = new HashMap<>();
        constraint.put("game_id", g.getGameId());
        constraint.put("player_id", p.getPlayerId());
        constraint.put("state", CardState.Meld);
        Collection<CardLookup> currentMeld = cDao.getByCompositeMap(constraint);

        Player partner = getPartner(g, p);
        Map<String, Object> constraint2 = new HashMap<>();
        constraint.put("game_id", g.getGameId());
        constraint.put("player_id", partner.getPlayerId());
        constraint.put("state", CardState.Meld);
        Collection<CardLookup> currentPartnerMeld = cDao.getByCompositeMap(constraint2);

        ArrayList<CardLookup> teamMeld = new ArrayList<>();
        teamMeld.addAll(currentMeld);
        teamMeld.addAll(currentPartnerMeld);
        return teamMeld;
    }

    /**
     * determines the partner of a player
     *
     * @param g the game
     * @param p the player whose partner we don't know
     * @return the partner
     */
    public Player getPartner(Game g, Player p) {
        int playerNumber = p.getPlayerNumber();
        int partnerNumber = ((g.getPlayers() / 2) + playerNumber) % g.getPlayers();
        Map<String, Object> constraint = new HashMap<>();
        constraint.put("game_id", g.getGameId());
        constraint.put("player_number", partnerNumber);
        Player partner = pDao.getByCompositeKey(constraint);
        return partner;
    }

    /**
     * determines total score for the parnership
     *
     * @param g the game
     * @param p one of the players in the partnership
     * @return the combined total score
     */
    public int getTotalScore(Game g, Player p) {
        int playerScore = p.getScore();
        int meld = getTeamMeldScore(g, p);
        return (playerScore + meld);
    }

    /**
     * Calculates the value of a team's meld
     *
     * @param g the game
     * @param p the player
     * @return the value of the player's meld
     */
    public int getTeamMeldScore(Game g, Player p) {
        int score = 0;
        ArrayList<CardLookup> meld = getTeamMeld(g, p);
        for (CardLookup c : meld) {
            score += c.getCard().getValue();
        }
        int[] canastaCount = getCanastaCount(meld);
        score += 500 * canastaCount[0];
        score += 300 * canastaCount[1];
        if (hasRed3sBonus(meld)) {
            score += 400;
        }

        return score;
    }

    /**
     * determines if a meld has the all 4 red3s bonus
     *
     * @param meld the meld
     * @return true if the meld contains 4 red3s
     */
    public boolean hasRed3sBonus(ArrayList<CardLookup> meld) {
        int red3Count = 0;
        for (CardLookup c : meld) {
            if (c.getCard().isRedThree()) {
                red3Count++;
            }
        }
        if (red3Count == 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * determines if the discard pile is frozen
     *
     * @param g
     * @return
     */
    public boolean isDiscardFrozen(Game g) {
        Map<String, Object> constraint = new HashMap<>();
        constraint.put("game_id", g.getGameId());
        constraint.put("state", CardState.TOP);
        CardLookup c = cDao.getByCompositeKey(constraint);
        if (c.isWild() || c.getCard().getRank() == 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * determines what initial meld value must be surpassed
     *
     * @param g the game
     * @param p one of the partners
     * @return initial meld point constraint
     */
    public int getInitialMeldValueConstraint(Game g, Player p) {
        if (getTeamMeldScore(g, p) > 0) {
            return 0; //already melded
        }
        int score = p.getScore();
        if (score < 0) {
            return 15;
        } else if (score < 1500) {
            return 50;
        } else if (score < 3000) {
            return 90;
        } else {
            return 120;
        }
    }

    /**TODO
     * ends the current round and checks for win
     * @param g the game
     */
    public void endRound(Game g) {
        g.setRound(g.getRound() + 1);
        newRound(g);
    }

    /**sets up a new round
     *
     * @param g
     */
    private void newRound(Game g) {
    }

}
