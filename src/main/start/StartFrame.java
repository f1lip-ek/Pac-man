package main.start;

import javax.swing.*;

/**
 * Class that represents the frame at the start of the game
 */
public class StartFrame extends JFrame {

    private static StartGamePanel startGamePanel;

    public StartFrame(){
        startGamePanel = new StartGamePanel(this);
        this.add(startGamePanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Start");

        this.pack();
    }

    /**
     * Method for viewing the frame
     */
    public static void view(){
        StartFrame f = new StartFrame();
        f.setVisible(true);
    }

    /**
     * Method
     */
    public static void playAgain(){
        startGamePanel.playAgain();
    }
}
