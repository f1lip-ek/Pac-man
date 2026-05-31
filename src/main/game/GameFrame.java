package main.game;

import main.game.levels.Level;
import main.gameOver.GameOverFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the game frame that appears when the game starts
 */
public class GameFrame extends JFrame {

    private final GamePanel gamePanel;
    private final GameOverFrame gameOverFrame;

    private final int score = 0;
    private final int gameType;
    private int level = 1;

    public GameFrame(BufferedImage mapImg, int gameType){
        this.setTitle("Pac-man");

        this.gameType = gameType;
        this.gamePanel = new GamePanel(mapImg, this);

        this.gameOverFrame = new GameOverFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(gamePanel);

        this.setResizable(false);

        this.pack();
    }

    /**
     * Method that checks if the game is over and if it is, it calls the GameOverFrame
     * @param score that the player got
     * @param ending if the player won or lost
     */
    public void viewEnd(int score, int ending){
        if (gameType == 1 && ending == 1 && Level.getNumOfMaps() != level) {
            this.level++;
            gamePanel.getMainGamePanel().setLevel(level);

            this.gamePanel.setGameThread(new Thread(this.gamePanel.getGameLoop()));
            this.gamePanel.getGameThread().start();

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
