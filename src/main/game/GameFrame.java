package main.game;

import main.game.levels.Level;
import main.gameOver.GameOverFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private Thread gameThread;
    private GameLoop gameLoop;
    private GameOverFrame gameOverFrame;

    private int score = 0;
    private int gameType;
    private int level = 1;

    public GameFrame(BufferedImage mapImg, int gameType){
        this.setTitle("Pac-man");

        this.gameType = gameType;
        this.gamePanel = new GamePanel(mapImg);
        this.gameLoop = new GameLoop(gamePanel, this);
        this.gameThread = new Thread(gameLoop);

        this.gameOverFrame = new GameOverFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(gamePanel);

        this.gameThread.start();

        this.setResizable(false);

        this.pack();
    }

    public void viewEnd(int score, int ending){
        if (gameType == 1 && Level.getNumOfMaps() == level && ending == 1) {
            this.dispose();
            this.gameOverFrame.view(this.score, ending);
        }

        if (gameType == 2){
            this.dispose();
            this.gameOverFrame.view(score, ending);
        } else if (gameType == 1 && ending == 1 && Level.getNumOfMaps() != level) {
            this.score += score;
            this.level++;
            gamePanel.getMainGamePanel().setLevel(level);
            this.gameThread = new Thread(gameLoop);
            this.gameThread.start();
        } else if (gameType == 1 && Level.getNumOfMaps() == level && ending == 1) {
            this.dispose();
            this.gameOverFrame.view(this.score, ending);
        }
    }

    public static void view(BufferedImage mapImg, int gameType){
        new GameFrame(mapImg, gameType).setVisible(true);
    }
}
