package com.example.uno;

import android.widget.Toast;

/**
 * Player is an abstract base class which the subclasses 'User'
 * and 'Computer' inherit from
 * */
public class Player {
    private Hand hand;

    public Player(Deck deck) {
        hand = new Hand(deck);
    }

    public void playCard(int i) {
        hand.playCard(i);
    }

    public void pickUpCard(Deck deck) {
        if (deck.deckIsEmpty()) {
            return;
        } else {
            Card newCard = deck.pickUp();
            hand.addCard(newCard);
        }
    }

    public boolean usedLastCard() {
        return hand.getNumberOfCardsInHand() == 0;
    }

    public String printCardInHand(int i) {
        Card cardInHand = hand.getCard(i);
        return cardInHand.toString();
    }

    public Card getCard(int i) {
        return hand.getCard(i);
    }

    public int getNumberOfCardsInHand() {
        return hand.getNumberOfCardsInHand();
    }

    public boolean canCardBePlayed(Card cardInHand, Card cardOnTopOfPile) {
        return hand.canCardBePlayed(cardInHand, cardOnTopOfPile);
    }

    public Hand getHand() {
        return hand;
    }
    public void shuffleHand() {
        hand.shuffleHand();
    }
}