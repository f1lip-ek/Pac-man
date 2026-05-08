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

        this.setSize(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT + GamePanel.RECT_SIZE);
        this.add(gamePanel);

        this.gameThread.start();

        this.setResizable(false);

        this.pack();
    }

    public void viewEnd(int score, int ending){
        this.dispose();
        this.gameOverFrame.view(score, ending);
    }

    public static void view(){
        new GameFrame().setVisible(true);
    }
}
