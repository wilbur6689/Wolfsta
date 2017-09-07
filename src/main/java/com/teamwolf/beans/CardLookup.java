package com.teamwolf.beans;


import com.teamwolf.dataAccess.*;
import com.teamwolf.enums.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name="CARD_LOOKUP")
public class CardLookup implements DataObject
{
    private Integer cardId;
    private Card    card;
    private Integer gameId;
    private Player  owner;
    private CardState state;
    private Integer meldId;

    @Id
    @Column(name="CARD_ID")
    public Integer getCardId()
    {
        return cardId;
    }
    public void setCardId(Integer cardId)
    {
        this.cardId = cardId;
    }

    @Column(name="CARD_NUMBER")
    public Card getCard()
    {
        return card;
    }
    public void setCard(Card card)
    {
        this.card = card;
    }

    @Column(name="GAME_ID")
    public Integer getGameId()
    {
        return gameId;
    }
    public void setGameId(Integer gameId)
    {
        this.gameId = gameId;
    }

    public Player getOwner()
    {
        return owner;
    }
    public void setOwner(Player owner)
    {
        this.owner = owner;
    }

    public CardState getState()
    {
        return state;
    }
    public void setState(CardState state)
    {
        this.state = state;
    }

    @Column(name="MELD_ID")
    public Integer getMeldId()
    {
        return meldId;
    }
    public void setMeldId(Integer meldId)
    {
        this.meldId = meldId;
    }

    @Transient
    @Override
    public Serializable getID()
    {
        return this.getCardId();
    }

    public boolean isWild() {
        if (this.getCard().getRank() == 2 || this.getCard().getRank() == 14){
            return true;
        }
        else{
            return false;
        }
    }
}
