package com.example.uno;
/**
 * CardStack provides an abstract base class for the two concrete classes
 * 'Deck' and 'Pile' to inherit from.
 * */
import java.util.ArrayList;

public abstract class CardStack {

    public void push(Card card, ArrayList<Card> cardStack) {
        cardStack.add(0, card);
    }

    public Card pop(ArrayList<Card> cardStack) {
        if (stackIsEmpty(cardStack)) {
            return null;
        } else {
            return cardStack.remove(0);
        }
    }

    public boolean stackIsEmpty(ArrayList<Card> cardStack) {
        return cardStack.isEmpty();
    }

    public int stackHeight(ArrayList<Card> cardStack) {
        return cardStack.size();
    }
}