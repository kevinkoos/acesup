package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void testBuild(){
        deck.buildDeck();
    }

    @Test
    public void testDraw(){
        Card c = deck.takeTopCard();
    }

    @Test
    public void testHasCards(){
        boolean bool = deck.hasCards();
        assertEquals(true,bool);
    }
}
