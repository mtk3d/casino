package casino.roulette;

import casino.Drawable;
import casino.Money;
import casino.shared.Button;
import casino.player.Player;

import java.awt.*;

public class RouletteGame implements Drawable {
    private final Player player;
    private final Board board;
    private Wheel wheel;
    private Button spin;
    private Button takeWin;
    private Money win = Money.of(0);

    RouletteGame(Player player, Board board, Wheel wheel) {
        this.player = player;
        this.board = board;
        this.wheel = wheel;
        this.spin = new Button("Spin", "spin", 10, 290, 100, 30);
        this.takeWin = new Button("Take win", "take", 120, 290, 100, 30);
    }

    public static RouletteGame standard(Player player) {
        return new RouletteGame(player, Board.generate(), new Wheel());
    }

    @Override
    public void draw(Graphics2D g2D) {
        board.draw(g2D);
        wheel.draw(g2D);
        spin.draw(g2D);
        takeWin.draw(g2D);
    }

    public void clickOn(int x, int y) {
        if (spin.hasBeenClicked(x, y)) {
            int value = wheel.spin();
            win = board.calculate(value);
        } else if (takeWin.hasBeenClicked(x, y)) {
            player.give(win);
            board.clear();
            win = Money.of(0);
        } else {
            if (player.hasEnough(Money.of(5))) {
                if (board.hasBeenClicked(x, y)) {
                    player.take(Money.of(5));
                }
            }
        }
    }
}
