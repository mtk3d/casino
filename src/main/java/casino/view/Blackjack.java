package casino.view;

import casino.Drawable;
import casino.View;
import casino.blackjack.Table;
import casino.player.Player;
import casino.shared.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Blackjack implements View {
    private final Table table;
    private List<Drawable> drawableItems = new ArrayList<>();
    private Button backButton;
    private String nextView = null;

    public Blackjack(Player player) {
        backButton = Button.backButton();
        drawableItems.add(backButton);
        table = Table.basic(player);
        drawableItems.add(table);

        backButton = Button.backButton();
    }

    @Override
    public void event(int x, int y) {
        if (backButton.hasBeenClicked(x, y)) {
            nextView = backButton.getValue();
        }

        table.hasBeenClicked(x, y);
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
