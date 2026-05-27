package main.gameOver;

import javax.swing.*;

/**
 * Class that represents the frame that appears when the game ends
 */
public class GameOverFrame extends JFrame {

    private final GameOverPanel gameOverPanel;

    public GameOverFrame(){
        this.gameOverPanel = new GameOverPanel(this);
        this.add(gameOverPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Game Over");

        this.pack();
    }

    /**
     * Method to view the frame
     * @param score that the player got
     * @param ending if the player won or lost
     */
    public void view(int score, int ending){
        this.gameOverPanel.setNums(score, ending);
        this.gameOverPanel.setEndingText();
        this.gameOverPanel.repaint();
        this.setVisible(true);
    }
}
