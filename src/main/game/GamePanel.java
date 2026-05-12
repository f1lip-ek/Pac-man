package main.game;

import main.game.gamePanels.HealthPanel;
import main.game.gamePanels.MainGamePanel;
import main.game.gamePanels.PointPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_WIDTH = 800;
    public static final int RECT_SIZE = 40;

    private MainGamePanel mainGamePanel;
    private HealthPanel healthPanel;
    private PointPanel pointPanel;


    public GamePanel(BufferedImage mapImg){
        this.removeAll();
        this.mainGamePanel = new MainGamePanel(mapImg);
        this.healthPanel = new HealthPanel(mainGamePanel.getPlayer());
        this.pointPanel = new PointPanel(mainGamePanel.getPlayer().getScore());
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT + RECT_SIZE));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        this.add(mainGamePanel);
        panel();
    }

    public void panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, RECT_SIZE));
        panel.add(healthPanel);
        panel.add(pointPanel);

        this.add(panel);
    }

    public MainGamePanel getMainGamePanel() {
        return mainGamePanel;
    }

    public HealthPanel getHealthPanel() {
        return healthPanel;
    }

    public PointPanel getPointPanel() {
        return pointPanel;
    }
}
