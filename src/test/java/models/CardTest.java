package models;

import org.junit.Test;

import static org.junit.Assert.*;


public class CardTest {

    Card card = new Card(5, Suit.Spades);

    @Test
    public void getSuitTest() {
        Suit suit = card.getSuit();
        assertEquals(Suit.Spades,suit);
    }

    @Test
    public void getValueTest() {
        int val = card.getValue();
        assertEquals(5,val);
    }

    @Test
    public void testToString(){
        String str = card.toString();
        assertEquals("5Spades",str);
    }
}

