import java.util.*;
/*
Include your name, date here
Isaac Cadena
4-3-25
*/

interface Shufflable {
    void shuffle();
}

interface PlayableEntity {
    void takeTurn();
}

abstract class GamePiece {
    abstract void play();
}

class Card extends GamePiece implements Comparable<Card> {
    private final char rank;
    private final char suit;

    public Card(char rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public char getRank() {
        return rank;
    }

    @Override
    public void play() {
        // Simply prints the card rank and suit
        System.out.print(this.toString());
    }

    @Override
    public int compareTo(Card other) {
        // Implement comparison logic based on rank
        //Each rank is given a value, where Aces is the highest, and they are compared
        int value = 0; int otherValue = 0;
        if (this.rank == 'A'){ value = 14;} if (other.getRank() == 'A'){otherValue = 14;}
        if (this.rank == '2'){ value = 2;} if (other.getRank() == '2'){otherValue = 2;}
        if (this.rank == '3'){ value = 3;} if (other.getRank() == '3'){otherValue = 3;}
        if (this.rank == '4'){ value = 4;} if (other.getRank() == '4'){otherValue = 4;}
        if (this.rank == '5'){ value = 5;} if (other.getRank() == '5'){otherValue = 5;}
        if (this.rank == '6'){ value = 6;} if (other.getRank() == '6'){otherValue = 6;}
        if (this.rank == '7'){ value = 7;} if (other.getRank() == '7'){otherValue = 7;}
        if (this.rank == '8'){ value = 8;} if (other.getRank() == '8'){otherValue = 8;}
        if (this.rank == '9'){ value = 9;} if (other.getRank() == '9'){otherValue = 9;}
        if (this.rank == 'T'){ value = 10;} if (other.getRank() == 'T'){otherValue = 10;}
        if (this.rank == 'J'){ value = 11;} if (other.getRank() == 'J'){otherValue = 11;}
        if (this.rank == 'Q'){ value = 12;} if (other.getRank() == 'Q'){otherValue = 12;}
        if (this.rank == 'K'){ value = 13;} if (other.getRank() == 'K'){otherValue = 13;}
        //return 0 if player 1 wins, 1 if player 2 wins, or 2 if a tie
        if (value > otherValue){return 0;} else if (otherValue > value){return 1;} else {return 2;}
    }

    @Override
    public String toString() {
        return rank + "-" + suit;
    }
}

class Deck implements Shufflable {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        // Initialize deck with all 52 cards by creating 2 arrays for suits and ranks
        ArrayList<Character> suits = new ArrayList<>();
        ArrayList<Character> ranks = new ArrayList<>();
        suits.add('C'); suits.add('D'); suits.add('H'); suits.add('S');
        ranks.add('A'); ranks.add('2'); ranks.add('3'); ranks.add('4'); ranks.add('5'); ranks.add('6'); ranks.add('7'); ranks.add('8'); ranks.add('9'); ranks.add('T');
        ranks.add('J'); ranks.add('Q'); ranks.add('K');
        //double for loop to get all combinations of cards and puts them into the deck
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                Card card = new Card(ranks.get(j),suits.get(i));
                cards.add(card);
            }
        }
        //shuffles the deck
        this.shuffle();
    }

    @Override
    public void shuffle() {
        // Implement shuffle logic
        Collections.shuffle(cards);
    }

    public List<Card> splitDeck() {
        // Return half of the deck by going through the first 26 cards
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < 26;i++){newCards.add(cards.get(i));}
        return newCards;
    }

    public List<Card> getRemainingHalf() {
        // Return the other half of the deck by going through the last 26 cards
        List<Card> newerCards = new ArrayList<>();
        for (int i = 26; i < 52;i++){newerCards.add(cards.get(i));}
        return newerCards;
    }
}

class Player implements PlayableEntity {
    private final Queue<Card> hand;

