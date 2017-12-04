package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TableauTest {

    Tableau table = new Tableau();
    Card testCard1 = new Card(2, Suit.Hearts);
    Card testCard2 = new Card(5, Suit.Spades);
    Card testCard3 = new Card(14, Suit.Spades);
    Card testCard4 = new Card(13, Suit.Spades);
    Card spanishCard1 = new Card(2, Suit.Oros);
    Card spanishCard2 = new Card(12, Suit.Espadas);
    Card spanishCard3 = new Card(13, Suit.Espadas);
    Card joker = new Card(0, Suit.JOKER);

    @Test
    public void testCanRemove() {
        table.addCardToCol(1, testCard1);
        table.addCardToCol(2, testCard2);
        table.addCardToCol(3, testCard3);
        assertEquals(false, table.canRemove(1));
        assertEquals(true, table.canRemove(2));
        assertEquals(false, table.canRemove(3));
    }

    @Test
    public void testCanMove() {
        table.addCardToCol(1, testCard1);
        table.addCardToCol(2, testCard4);
        table.addCardToCol(3, testCard3);
        assertEquals(0, table.canMove(3));
        assertEquals(-2, table.canMove(2));
        table.addCardToCol(0, testCard2);
        assertEquals(-1, table.canMove(3));
    }

    @Test
    public void testExistJoker() {
        table.addCardToCol(1, spanishCard1);
        table.addCardToCol(2, spanishCard2);
        table.addCardToCol(3, spanishCard3);
        assertEquals(-1, table.existJoker());
        table.addCardToCol(2, joker);
        assertEquals(2, table.existJoker());
    }

    @Test
    public void testMove() {
        table.addCardToCol(1, testCard1);
        table.moveFromToCol(1, 2);
        assertEquals(false, table.colHasCards(1));
        assertEquals(2, table.getTopCardValue(2));
        assertEquals(Suit.Hearts, table.getTopCardSuit(2));
    }

    @Test
    public void testCardCount() {
        assertEquals(0, table.cardCount(0));
        table.addCardToCol(0, testCard1);
        table.addCardToCol(0, testCard2);
        table.addCardToCol(0, testCard3);
        assertEquals(3, table.cardCount(0));
    }
}
