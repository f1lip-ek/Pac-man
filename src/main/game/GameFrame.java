package main.game;

import main.game.levels.Level;
import main.gameOver.GameOverFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the game frame that appears when the game starts
 */
public class GameFrame extends JFrame {

    private final GameOverFrame gameOverFrame;

    private final SwitchingPanel switchingPanel;

    private final int score = 0;
    private final int gameType;
    private int level = 1;

    public GameFrame(BufferedImage mapImg, int gameType){
        this.switchingPanel = new SwitchingPanel(mapImg, this);
        this.setTitle("Pac-man");

        this.gameType = gameType;

        this.gameOverFrame = new GameOverFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(switchingPanel);

        this.setResizable(false);

        this.pack();

        this.switchingPanel.startGame();
    }

    /**
     * Method that checks if the game is over and if it is, it calls the GameOverFrame
     * @param score that the player got
     * @param ending if the player won or lost
     */
    public void viewEnd(int score, int ending){
        if (gameType == 1 && ending == 1 && Level.getNumOfMaps() != level) {
            this.level++;
            this.switchingPanel.getGamePanel().getMainGamePanel().setLevel(level);

            this.switchingPanel.getGamePanel().setGameThread(new Thread(this.switchingPanel.getGamePanel().getGameLoop()));
            this.switchingPanel.getGamePanel().getGameThread().start();

        } else if (gameType == 1 && Level.getNumOfMaps() == level && ending == 1) {
            this.dispose();
            this.gameOverFrame.view(this.score, ending);
        } else {
            this.dispose();
            this.gameOverFrame.view(score, ending);
        }
    }

    /**
     * Method to view the frame
     * @param mapImg that the player is playing on
     * @param gameType that the player is playing
     */
    public static void view(BufferedImage mapImg, int gameType){
        new GameFrame(mapImg, gameType).setVisible(true);
    }
}
