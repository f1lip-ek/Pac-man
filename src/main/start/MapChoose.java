package main.start;

import main.game.GameFrame;
import main.game.StaticMethods;
import main.game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapChoose extends JPanel {

    private String[] maps;
    private BufferedImage mapImg;

    private JComboBox<String> mapChooser;
    private JButton playButton;
    private JButton exitButton;

    private StartFrame startFrame;
    private StartGamePanel startPanel;

    public MapChoose(StartFrame startFrame, StartGamePanel startPanel){
        this.startPanel = startPanel;
        this.startFrame = startFrame;
        this.maps = Level.getImages();
        setMapChooser();
        this.playButton = new JButton("Play");
        this.exitButton = new JButton("Exit");

        setButtons();
        this.setLayout(new GridLayout(3, 1));
        this.add(mapChooser);
        this.add(playButton);
        this.add(exitButton);

    }

    public void setMapChooser(){
        String[] mapNames = new String[maps.length];
        for (int i = 0; i < maps.length; i++) {
            mapNames[i] = maps[i];
        }
        mapChooser = new JComboBox<>(mapNames);
    }

    private void setButtons(){
        this.playButton.addActionListener(e -> {
            mapImg = StaticMethods.getImage("/maps/" + mapChooser.getSelectedItem());
            startFrame.dispose();
            GameFrame.view(mapImg);
        });
        this.exitButton.addActionListener(e -> {
            startPanel.getCardLayout().show(startPanel, "mainPanel");
        });
    }

    public BufferedImage getMapImg() {
        return mapImg;
    }
}
