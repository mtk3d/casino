package casino.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    Deck(List<Card> cards) {
        this.cards = cards;
    }

    Deck() {
        this.cards = new ArrayList<>();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card takeFirst(boolean facedUp) {
        Card card = cards.get(0);
        cards.remove(card);
        if (facedUp) {
            card.faceUp();
        } else {
            card.faceDown();
        }
        return card;
    }

    public Card takeFirst() {
        return takeFirst(false);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
