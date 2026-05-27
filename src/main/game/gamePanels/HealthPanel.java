package main.game.gamePanels;

import main.game.StaticThings;
import main.game.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the panel that shows the health of the player
 */
public class HealthPanel extends JPanel {

    private final BufferedImage image;
    private int health;

    public HealthPanel(Player p){
        this.health = p.getLives();

        this.image = StaticThings.getImage("/entities/pacman/open/pacman_open_right.png");

        this.setPreferredSize(new Dimension(StaticThings.RECT_SIZE*3, StaticThings.RECT_SIZE));

        this.setBackground(Color.BLACK);

        this.setOpaque(true);

    }

    /**
     * Method that paints the health of the player
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < health; i++) {
            g.drawImage(image, 10 + i*(StaticThings.RECT_SIZE + 10), 0, 40, 40, null);
        }
    }

    public void setHealth(int health){
        this.health = health;
    }
}
