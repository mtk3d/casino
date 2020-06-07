package player;

import casino.Money;
import casino.Drawable;
import java.awt.Graphics2D;

public class Player implements Drawable {
    private Money money;

    private Player(Money money) {
        this.money = money;
    }

    public static Player standard() {
        return new Player(Money.of(200));
    }

    public void give(Money money) {
        this.money = this.money.sum(money);
    }

    public void take(Money money) {
        this.money = this.money.substract(money);
    }

    public int money() {
        return this.money.value();
    }

    public void draw(Graphics2D g2D) {
        g2D.drawString("Your money: " + this.money.toString(), 10, 20);
    }
}