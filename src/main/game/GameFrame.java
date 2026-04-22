package main.game;

import main.gameOver.GameOverFrame;

import javax.swing.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private Thread gameThread;
    private GameLoop gameLoop;
    private GameOverFrame gameOverFrame;

    public GameFrame(){
        this.setTitle("Pac-man");

        this.gamePanel = new GamePanel();
        this.gameLoop = new GameLoop(gamePanel, this);
        this.gameThread = new Thread(gameLoop);

        this.gameOverFrame = new GameOverFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);

        this.gameThread.start();

        this.setResizable(false);

        this.pack();
    }

    public void viewEnd(){
        this.dispose();
        this.gameOverFrame.view();
    }

    public static void view(){
        new GameFrame().setVisible(true);
    }
}
