package casino.shared;

import casino.Clickable;
import casino.Drawable;

import java.awt.*;

public class Button implements Drawable, Clickable {
    private final String text;
    private final String value;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Button(String text, String value, int x, int y, int width, int height) {
        this.text = text;
        this.value = value;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static Button backButton() {
        return new Button("Back", "game-menu", 10, 10, 50, 30);
    }

    public boolean hasBeenClicked(int x, int y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawRect(x, y, width, height);
        g2D.drawString(text, (float)x + 10, (float)(y + (.5 * height) + 4));
    }
}
