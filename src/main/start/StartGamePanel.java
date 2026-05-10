package main.start;

import main.game.GameFrame;
import main.game.GamePanel;
import main.gameOver.GameOverFrame;

import javax.swing.*;
import java.awt.*;

public class StartGamePanel extends JPanel {

    private JLabel label;

    private JButton playButton;
    private JButton optionsButton;
    private JButton exitButton;

    private StartFrame startFrame;

    public StartGamePanel(StartFrame startFrame){
        this.startFrame = startFrame;

        this.label = new JLabel("Pac-man");

        this.playButton = new JButton("Play");
        this.optionsButton = new JButton("Settings");
        this.exitButton = new JButton("Exit");

        this.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        setLabel();
        setButtons();
    }

    public void setLabel(){
        this.label.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, (GamePanel.PANEL_HEIGHT/5)*4));
        this.label.setFont(new Font("Arial", Font.BOLD, 50));
        this.label.setForeground(Color.WHITE);
        this.add(label);
    }

    public void setButtons() {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT/5));
        panel.setLayout(new GridLayout(1, 3, 50, 0));
        panel.setBackground(Color.BLACK);

        this.playButton.addActionListener(_ -> {
            this.startFrame.dispose();
            GameFrame.view();
        });

        this.optionsButton.addActionListener(_ -> {
            System.out.println("Options");
        });
        this.exitButton.addActionListener(_ -> {
            System.exit(0);
        });

        panel.add(playButton);
        panel.add(optionsButton);
        panel.add(exitButton);
        this.add(panel);

    }

}
