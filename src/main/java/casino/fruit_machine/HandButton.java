package fruit_machine;

import casino.Drawable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class HandButton implements Drawable {
    private int x;
    private int y;
    private int height;
    private int width;

    HandButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static HandButton basic() {
        return new HandButton(300, 240, 100, 20);
    }

    public void draw(Graphics2D g2D) {
        g2D.drawRect(this.x, this.y, this.width, this.height);
        g2D.drawString("Play 45$", this.x + 35, this.y + 15);
    }

    public boolean isInRange(int x, int y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }
}