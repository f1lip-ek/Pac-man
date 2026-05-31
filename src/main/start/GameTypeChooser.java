package main.start;

import main.game.GameFrame;
import main.game.StaticThings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the panel from which the user can choose the game type to play
 */
public class GameTypeChooser extends JPanel {

    private int gameType;

    private final JLabel label;

    private final JButton progressiveTypeButton;
    private final JButton customTypeButton;
    private final JButton exitButton;

    private final StartGamePanel startPanel;
    private final StartFrame startFrame;

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

    /**
     * Method to set the action listeners for the buttons
     */
    private void setButtons(){
        this.progressiveTypeButton.addActionListener(e -> {
            this.gameType = 1;
            BufferedImage mapImg = StaticThings.getImage("/maps/map_01.png");
            startFrame.disposeThis();
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

    /**
     * Method to add the buttons to the panel
     */
    private void addButtonsToPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 500, 800, 100);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        exitButton.setBounds(300, 25, 200, 50);
        StaticThings.editButton(exitButton, new Color(255, 244, 0), Color.BLACK);
        panel.add(exitButton);

        this.add(buttonPanel());
        this.add(panel);
    }

    /**
     * Method to create the panel with the buttons
     * @return JPanel with added buttons
     */
    private JPanel buttonPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 100, 800, 400);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        this.progressiveTypeButton.setBounds(300, 100, 200, 50);
        this.customTypeButton.setBounds(300, 200, 200, 50);

        StaticThings.editButton(this.progressiveTypeButton, new Color(255, 244, 0), Color.BLACK);
        StaticThings.editButton(this.customTypeButton, new Color(255, 244, 0), Color.BLACK);

        panel.add(progressiveTypeButton);
        panel.add(customTypeButton);
        return panel;
    }

    /**
     * Method to set the label to say to the player what they need to do in this panel
     */
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
