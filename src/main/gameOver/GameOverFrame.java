package main.gameOver;

import javax.swing.*;

public class GameOverFrame extends JFrame {

    private GameOverPanel gameOverPanel;

    public GameOverFrame(){
        this.gameOverPanel = new GameOverPanel(this);
        this.add(gameOverPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Game Over");

        this.pack();
    }

    public void view(){
        this.setVisible(true);
    }
}
