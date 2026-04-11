package main.game;

import javax.swing.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private Thread loop;
    private GameLoop gameLoop;

    public GameFrame(){
        this.gamePanel = new GamePanel();
        this.gameLoop = new GameLoop(gamePanel);
        this.loop = new Thread(gameLoop);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);

        this.loop.start();

        this.setResizable(false);

        this.pack();
        this.setVisible(true);
    }
}
