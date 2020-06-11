package casino.roulette;

import casino.Clickable;
import casino.Drawable;
import casino.Money;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bet implements Drawable, Clickable {

    private final Rule rule;
    private Money money;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Bet(Rule rule, Money money, int x, int y, int width, int height) {
        this.rule = rule;
        this.money = money;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void increase() {
        this.money = this.money.sum(Money.of(5));
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.BLUE);
        g2D.fillOval((int)(x + (.5 * width) - (.5 * 18)), (int)(y + (.5 * height) - (.5 * 18)), 18, 18);
        g2D.setColor(Color.WHITE);
        FontMetrics fm = g2D.getFontMetrics();
        int stringWidth = fm.stringWidth(money.toString());
        g2D.drawString(money.toString(), (float)(x + (.5 * width) - (.5 * stringWidth)), (float)(y + (.5 * height) + 5));
    }

    @Override
    public boolean hasBeenClicked(int x, int y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }

    public boolean inRange(int value) {
        return rule.hit(value);
    }

    public Money getWin() {
        return money.sum(Money.of(money.value() * rule.getMultiplier()));
    }
}
