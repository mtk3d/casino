package casino.blackjack;

import casino.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Card implements Drawable {
    private final String sign;
    private final String color;
    private final BufferedImage image;
    private BufferedImage reverseImage;
    private boolean faceUp;
    private int x;
    private int y;

    Card(String sign, String color, BufferedImage image, BufferedImage reverseImage, boolean faceUp) {
        this.sign = sign;
        this.color = color;
        this.image = image;
        this.reverseImage = reverseImage;
        this.faceUp = faceUp;
    }

    public String getName() {
        return sign+"-"+color;
    }

    public int getValue() {
        int value = Integer.parseInt(sign);
        if (value >= 2 && value <= 10) {
            return value;
        } else if (sign == "as") {
            return 0;
        } else {
            return 10;
        }
    }

    public void faceUp() {
        faceUp = true;
    }

    public void faceDown() {
        faceUp = false;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g2D) {
        BufferedImage cardImage;

        if (faceUp) {
            cardImage = image;
        } else {
            cardImage = reverseImage;
        }

        g2D.drawImage(cardImage, x, y, 40, 60, null);
    }
}
