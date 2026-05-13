package main.start;

import main.game.StaticThings;

import javax.swing.*;
import java.awt.*;

public class StartGamePanel extends JPanel {

    private CardLayout cardLayout;

    private JPanel mainPanel;
    private MapChoose mapChoose;

    private JLabel label;

    private JButton playButton;
    private JButton optionsButton;
    private JButton exitButton;

    private StartFrame startFrame;

    public StartGamePanel(StartFrame startFrame){
        this.startFrame = startFrame;
        this.mainPanel = new JPanel();
        this.mapChoose = new MapChoose(startFrame, this);

        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.label = new JLabel("Pac-man");

        this.playButton = new JButton("Play");
        this.optionsButton = new JButton("Settings");
        this.exitButton = new JButton("Exit");

        this.mainPanel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT));
        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT));
        this.mainPanel.setBackground(Color.BLACK);
        setLabel();
        setButtons();

        this.add(mainPanel, "mainPanel");
        this.add(mapChoose, "play");
        this.cardLayout.show(this, "mainPanel");
    }

    public void setLabel(){
        this.label.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, (StaticThings.PANEL_HEIGHT/5)*4));
        this.label.setFont(new Font("Arial", Font.BOLD, 50));
        this.label.setForeground(Color.WHITE);
        this.mainPanel.add(label);
    }

    public void setButtons() {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT/5));
        panel.setLayout(new GridLayout(1, 3, 50, 0));
        panel.setBackground(Color.BLACK);

        this.playButton.addActionListener(_ -> {
            this.cardLayout.show(this, "play");
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
        this.mainPanel.add(panel);

    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void playAgain(){
        this.startFrame.setVisible(true);
        this.mapChoose.getMapChooser().setSelectedIndex(mapChoose.getMapChoosenIndex());
        this.mapChoose.getViewMap().repaint();
        this.cardLayout.show(this, "play");
    }

}
