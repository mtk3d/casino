package casino.blackjack;

import java.awt.*;
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

    public int getPoints() {
        int sum = 0;
        List<Card> aces = new ArrayList<>();
        for (Card card: cards) {
            if (card.getSign().equals("as")) {
                aces.add(card);
            }
            sum += card.getValue();
        }

        for (Card card: aces) {
            if (21 - sum >= 11) {
                sum += 11;
            } else {
                sum += 1;
            }
        }

        return sum;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void draw(Graphics2D g2D, int x, int y) {
        int offset = -cards.size() * 20 / 2;
        g2D.drawString(String.valueOf(getPoints()), x + offset - 25, y + 45);
        for (Card card: cards) {
            card.setPosition(x + offset, y);
            card.draw(g2D);
            offset += 20;
        }
    }
}
