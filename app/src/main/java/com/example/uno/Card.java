package com.example.uno;
/**
 * Representation of a Card, with a number and colour
 * */
public class Card {
    private Colour colour;
    private Number number;

    public Card(Colour colour, Number number) {
        this.colour = colour;
        this.number = number;
    }

    public Colour getColour() {
        return colour;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + number.toString();
    }
}