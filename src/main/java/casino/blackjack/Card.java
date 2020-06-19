package casino.blackjack;

import casino.Drawable;
import com.sun.tools.javac.util.StringUtils;

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

    public String getSign() {
        return sign;
    }

    public int getValue() {
        if (sign.equals("as")) {
            return 0;
        }

        if (sign.equals("krol") || sign.equals("dama") || sign.equals("walet")) {
            return 10;
        }

        return Integer.parseInt(sign);
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

        g2D.drawImage(cardImage, x, y, 60, 80, null);
    }
}
