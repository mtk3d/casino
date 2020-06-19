package casino.blackjack;

import casino.Clickable;
import casino.Drawable;
import casino.Money;
import casino.player.Player;
import casino.shared.Button;

import java.awt.*;

public class Table implements Drawable, Clickable {
    private final Player player;
    private final CardFactory cardFactory;
    private PlayerGame playerGame = new PlayerGame();
    private PlayerGame secondPlayerGame;
    private final Deck croupierDeck = new Deck();
    private final Button hitButton;
    private final Button doubleDownButton;
    private final Button standButton;
    private boolean gameEnd = false;
    private final Button startButton;
    private final Button upButton;

    private boolean started = false;

    Table(Player player, CardFactory cardFactory) {
        hitButton        = new Button("Hit",    "hit",        10,  300, 80, 30);
        doubleDownButton = new Button("Double", "doubleDown", 100, 300, 80, 30);
        standButton      = new Button("Stand",  "stand",      190, 300, 80, 30);
        startButton      = new Button("Start",  "start",      100, 170, 80, 30);
        upButton         = new Button("Up 10$", "up",         190, 170, 80, 30);
        this.player = player;
        this.cardFactory = cardFactory;
    }

    public static Table basic(Player player) {
        return new Table(player, new CardFactory());
    }

    public void start() {
        started = true;
        playerGame.addCard(cardFactory.getCard(true));
        playerGame.addCard(cardFactory.getCard(true));
        croupierDeck.addCard(cardFactory.getCard());
        croupierDeck.addCard(cardFactory.getCard(true));
    }

    public void hit() {
        playerGame.addCard(cardFactory.getCard(true));
        if (playerGame.getPoints() > 21) {
            gameOver();
        }
    }

    private void gameOver() {
        hitButton.deactivate();
        doubleDownButton.deactivate();
        standButton.deactivate();
    }

    public void doubleDown() {
        if (player.hasEnough(playerGame.getBet())) {
            player.take(playerGame.getBet());
            playerGame.doubleDown();
        }
    }

    public void stand() {
        for (Card card: croupierDeck.getCards()) {
            card.faceUp();
        }

        int croupierPoints = croupierDeck.getPoints();
        if (croupierPoints < 16) {
            croupierDeck.addCard(cardFactory.getCard(true));
            standButton.deactivate();
        }

        if (croupierPoints == 21 && playerGame.getPoints() == 21) {
            player.give(playerGame.getBet());
        } else if (playerGame.getPoints() > croupierPoints) {
            player.give(playerGame.getWin());
        }

        gameEnd = true;
    }

    @Override
    public void draw(Graphics2D g2D) {
        player.draw(g2D);
        playerGame.drawBet(g2D);
        if (!started) {
            startButton.draw(g2D);
            upButton.draw(g2D);
        } else {
            playerGame.drawCurrent(g2D);
            if (secondPlayerGame != null) {
                secondPlayerGame.drawSecond(g2D);
            }
            croupierDeck.draw(g2D, 180, 50);

            hitButton.draw(g2D);
            doubleDownButton.draw(g2D);
            standButton.draw(g2D);
        }
    }

    @Override
    public boolean hasBeenClicked(int x, int y) {
        if (startButton.hasBeenClicked(x, y)) {
            start();
        }

        if (upButton.hasBeenClicked(x, y)) {
            Money up = Money.of(10);
            if (player.hasEnough(up)) {
                player.take(up);
                playerGame.upBet(up);
            }
        }

        if (hitButton.hasBeenClicked(x, y)) {
            hit();
        } else if (doubleDownButton.hasBeenClicked(x, y)) {
            doubleDown();
        } else if (standButton.hasBeenClicked(x, y)) {
            stand();
        }

        return false;
    }
}
