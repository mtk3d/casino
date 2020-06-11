package casino.roulette;

import casino.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Wheel implements Drawable {
    private Random generator;
    private int currentValue;
    private BufferedImage wheelImage;
    private Ball ball;

    public Wheel() {
        this.generator = new Random();
        this.ball = new Ball(0);
        loadImage();
    }

    public int spin() {
        currentValue = this.generator.nextInt(36);
        ball = new Ball(currentValue);
        return currentValue;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(wheelImage, 20, 80, 160, 160, null);
        g2D.drawString(String.valueOf(currentValue), 20, 250);
        ball.draw(g2D);
    }

    private void loadImage() {
        File file = new File("./images/wheel.png");
        try {
            wheelImage = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
    }
}
