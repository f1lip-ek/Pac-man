package main.start;

import main.game.StaticThings;

import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * Class that represents the frame at the start of the game
 */
public class StartFrame extends JFrame {

    private static StartGamePanel startGamePanel;

    private final Clip sound;

    public StartFrame(){
        startGamePanel = new StartGamePanel(this);
        this.add(startGamePanel);

        this.sound = StaticThings.playSound("/music/startSound.wav");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Start");

        this.pack();
        this.sound.start();
        this.sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void disposeThis(){
        this.dispose();
        this.sound.stop();
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
