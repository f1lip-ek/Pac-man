package main.start;

import javax.swing.*;

public class StartFrame extends JFrame {

    private static StartGamePanel startGamePanel;

    public StartFrame(){
        this.startGamePanel = new StartGamePanel(this);
        this.add(startGamePanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Start");

        this.pack();
    }

    public static void view(){
        StartFrame f = new StartFrame();
        f.setVisible(true);
    }

    public static void playAgain(){
        startGamePanel.playAgain();
    }

    public StartGamePanel getStartGamePanel(){
        return startGamePanel;
    }
}
