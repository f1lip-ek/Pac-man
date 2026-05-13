package main.game.gamePanels;

import main.game.GamePanel;
import main.game.StaticThings;
import main.game.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthPanel extends JPanel {

    private BufferedImage image;
    private int health;

    public HealthPanel(Player p){
        this.health = p.getLives();

        this.image = StaticThings.getImage("/entities/pacman/pacman_right.png");

        this.setPreferredSize(new Dimension(GamePanel.RECT_SIZE*3, GamePanel.RECT_SIZE));

        this.setBackground(Color.BLACK);

        this.setOpaque(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < health; i++) {
            g.drawImage(image, 10 + i*(GamePanel.RECT_SIZE + 10), 0, 40, 40, null);
        }
    }

    public void setHealth(int health){
        this.health = health;
    }
}
