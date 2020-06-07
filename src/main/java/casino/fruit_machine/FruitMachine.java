package fruit_machine;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import casino.Drawable;
import casino.Money;
import player.Player;

public class FruitMachine implements Drawable {
    private List<Roll> rolls;
    private ChanceIntGenerator generator;
    private RollImages rollImages;
    private HandButton button;
    private Player player;

    private FruitMachine(List<Roll> rolls, ChanceIntGenerator generator, RollImages rollImages, Player player) {
        this.rolls = rolls;
        this.generator = generator;
        this.rollImages = rollImages;
        this.button = HandButton.basic();
        this.player = player;
    }

    public static FruitMachine standard(Player player) {
        List<Roll> rolls = new ArrayList<Roll>();
        rolls.add(Roll.standard());
        rolls.add(Roll.standard());
        rolls.add(Roll.standard());

        return new FruitMachine(rolls, new ChanceIntGenerator(Roll.Sign.getLastValueIndex() - 1, 3), new RollImages(), player);
    }

    public void pullLever() {
        this.generator.regenerate();
        for (Roll roll : this.rolls) {
            int random = this.generator.nextInt();
            roll.turn(random);
        }
    }

    public boolean allTheSame() {
        boolean state = true;
        for (Roll roll : this.rolls) {
            state &= roll.isSame(this.rolls.get(0));
        }
        return state;
    }

    public List<Roll.Sign> getSigns() {
        List<Roll.Sign> signs = new ArrayList<Roll.Sign>();
        for (Roll roll : this.rolls) {
            signs.add(roll.getSign());
        }
        return signs;
    }

    public Money getWin() {
        if (!this.allTheSame()) {
            return Money.of(0);
        }

        int sum = 0;
        for (Roll roll : this.rolls) {
            sum += roll.getValue();
        }

        return Money.of(sum * 10);
    }

    public void draw(Graphics2D g2D) {
        int i = 0;
        for (Roll roll : this.rolls) {
            int val = roll.getValue();
            g2D.drawImage(rollImages.getImageOf(val), 100+i, 80, 60, 60, null);
            i += 75;
        }
        this.button.draw(g2D);
        g2D.drawString(this.allTheSame() ? "Win" : "Lose", 20, 250);
        g2D.drawString(this.allTheSame() ? this.getWin().toString() : "0", 100, 250);
    }

    public void clickOn(int x, int y) {
        if (this.button.isInRange(x, y)) {
            this.player.take(Money.of(45));
            this.pullLever();
            this.player.give(this.getWin());
        }
    }
}