package com.example.uno;
/**
 * Each player gets a hand initially consisting of 7 cards.
 * */
import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private final int INITIAL_HAND_SIZE = 7;

    public Hand(Deck deck) {
        for (int i = 0; i < INITIAL_HAND_SIZE; i++) {
            addCard(deck.deal());
        }
    }

    public int getNumberOfCardsInHand() {
        return hand.size();
    }

    public Card playCard(int i) {
        return hand.remove(i);
    }

    public boolean canCardBePlayed(Card cardInHand, Card cardOnTopOfPile) {
        if (cardInHand.getNumber() == cardOnTopOfPile.getNumber() ||
                cardInHand.getColour() == cardOnTopOfPile.getColour()) {
            return true;
        }
        return false;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getCard(int i) {
        return hand.get(i);
    }
    public void shuffleHand() {
        Collections.shuffle(hand);
    }
}