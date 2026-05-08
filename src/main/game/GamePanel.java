package main.game;

import main.game.gamePanels.MainGamePanel;
import main.game.levels.Level;
import main.game.entities.Ghost;
import main.game.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_WIDTH = 800;
    public static final int RECT_SIZE = 40;

    private MainGamePanel mainGamePanel;

    public GamePanel(){
        this.mainGamePanel = new MainGamePanel();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.add(mainGamePanel);
    }

    public MainGamePanel getMainGamePanel() {
        return mainGamePanel;
    }
}
