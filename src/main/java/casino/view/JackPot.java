package casino.view;

import casino.Drawable;
import casino.View;
import casino.fruit_machine.FruitMachine;
import casino.player.Player;
import casino.shared.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JackPot implements View {
    private List<Drawable> drawableItems = new ArrayList<>();
    private Button backButton;
    private String nextView = null;
    private FruitMachine machine;

    public JackPot(Player player) {
        backButton = Button.backButton();
        drawableItems.add(backButton);

        machine = FruitMachine.standard(player);
        drawableItems.add(this.machine);
        drawableItems.add(player);
    }

    @Override
    public void event(int x, int y) {
        if (backButton.hasBeenClicked(x, y)) {
            nextView = backButton.getValue();
        }
        machine.clickOn(x, y);
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
