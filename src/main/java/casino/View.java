package casino;

import casino.player.Player;

import java.awt.*;

public interface View {
    void event(int x, int y);
    String shouldChangeView();
    void draw(Graphics2D g2D);
}
