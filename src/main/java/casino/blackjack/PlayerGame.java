package casino.blackjack;

import casino.Money;

import java.awt.*;

public class PlayerGame {
    private Money bet = Money.of(0);
    private Deck cards = new Deck();

    PlayerGame() {

    }

    PlayerGame(Card initialCard) {
        cards.addCard(initialCard);
    }

    public void doubleDown() {
        bet = bet.sum(bet);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public void drawCurrent(Graphics2D g2D) {
        cards.draw(g2D, 180, 180);
    }

    public void drawSecond(Graphics2D g2D) {
        cards.draw(g2D, 260, 270);
    }

    public void upBet(Money money) {
        bet = bet.sum(money);
    }

    public void drawBet(Graphics2D g2D) {
        g2D.drawString(bet.toString(), 180, 160);
    }

    public Money getBet() {
        return bet;
    }

    public int getPoints() {
        return cards.getPoints();
    }

    public Money getWin() {
        if (cards.getPoints() == 21) {
            return bet.sum(Money.of((int)(bet.value() * 1.5)));
        }

        return bet.sum(bet);
    }
}
