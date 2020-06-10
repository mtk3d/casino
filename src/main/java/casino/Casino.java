package casino;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import casino.player.Player;
import casino.view.Blackjack;
import casino.view.GameMenu;
import casino.view.JackPot;
import casino.view.Roulette;

public class Casino extends JPanel implements MouseListener {
    private List<Drawable> drawableItems = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private Player player;
    private View currentView;

    Casino() {
        player = Player.standard();
        currentView = new GameMenu();

        addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        currentView.event(e.getX(), e.getY());
        String newView = currentView.shouldChangeView();
        if (newView != null) {
            changeView(newView);
        }
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

 	private void changeView(String view) {
        switch (view) {
            case "jackpot": currentView = new JackPot(player); break;
            case "roulette": currentView = new Roulette(player); break;
            case "blackjack": currentView = new Blackjack(player); break;
            default: currentView = new GameMenu();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        
        currentView.draw(g2D);
    }
}