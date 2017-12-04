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

    public int highScore = 0;

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
            int num = table.canMove();
            if (num >= 0) {
                table.moveFromToCol(colFrom, num);
                feedbackText = "";
            } else if (num == -1) {
                feedbackText = "There are no empty slots to move to!";
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
        if (this.deckType == 'S') {
            this.deckType = 'E';
        } else if (this.deckType == 'E') {
            this.deckType = 'S';
        }
        this.highScore = 0;
        this.reset();
    }

    //checks if the player has lost, meaning there are no cards in the deck and no removes or moves possible
    //will return true when the player wins as well, so the score will be updated then
    public void hasPlayerLost() {
        if (!deck.hasCards()) {
            for (int i = 0; i < 4; i++) {
                if (table.canRemove(i)) {
                    return;
                }
            }
            if (table.canMove() >= 0) {
                return;
            }

            //update highscore
            if (score > highScore) {
                highScore = score;
            }
            this.playerLost = true;
        }
    }
}
