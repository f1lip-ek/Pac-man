package main.game;

import main.game.gamePanels.CountdownPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that works as a switch between the countdown panel and the game panel
 */
public class SwitchingPanel extends JPanel {

    private final CountdownPanel countdownPanel;
    private final GamePanel gamePanel;

    private CardLayout cardLayout;

    public SwitchingPanel(BufferedImage mapImg, GameFrame gameFrame){
        this.countdownPanel = new CountdownPanel();
        this.gamePanel = new GamePanel(mapImg, gameFrame);

        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT + StaticThings.RECT_SIZE));

        this.add(countdownPanel, "countdown");
        this.add(gamePanel, "game");
    }

    /**
     * Method that starts the countdown and then views the game
     */
    public void startGame(){
        this.cardLayout.show(this, "countdown");
        new Thread(new Runnable() {
            @Override
            public void run() {
                countdownPanel.countdown();
                SwingUtilities.invokeLater(() -> {
                    cardLayout.show(getPanel(), "game");
                    gamePanel.getMainGamePanel().setFocusable(true);
                    gamePanel.getMainGamePanel().requestFocus();
                    gamePanel.getGameThread().start();
                });
            }
        }).start();

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public SwitchingPanel getPanel(){
        return this;
    }
}
