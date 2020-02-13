package com.example.uno;
/**
 * Deck represents the stack of cards players are dealt form,
 * and pick up from. Initially 80 cards are in the deck which is
 * then shuffled. Each player gets dealt 7 cards each and one
 * card put on the Pile
 * */
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardStack {
    private final int NUMBER_OF_CARD_SETS = 2;
    ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        super();
        for (int i = 0; i < NUMBER_OF_CARD_SETS; i++) {
            for (Colour colour : Colour.values()) {
                for (Number number : Number.values()) {
                    Card card = new Card(colour, number);
                    super.push(card, deck);
                }
            }
        }
        Collections.shuffle(deck);
    }

    public Card deal() {
        return super.pop(deck);
    }

    public Card pickUp() {
        return super.pop(deck);
    }

    public boolean deckIsEmpty() {
        return super.stackIsEmpty(deck);
    }

    public int deckHeight() {
        return super.stackHeight(deck);
    }
}