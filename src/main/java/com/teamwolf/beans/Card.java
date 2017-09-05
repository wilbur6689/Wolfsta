package com.teamwolf.beans;

import com.teamwolf.enums.CardEnum;
import com.teamwolf.enums.CardState;

public class Card {
    private int cardId;
    private int cardNumber;
    private CardEnum cardEnum;
    private int gameId;
    private int state;
    private CardState cardState;
    private int meldId;

    public Card() {
    }

    public Card(int cardId, int cardNumber, int gameId, int state, int meldId) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.cardEnum = CardEnum.getCardbyCardId(cardNumber);
        this.gameId = gameId;
        this.state = state;
        this.cardState = CardState.getCardStatebyCardStateId(state);
        this.meldId = meldId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardEnum getCardEnum() {
        return cardEnum;
    }

    public void setCardEnum(CardEnum cardEnum) {
        this.cardEnum = cardEnum;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    public int getMeldId() {
        return meldId;
    }

    public void setMeldId(int meldId) {
        this.meldId = meldId;
    }
}
