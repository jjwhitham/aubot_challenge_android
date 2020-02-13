package com.example.uno;
/**
 * Pile is a concrete class representing the stack onto which
 * players put down cards
 * */
import java.util.ArrayList;

public class Pile extends CardStack {
    ArrayList<Card> pile = new ArrayList<>();

    public Pile() {
        super();
    }

    public void putDown(Card card) {
        super.push(card, pile);
    }

    public Card getCardOnTopOfPile() {
        return pile.get(0);
    }
}