    public Player(List<Card> cards) {
        hand = new LinkedList<>(cards);
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public Card drawCard() {
        // Implement logic to draw a card
        return hand.poll();
    }

    public void collectCards(List<Card> wonCards) {
        // Implement logic to collect won cards
        for (Card element : wonCards){hand.offer(element);}
    }

    public int getCardCount() {
        return hand.size();
    }

    @Override
    public void takeTurn() {
        // Placeholder for player actions
    }
}

class WarGame {
    private final Player player1;
    private final Player player2;

    public WarGame() {
        Deck deck = new Deck();
        player1 = new Player(deck.splitDeck());
        player2 = new Player(deck.getRemainingHalf());
    }

    public void playGame() {
        while (player1.hasCards() && player2.hasCards()) {
            playRound();
        }
        declareWinner();
    }

    private void playRound() {
        // Implement round logic
        //Both players draw cards from their piles
        Card card1 = player1.drawCard();
        Card card2 = player2.drawCard();
        //Both players play their cards, which are shown
        System.out.print("Player 1 has played: "); card1.play(); System.out.println();
        System.out.print("Player 2 has played: "); card2.play(); System.out.println();
        //Cards are compared. The player that wins collects both cards. If tie, a war begins
        if (card1.compareTo(card2) == 0){List<Card> won = new ArrayList<Card>(); won.add(card1); won.add(card2); player1.collectCards(won); System.out.println("Player 1 collects");}
        else if (card1.compareTo(card2) == 1){List<Card> won = new ArrayList<Card>(); won.add(card1); won.add(card2); player2.collectCards(won); System.out.println("Player 2 collects");}
        else {
            //Cards are added to the warpile one by one (10 cards total). A check is made every time to make sure the pplayers can still add to the war pile.
            List<Card> warPile = new ArrayList<>();
            if (!player1.hasCards() || !player2.hasCards()){return;}
            warPile.add(card1);
            for (int i = 0; i < 4; i++){
                if (!player1.hasCards() || !player2.hasCards()){return;}
                warPile.add(player1.drawCard());
            }
            warPile.add(card2);
            for (int i = 0; i < 4; i++){
                if (!player1.hasCards() || !player2.hasCards()){return;}
                warPile.add(player2.drawCard());
            }
            //calls handleWar using the warpile with 10 cards
            handleWar(warPile);
            System.out.println("War over");
        }
        System.out.println();
    }

    private void handleWar(List<Card> warPile) {
        // Implement war scenario
        System.err.println("WAR");
        //Gets the fourth card each player played into the war pile
        Card war1 = warPile.get(warPile.size()-6);
        Card war2 = warPile.get(warPile.size()-1);
        //Simply states the cards each player played.
        System.out.print("Player 1 has played: "); war1.play(); System.out.println();
        System.out.print("Player 2 has played: "); war2.play(); System.out.println();
        //Compares the 4th cards from each player to see who wins
        if (war1.compareTo(war2) == 0){player1.collectCards(warPile); System.out.println("Player 1 wins the war");}
        else if (war1.compareTo(war2) == 1){player2.collectCards(warPile); System.out.println("Player 2 wins the war");}
        else {
            //Cards are added to the warpile one by one. A check is made every time to make sure the players can still add to the war pile.
            if (!player1.hasCards() || !player2.hasCards()){return;}
            warPile.add(war1);
            for (int i = 0; i < 4; i++){
                if (!player1.hasCards() || !player2.hasCards()){return;}
                warPile.add(player1.drawCard());
            }
            warPile.add(war2);
            for (int i = 0; i < 4; i++){
                if (!player1.hasCards() || !player2.hasCards()){return;}
                warPile.add(player2.drawCard());
            }
            //handle warPile is called again recursively
            handleWar(warPile);
        }

    }

    private void declareWinner() {
        // Implement logic to declare winner by checking if both players are out, or only one
        if (!player1.hasCards() && !player2.hasCards()){System.out.println("TIE");}
        else if (!player1.hasCards() && player2.hasCards()){System.out.println("Player 2 Wins");}
        else if (player1.hasCards() && !player2.hasCards()){System.out.println("Player 1 Wins");}
        else {return;}

    }

    public static void main(String[] args) {
        // there is no need to change the main method. 
        // all work is in the methods above.
        WarGame game = new WarGame();
        game.playGame();
    }
}