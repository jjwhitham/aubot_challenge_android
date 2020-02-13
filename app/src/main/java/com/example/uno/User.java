package com.example.uno;

/**
 * Concrete User agent
 * */
import java.util.Scanner;
import android.util.Log;

public class User extends Player {

    public User(Deck deck) {
        super(deck);
    }

    public Card playCard(int chosenCardIndex, Card cardOnTopOfPile) {


        if (!super.canCardBePlayed(super.getCard(chosenCardIndex), cardOnTopOfPile)) {
            return null;
        }

        // Display which card has been played and return it
        Card cardToPlay = super.getCard(chosenCardIndex);
        Log.d("myTag", "The User has played: " + super.printCardInHand(chosenCardIndex));
        super.playCard(chosenCardIndex);
        Log.d("myTag", "There are " + super.getNumberOfCardsInHand() + " cards left in the User's hand. \n");
        return cardToPlay;
    }
}