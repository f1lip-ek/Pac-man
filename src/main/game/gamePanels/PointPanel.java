package main.game.gamePanels;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the panel that shows the points of the player
 */
public class PointPanel extends JPanel {

    private int points;

    public PointPanel(int points){
        this.points = points;

        this.setBackground(Color.BLACK);

    }

    public void setPoints(int points){
        this.points = points;
    }

    /**
     * Method that paints the points of the player
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(String.valueOf(points), this.getWidth()/2, 27);
    }
}
