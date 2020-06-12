package casino.blackjack;

import casino.Drawable;
import casino.Money;
import casino.player.Player;

import java.awt.*;

public class Table implements Drawable {
    private final Player player;
    private final CardFactory cardFactory;
    private Deck playerDeck = new Deck();
    private Deck croupierDeck = new Deck();
    private Money bet = Money.of(0);

    Table(Player player, CardFactory cardFactory) {
        this.player = player;
        this.cardFactory = cardFactory;
        start();
    }

    public void start() {
        playerDeck.addCard(cardFactory.getCard(true));
        playerDeck.addCard(cardFactory.getCard(true));
        croupierDeck.addCard(cardFactory.getCard(true));
        croupierDeck.addCard(cardFactory.getCard());
    }

    public void hit() {
        playerDeck.addCard(cardFactory.getCard(true));
    }

    public void doubleDown() {
        bet = bet.sum(bet);
    }

    public void stand() {
        croupierDeck.addCard(cardFactory.getCard(true));
    }

    public static Table basic(Player player) {
        return new Table(player, new CardFactory());
    }

    @Override
    public void draw(Graphics2D g2D) {
        int i = 0;
        for (Card card: croupierDeck.getCards()) {
            card.setPosition(100+i, 100);
            card.draw(g2D);
            i+=20;
        }
    }
}
