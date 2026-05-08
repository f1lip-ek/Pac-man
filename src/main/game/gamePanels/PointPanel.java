package main.game.gamePanels;

import javax.swing.*;
import java.awt.*;

public class PointPanel extends JPanel {

    private int points;

    public PointPanel(int points){
        this.points = points;

        this.setBackground(Color.BLACK);

    }

    public void setPoints(int points){
        this.points = points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(String.valueOf(points), this.getWidth()/2, 27);
    }
}
