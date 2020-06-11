package casino.view;

import casino.Drawable;
import casino.View;
import casino.player.Player;
import casino.roulette.RouletteGame;
import casino.shared.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Roulette implements View {
    private List<Drawable> drawableItems = new ArrayList<>();
    private Button backButton;
    private String nextView = null;
    private RouletteGame roulette;

    public Roulette(Player player) {
        backButton = Button.backButton();
        roulette = RouletteGame.standard(player);

        drawableItems.add(backButton);
        drawableItems.add(roulette);
        drawableItems.add(player);
    }

    @Override
    public void event(int x, int y) {
        if (backButton.hasBeenClicked(x, y)) {
            nextView = backButton.getValue();
        }
        roulette.clickOn(x, y);
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
