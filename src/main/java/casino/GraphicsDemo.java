package casino;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import fruit_machine.FruitMachine;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;
import java.lang.InterruptedException;
import player.Player;

public class GraphicsDemo extends JPanel implements MouseListener {  
    List<Drawable> items = new ArrayList();
    FruitMachine machine;
    Player player;

    GraphicsDemo() {
        this.player = Player.standard();
        this.machine = FruitMachine.standard(this.player);
        this.machine.pullLever();

        items.add(this.machine);
        items.add(this.player);
        addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        this.machine.clickOn(e.getX(), e.getY());
        repaint();
 	}

 	public void mouseClicked(MouseEvent e) {
 	}
 
 	public void mouseEntered(MouseEvent e) {
 	}

    public void mouseExited(MouseEvent e) {
 	}

 	public void mouseReleased(MouseEvent e) {
 	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        
        for (Drawable item : this.items) {
            item.draw(g2D);
        }
    }
}