package casino.blackjack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CardFactory {
    private static List<String> signs = Arrays.asList("2","3","4","5","6","7","8","9","10","walet","dama","krol","as");
    private static List<String> colors = Arrays.asList("karo","kier","pik","trefl");
    private HashMap<String, BufferedImage> images = new HashMap<>();
    private List<Card> basicDeck;

    CardFactory() {
        for (String sign: signs) {
            for (String color: colors) {
                images.put(sign+"-"+color, loadImage("./images/cards/"+sign+"-"+color+".png"));
            }
        }
        images.put("reverse", loadImage("./images/cards/reverse.png"));

        basicDeck = getDeck().getCards();
    }

    public Card getCard(boolean facedUp) {
        int index = new Random().nextInt(52);
        Card card = basicDeck.get(index);
        if (facedUp) {
            card.faceUp();
        } else {
            card.faceDown();
        }

        return card;
    }

    public Card getCard() {
        return getCard(false);
    }

    public Deck getDeck() {
        List<Card> cards = new ArrayList<>();
        for (String sign: signs) {
            for (String color: colors) {
                cards.add(new Card(sign, color, images.get(sign+"-"+color), images.get("reverse"), false));
            }
        }
        return new Deck(cards);
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