package casino.roulette;

import casino.Drawable;

import java.awt.*;
import java.util.Random;

public class Wheel implements Drawable {
    private Random generator;
    private int currentValue;

    public Wheel() {
        this.generator = new Random();
    }

    public int spin() {
        currentValue = this.generator.nextInt(36);
        return currentValue;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawString(String.valueOf(currentValue), 10, 150);
    }
}
