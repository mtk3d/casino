package casino.view;

import casino.Drawable;
import casino.View;
import casino.player.Player;
import casino.shared.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Blackjack implements View {
    private List<Drawable> drawableItems = new ArrayList<>();
    private Button backButton;
    private String nextView = null;

    public Blackjack(Player player) {
        backButton = Button.backButton();
        drawableItems.add(backButton);

        backButton = Button.backButton();
    }

    @Override
    public void event(int x, int y) {
        if (backButton.hasBeenClicked(x, y)) {
            nextView = backButton.getValue();
        }
    }

    @Override
    public String shouldChangeView() {
        return nextView;
    }

    @Override
    public void draw(Graphics2D g2D) {
        for (Drawable drawable: drawableItems) {
            drawable.draw(g2D);
        }
    }
}
