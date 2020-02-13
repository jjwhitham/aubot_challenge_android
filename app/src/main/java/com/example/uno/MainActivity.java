package com.example.uno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    LinearLayout mLinearLayout;
    TextView mTopOfPile;
    TextView mComputerPlayed;
    TextView mDeckHeight;
    TextView mNumCardsComputer;
    final int PICK_UP_CARD_ID = 99;

    /**
     * Entry point on creation of Game
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Deck deck = new Deck();
        final Pile pile = new Pile();

        // Create Computer and User and serve each a hand
        final Computer computer = new Computer(deck);
        final User user = new User(deck);

        // Put down the first card
        pile.putDown(deck.pickUp());

        // Set static text fields
        setTextFields(computer, deck, pile);

        // Initialise "Computer Played" field
        mComputerPlayed = findViewById(R.id.computer_played);
        mComputerPlayed.append(" Nothing (User's turn)");

        // Render the User's hand
        renderAllButtons(user, computer, deck, pile);
    }

    /**
     * Renders all buttons on start and each time a button is pressed
     */
    protected void renderAllButtons(final User user, final Computer computer, final Deck deck,
                                    final Pile pile) {
        mLinearLayout = findViewById(R.id.linear_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // Initialise buttons. Card buttons are indexed from 0 to
        // user.getNumberOfCardsInHand()-1. IDs cannot be negative,
        // so choose PICK_UP_CARD_ID = 99
        for (int i = -1; i < user.getNumberOfCardsInHand(); i++) {
            Button button = new Button(MainActivity.this);
            if (i == -1) {
                button.setId(PICK_UP_CARD_ID);
                button.setText("Pick Up Card");
                button.setTextSize(20);
            } else {
                button.setId(i);
                String cardText = user.getCard(i).toString();
                button.setText(cardText);
            }

            // Set button functionality
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    boolean isEndGame = false;
                    // Wipe all buttons when a button is pressed
                    removeAllButtons(user.getNumberOfCardsInHand());
                    // Check if button is Pick Up or Card
                    if (v.getId() == PICK_UP_CARD_ID) {
                        user.pickUpCard(deck);
                        isEndGame = computersTurn(user, computer, deck, pile);
                    } else {
                        isEndGame = putDownCard(v.getId(), user, computer, deck, pile);
                    }
                    // Don't re-render buttons at end of game
                    Log.d("myTag", "above conditional, isEndGame: " + isEndGame);
                    if (!isEndGame) {
                        Log.d("myTag", "inside conditional");
                        renderAllButtons(user, computer, deck, pile);
                    } else {
                        removeAllButtons(user.getNumberOfCardsInHand());
                    }
                    // Set text fields
                    setTextFields(computer, deck, pile);
                }
            });

            // Set parameters and add buttons to view
            button.setLayoutParams(params);
            mLinearLayout.addView(button);
        }
    }

    /**
     * Removes all buttons for re-render, and upon game completion
     */
    protected void removeAllButtons(int numCardsInHand) {
        mLinearLayout = findViewById(R.id.linear_layout);
        if (mLinearLayout != null) {
            for (int i = -1; i < numCardsInHand; i++) {
                View button;
                if (i == -1) {
                    button = mLinearLayout.findViewById(PICK_UP_CARD_ID);
                } else {
                    button = mLinearLayout.findViewById(i);
                }
                mLinearLayout.removeView(button);
            }
        }
    }

    /**
     * Sets the static text fields
     */
    protected void setTextFields(Computer computer, Deck deck, Pile pile) {
        mDeckHeight = findViewById(R.id.deck_height);
        mDeckHeight.setText("Cards remaining in deck: " + deck.deckHeight());

        mTopOfPile = findViewById(R.id.top_of_pile);
        mTopOfPile.setText("Top of Pile: " + pile.getCardOnTopOfPile().toString());

        mNumCardsComputer = findViewById(R.id.num_cards_computer);
        mNumCardsComputer.setText("Cards in Computer's hand: " + computer.getNumberOfCardsInHand());
    }


    /**
     * Handles putting down a User's chosen card, including rejecting invalid
     * choices, and User winning the game
     */
    protected boolean putDownCard(int chosenCardIndex,
                                  User user, Computer computer, Deck deck, Pile pile) {
        Card chosenCardUser = user.playCard(chosenCardIndex, pile.getCardOnTopOfPile());
        // If the chosen card cannot be played
        if (chosenCardUser == null) {
            Toast.makeText(getApplicationContext(), "Invalid card!", Toast.LENGTH_LONG).show();
            return false;
        }
        // Otherwise, place the card on the pile and check for empty hand
        pile.putDown(chosenCardUser);
        if (user.usedLastCard()) {
            Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG).show();
            endGame(user, "You Win!");
            return true;
        }
        computersTurn(user, computer, deck, pile);
        return false;
    }

    /**
     * Handles the Computers turn, including card put down, pick up
     * and the Computer winning
     */
    protected boolean computersTurn(User user,
                                    Computer computer, Deck deck, Pile pile) {
        Log.d("myTag", "computersTurn() called");
        Card chosenCardComputer = computer.playCard(pile.getCardOnTopOfPile());
        // If the Computer cannot play any cards in hand, then pick up
        if (chosenCardComputer == null) {
            mComputerPlayed.setText("Computer picked up...");
            computer.pickUpCard(deck);
            return false;
        }
        // Otherwise, place the card on the pile and check for empty hand
        pile.putDown(chosenCardComputer);
        mComputerPlayed.setText("Computer Played: " + chosenCardComputer.toString());
        if (computer.usedLastCard()) {
            Toast.makeText(getApplicationContext(), "Computer Wins!", Toast.LENGTH_LONG).show();
            endGame(user, "Computer Wins!");
            return true;
        }
        return false;
    }

    /**
     * Cleans up buttons at the end of the game, and displays outcome
     */
    protected void endGame(User user, String endText) {
        removeAllButtons(user.getNumberOfCardsInHand());
        TextView currentHand = findViewById(R.id.current_hand);
        currentHand.setText(endText);

    }
}