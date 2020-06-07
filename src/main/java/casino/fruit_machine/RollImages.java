package fruit_machine;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RollImages {
    List<BufferedImage> images = new ArrayList();

    RollImages() {
        this.images.add(RollImages.loadImage("./images/apple.png"));
        this.images.add(RollImages.loadImage("./images/banana.png"));
        this.images.add(RollImages.loadImage("./images/bell.png"));
        this.images.add(RollImages.loadImage("./images/cherries.png"));
        this.images.add(RollImages.loadImage("./images/grapes.png"));
        this.images.add(RollImages.loadImage("./images/lemon.png"));
        this.images.add(RollImages.loadImage("./images/watermelon.png"));
    }

    public BufferedImage getImageOf(int n) {
        return this.images.get(n);
    }

    private static BufferedImage loadImage(String path) {
        File imageFile = new File(path);
        try {
 			return ImageIO.read(imageFile);
 		} catch (IOException e) {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
            return null;
 		}
    }
}