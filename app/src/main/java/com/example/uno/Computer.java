/**
 * Concrete Computer agent
 * */
package com.example.uno;

import android.util.Log;

public class Computer extends Player {

    public Computer(Deck deck) {
        super(deck);
    }


    public Card playCard(Card cardOnTopOfPile) {
        // Pick random card, keeping track of hand indices not yet tested
        super.shuffleHand();
        int i = 0;
        for (i = 0; i < super.getNumberOfCardsInHand(); i++) {
            Card cardToPlay = super.getCard(i);
            if (super.canCardBePlayed(cardToPlay, cardOnTopOfPile)) {
                // Log which card has been played
//                Log.d("myTag", "Computer's current hand:");
//                for (int j = 0; j < super.getNumberOfCardsInHand(); j++) {
//                    Log.d("myTag", j + ": " + super.printCardInHand(j));
//                }
                System.out.print("The Computer has played: ");
                Log.d("myTag", super.printCardInHand(i));
                super.playCard(i);
                Log.d("myTag", "There are " + super.getNumberOfCardsInHand() + " cards left in the Computer's hand. \n");
                return cardToPlay;
            }
        }
        Log.d("myTag", "The Computer just picked up a card \n");
        return null;
    }
}

//    public Card playCard(Card cardOnTopOfPile) {
//        // Pick random card, keeping track of hand indices not yet tested
//        Random rand = new Random();
//        ArrayList<Integer> cardsToChooseFrom = new ArrayList<>();
//        for (int i = 0; i < super.getNumberOfCardsInHand(); i++) {
//            cardsToChooseFrom.add(i);
//        }
//        for (int idx = 0; idx < cardsToChooseFrom.size(); idx++) {
//            Log.d("myTag", "[" + idx + ", " + cardsToChooseFrom.get(idx) + "]");
//        }
//        Log.d("myTag", "");
//        int i = 0;
//        // if the chosen card cannot be played, choose another, as long as there are still cards to choose from
//        do {
//            i = rand.nextInt(cardsToChooseFrom.size());
//            i = cardsToChooseFrom.remove(i);
//            Log.d("myTag", "Computer is about to check card i: " + i);
//            Log.d("myTag", super.getCard(i).toString());
//            for (int idx = 0; idx < cardsToChooseFrom.size(); idx++) {
//                Log.d("myTag", "[" + idx + ", " + cardsToChooseFrom.get(idx) + "]");
//            }
//            Log.d("myTag", "");
//        } while (!super.canCardBePlayed(super.getCard(i), cardOnTopOfPile) && cardsToChooseFrom.size() > 0);
//        // if the card cannot be played, then return null
//        if (!super.canCardBePlayed(super.getCard(i), cardOnTopOfPile)) {
//            return null;
//        }
//        Card cardToPlay = super.getCard(i);
//        // Log which card has been played
//        System.out.print("The Computer has played: ");
//        Log.d("myTag", super.printCardInHand(i));
//        super.playCard(i);
//        Log.d("myTag", "There are " + super.getNumberOfCardsInHand() + " cards left in the Computer's hand. \n");
//        return cardToPlay;
//    }