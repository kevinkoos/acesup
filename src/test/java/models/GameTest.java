package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Game game = new Game();

    @Test
    public void testDealFour() {
        for (int i = 0; i < 4; i++)
            assertEquals(1, game.table.cardCount(i));
        
        game.dealFour();
        for (int i = 0; i < 4; i++)
            assertEquals(2, game.table.cardCount(i));
    }
}
