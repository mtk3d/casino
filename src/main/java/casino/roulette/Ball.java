package casino.roulette;

import casino.Drawable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Ball implements Drawable {
    private final List<Integer> order;
    private final int number;
    private int x;
    private int y;

    private static final int r = 50;

    Ball(int number) {
        this.order = Arrays.asList(0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26);
        this.number = number;
        calculate();
    }

    private void calculate() {
        double angle = (6.28318531 / 37) * (order.indexOf(number) + 1) - 1.74532925;
        this.x = (int)(100 + r * Math.cos(angle)) - 3;
        this.y = (int)(159 + r * Math.sin(angle)) - 3;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.fillOval(x, y, 6, 6);
    }
}
