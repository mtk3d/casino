package casino.view;

import casino.Drawable;
import casino.View;
import casino.shared.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameMenu implements Drawable, View {
    private List<Button> menuList = new ArrayList<>();
    private String nextView = null;

    public GameMenu() {
        menuList.add(new Button("Jackpot", "jackpot", 20, 100, 100, 30));
        menuList.add(new Button("Roulette", "roulette", 20, 150, 100, 30));
        menuList.add(new Button("Blackjack", "blackjack", 20, 200, 100, 30));
    }

    @Override
    public void event(int x, int y) {
        for (Button button : menuList) {
            if (button.hasBeenClicked(x, y)) {
                nextView = button.getValue();
            }
        }
    }

    @Override
    public String shouldChangeView() {
        return nextView;
    }

    @Override
    public void draw(Graphics2D g2D) {
        for (Button button : menuList) {
            button.draw(g2D);
        }
    }
}
