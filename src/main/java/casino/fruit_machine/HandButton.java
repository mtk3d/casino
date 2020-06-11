package casino.fruit_machine;

import casino.shared.Button;

public class HandButton extends Button {
    HandButton(int x, int y, int width, int height) {
        super("Play 35$", "play", x, y, width, height);
    }

    public static HandButton basic() {
        return new HandButton(300, 280, 100, 20);
    }
}