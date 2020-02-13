/*
package com.example.uno;

import android.util.Log;
*/
/**
 * Entry point for Uno Game
 * *//*

public class Game {

    public static void main(String[] args) {
        Deck deck = new Deck();
        Pile pile = new Pile();

        // Create Computer and User and serve each a hand
        Computer computer = new Computer(deck);
        User user = new User(deck);

        // Print User's hand
        System.out.println("User has been dealt:");
        for (int i = 0; i < user.getNumberOfCardsInHand(); i++) {
            System.out.println(user.printCardInHand(i));
        }
        System.out.println("\n");

        // Put down the first card
        pile.putDown(deck.pickUp());
        System.out.println(pile.getCardOnTopOfPile() + " is the first card to be drawn from the deck \n");


        // GAME PLAY HAPPENS HERE...
        // Keep playing as long as either player has at least one card
        while (!computer.usedLastCard() && !user.usedLastCard()) {
            System.out.println("There are " + deck.deckHeight() + " cards left in the deck");

            // Computer's turn
            Card chosenCardComputer = computer.playCard(pile.getCardOnTopOfPile());
            if (chosenCardComputer == null) {
                computer.pickUpCard(deck);
            } else {
                pile.putDown(chosenCardComputer);
                if (computer.usedLastCard()) {
                    System.out.println("Computer Wins!");
                    break;
                }
            }

            // User's turn
            Card chosenCardUser = user.playCard(pile.getCardOnTopOfPile());
            if (chosenCardUser == null) {
                user.pickUpCard(deck);
            } else {
                pile.putDown(chosenCardUser);
                if (user.usedLastCard()) {
                    System.out.println(("User Wins!"));
                    break;
                }
            }
        }
    }
}*/
