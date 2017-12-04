package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpanishDeckTest {

    SpanishDeck deck = new SpanishDeck();

    @Test
    public void testBuildDeck(){
        assertEquals(50, this.deck.deck.size());
    }
}
