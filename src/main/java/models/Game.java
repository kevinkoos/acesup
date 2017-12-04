package models;

public class Game {

    public Deck deck = new Deck();

    public Tableau table = new Tableau();

    //Used to give feedback to the user if an action is not possible
    public String feedbackText = "";

    public int score = 0;

    public char deckType = 'E';

    public boolean playerLost = false;

    public boolean playerWon = false;

    public Game() {
        this.dealFour();
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        // if the deck has less than 4 cards, deal only what is left
        for (int i = 0; i < 4; i++) {
            if (deck.hasCards()) {
                table.addCardToCol(i, deck.takeTopCard());
            }
        }
        feedbackText = "";
    }

    public void remove(int colNumber) {
        if (table.colHasCards(colNumber)) {
            int jokerCol = table.existJoker();
            if (table.canRemove(colNumber) || table.getTopCardValue(colNumber) == 0) {
                table.removeFromCol(colNumber);
                feedbackText = "";
                score++;
            }
            // if there is a joker, remove both cards
            else if (jokerCol != -1) {
                table.removeFromCol(colNumber);
                table.removeFromCol(jokerCol);
                feedbackText = "Joker used!";
                score += 2;
            } else
                feedbackText = "That card can't be removed!";
        } else
            feedbackText = "No card to remove!";
    }

    public void move(int colFrom) {
        if (table.colHasCards(colFrom)) {
            int num = table.canMove(colFrom);
            if (num >= 0) {
                table.moveFromToCol(colFrom, num);
                feedbackText = "";
            } else if (num == -1) {
                feedbackText = "There are no empty slots to move to!";
            } else if (num == -2) {
                feedbackText = "Only Aces can be moved!";
            }
        } else {
            feedbackText = "No card to move!";
        }
    }

    //deckType = 'E' for english and 'S' for spanish cards
    public void reset() {
        this.score = 0;
        this.feedbackText = "";
        this.table = new Tableau();
        this.playerLost = false;
        if (this.deckType == 'E') {
            this.deck = new Deck();
        } else {
            this.deck = new SpanishDeck();
        }
        this.dealFour();
    }
    
    public void switchDeck() {
        if (this.deckType == 'S'){
            this.deckType = 'E';
        } else {
            this.deckType = 'S';
        }

        this.reset();
    }

    //checks if the player has lost, meaning there are no cards in the deck and no removes or moves possible
    public void hasPlayerLost() {
        if (!deck.hasCards()) {
            for (int i = 0; i < 4; i++) {
                if (table.canRemove(i))
                    return;
                else if (table.canMove(i) >= 0)
                    return;
            }

            this.playerLost = true;
        }
    }

    /*
        Win conditions:
           1) Deck is empty
           2) Each column has no more than 1 card left
           3) The only cards remaining are Aces
     */
    public void hasPlayerWon() {
        if (deck.hasCards())
            return;
        for (int i = 0; i < 4; i++) {
            if (table.cardCount(i) != 1)
                return;
            else {
                if (deckType == 'S' && table.getTopCardValue(i) != 13)
                    return;
                else if (table.getTopCardValue(i) != 14)
                    return;
            }
        }

        playerWon = true;
    }
}
