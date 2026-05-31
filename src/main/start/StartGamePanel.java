package main.start;

import main.game.StaticThings;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the panel that appears when the game starts
 */
public class StartGamePanel extends JPanel {

    private final CardLayout cardLayout;

    private final JPanel mainPanel;
    private final MapChoose mapChoose;
    private final GameTypeChooser gameTypeChooser;

    private final JLabel label;

    private final JButton playButton;
    private final JButton gameTypeButton;
    private final JButton exitButton;

    private StartFrame startFrame;

    public StartGamePanel(StartFrame startFrame){
        this.startFrame = startFrame;
        this.mainPanel = new JPanel();
        this.mapChoose = new MapChoose(startFrame, this);
        this.gameTypeChooser = new GameTypeChooser(this, startFrame);

        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.label = new JLabel("Pac-man");

        this.playButton = new JButton("Play");
        this.gameTypeButton = new JButton("Game Type");
        this.exitButton = new JButton("Exit");

        this.mainPanel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT));
        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT));
        this.mainPanel.setBackground(Color.BLACK);
        setLabel();
        setButtons();

        this.add(mainPanel, "mainPanel");
        this.add(mapChoose, "play");
        this.add(gameTypeChooser, "gameType");
        this.cardLayout.show(this, "mainPanel");
    }

    /**
     * Method to set the label with the title of the game
     */
    private void setLabel(){
        this.label.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, (StaticThings.PANEL_HEIGHT/5)*4));
        this.label.setFont(new Font("Arial", Font.BOLD, 50));
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.mainPanel.add(label);
    }

    /**
     * Method to set position and action listeners of the buttons at the start of the game
     */
    private void setButtons() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT/5));
        panel.setLayout(new GridLayout(1, 2, 50, 0));
        panel.setBackground(Color.BLACK);

        this.playButton.addActionListener(_ -> {
            this.cardLayout.show(this, "gameType");
        });
        this.exitButton.addActionListener(_ -> {
            System.exit(0);
        });

        panel.add(playButton);
        panel.add(exitButton);
        this.mainPanel.add(panel);

    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Method to play again when the player loses or wins
     */
    public void playAgain(){
        this.startFrame.setVisible(true);
        this.mapChoose.getMapChooser().setSelectedIndex(mapChoose.getMapChoosenIndex());
        this.mapChoose.getViewMap().repaint();
        this.cardLayout.show(this, "gameType");
    }

}
