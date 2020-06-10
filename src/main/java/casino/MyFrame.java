package casino;

import javax.swing.*;

public class MyFrame extends JFrame {
    Casino casino = new Casino();

    public MyFrame() {
        this.setSize(420, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(casino);
        this.setVisible(true);
    }
}