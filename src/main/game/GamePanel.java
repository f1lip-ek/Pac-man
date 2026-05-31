package main.game;

import main.game.gamePanels.HealthPanel;
import main.game.gamePanels.MainGamePanel;
import main.game.gamePanels.PointPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the main panel of the game
 */
public class GamePanel extends JPanel {

    private final MainGamePanel mainGamePanel;
    private final HealthPanel healthPanel;
    private final PointPanel pointPanel;

    private Thread gameThread;
    private final GameLoop gameLoop;


    public GamePanel(BufferedImage mapImg, GameFrame gameFrame){
        this.gameLoop = new GameLoop(this, gameFrame);
        this.removeAll();
        this.mainGamePanel = new MainGamePanel(mapImg);
        this.healthPanel = new HealthPanel(mainGamePanel.getPlayer());
        this.pointPanel = new PointPanel(mainGamePanel.getPlayer().getScore());
        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT + StaticThings.RECT_SIZE));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        this.add(mainGamePanel);
        panel();

        this.gameThread = new Thread(gameLoop);
        this.gameThread.start();
    }

    /**
     * Method to add the health and score panel for viewing
     */
    private void panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.RECT_SIZE));
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

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }
}
