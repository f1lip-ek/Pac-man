package main.gameOver;

import main.game.GameFrame;
import main.game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    private JLabel label;
    private JLabel label2;

    private JButton playAgainButton;
    private JButton exitButton;
    private JButton goMainMenuButton;

    private GameOverFrame gameOverFrame;

    public GameOverPanel(GameOverFrame gameOverFrame){
        this.gameOverFrame = gameOverFrame;
        this.label = new JLabel("Game");
        this.label2 = new JLabel("Over");

        this.playAgainButton = new JButton("Play Again");
        this.exitButton = new JButton("Exit");
        this.goMainMenuButton = new JButton("Go to Main Menu");

        this.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLabels();
        this.setButtons();
    }

    public void setLabels(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, (GamePanel.PANEL_HEIGHT/5)*4));
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.BLACK);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(Color.BLACK);

        JPanel labelPanel2 = new JPanel();
        labelPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel2.setBackground(Color.BLACK);

        this.label.setForeground(Color.WHITE);
        this.label2.setForeground(Color.WHITE);
        this.label.setFont(new Font("Arial", Font.BOLD, 100));
        this.label2.setFont(new Font("Arial", Font.BOLD, 100));
        labelPanel.add(label);
        labelPanel2.add(label2);


        panel.add(labelPanel);
        panel.add(labelPanel2);

        this.add(panel);
    }

    public void setButtons(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT/5));
        panel.setLayout(new GridLayout(1, 3, 50, 0));
        panel.setBackground(Color.BLACK);

        this.playAgainButton.addActionListener(_ -> {
            this.gameOverFrame.dispose();
            GameFrame.view();
        });
        this.exitButton.addActionListener(_ -> {
            System.exit(0);
        });

        panel.add(playAgainButton);
        panel.add(goMainMenuButton);
        panel.add(exitButton);
        this.add(panel);
    }

}
