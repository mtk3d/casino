package casino.roulette;

import casino.Clickable;
import casino.Drawable;
import casino.Money;

import java.awt.*;

public class Field implements Drawable, Clickable {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String value;
    private Rule rule;
    private final Color color;

    Field(int x, int y, int width, int height, String value, Rule rule, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.rule = rule;
        this.color = color;
    }

    Field(int x, int y, int width, int height, String value, Rule rule) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.rule = rule;
        this.color = Color.BLACK;
    }

    public static Field standard(int x, int y, String value, Rule rule, Color color) {
        return new Field(x, y, 30, 20, value, rule, color);
    }

    public static Field standard(int x, int y, String value, Rule rule) {
        return new Field(x, y, 30, 20, value, rule);
    }

    public Rule getRule() {
        return rule;
    }

    public Bet createBet() {
        return new Bet(rule, Money.of(5), x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.setColor(this.color);
        g2D.fillRect(x, y, width, height);
        g2D.setColor(Color.WHITE);
        g2D.drawRect(x, y, width, height);
        FontMetrics fm = g2D.getFontMetrics();
        int stringWidth = fm.stringWidth(value);
        g2D.drawString(value, (float)(x + (.5 * width) - (.5 * stringWidth)), (float)(y + 15));
    }

    @Override
    public boolean hasBeenClicked(int x, int y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }
}
