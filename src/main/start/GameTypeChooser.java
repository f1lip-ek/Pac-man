package main.start;

import main.game.GameFrame;
import main.game.StaticThings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameTypeChooser extends JPanel {

    private int gameType;

    private JLabel label;

    private JButton progressiveTypeButton;
    private JButton customTypeButton;
    private JButton exitButton;

    private StartGamePanel startPanel;
    private StartFrame startFrame;

    public GameTypeChooser(StartGamePanel startPanel, StartFrame startFrame){
        this.startFrame = startFrame;

        this.label = new JLabel("Choose Game Type:");
        this.startPanel = startPanel;
        this.exitButton = new JButton("Exit");
        this.progressiveTypeButton = new JButton("Progressive");
        this.customTypeButton = new JButton("Custom");
        setButtons();
        this.setLayout(null);

        this.setBackground(Color.BLACK);
        setLabel();
        addButtonsToPanel();
    }

    private void setButtons(){
        this.progressiveTypeButton.addActionListener(e -> {
            this.gameType = 1;
            BufferedImage mapImg = StaticThings.getImage("/maps/map_01.png");
            startFrame.dispose();
            GameFrame.view(mapImg, gameType);
        });
        this.customTypeButton.addActionListener(e -> {
            this.gameType = 2;
            startPanel.getCardLayout().show(startPanel, "play");
        });
        this.exitButton.addActionListener(e -> {
            startPanel.getCardLayout().show(startPanel, "mainPanel");
        });
    }

    public void addButtonsToPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 500, 800, 100);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        exitButton.setBounds(300, 25, 200, 50);
        panel.add(exitButton);

        this.add(buttonPanel());
        this.add(panel);
    }

    private JPanel buttonPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 100, 800, 400);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        this.progressiveTypeButton.setBounds(300, 100, 200, 50);
        this.customTypeButton.setBounds(300, 200, 200, 50);

        panel.add(progressiveTypeButton);
        panel.add(customTypeButton);
        return panel;
    }

    private void setLabel(){
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setBounds(0, 50, 800, 50);
        this.label.setFont(new Font("Arial", Font.BOLD, 24));
        this.label.setForeground(Color.WHITE);
        this.add(label);
    }


    public int getGameType() {
        return gameType;
    }
}
