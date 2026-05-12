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
    private BufferedImage mapChoosen;

    private JComboBox<String> mapChooser;
    private JButton playButton;
    private JButton exitButton;

    private JPanel viewMap;

    private StartFrame startFrame;
    private StartGamePanel startPanel;

    public MapChoose(StartFrame startFrame, StartGamePanel startPanel){
        this.startPanel = startPanel;
        this.startFrame = startFrame;
        this.maps = Level.getImages();
        setMapChooser();
        this.playButton = new JButton("Play");
        this.exitButton = new JButton("Exit");
        setViewMap();
        setButtons();
        this.setLayout(null);
        this.add(mapChooser);
        this.add(viewMap);
        addButtonsToPanel();
    }

    public void setViewMap(){
        this.viewMap = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapChoosen, 100, 10, 600, 450, null);
            }
        };
        this.viewMap.setBounds(0, 31, 800, 469);
    }

    public void setMapChooser(){
        String[] mapNames = new String[maps.length+1];
        mapNames[0] = " ";
        for (int i = 1; i <= maps.length; i++) {
            mapNames[i] = maps[i-1];
        }
        this.mapChooser = new JComboBox<>(mapNames);
        this.mapChooser.setBounds(300, 0, 200, 30);
        this.mapChooser.addActionListener(e -> {
            if (mapChooser.getSelectedIndex() != 0){
                mapChoosen = StaticMethods.getImage("/maps/" + mapChooser.getSelectedItem());
            }
            viewMap.repaint();
        });
    }

    private void setButtons(){
        this.playButton.addActionListener(e -> {
            if (mapChooser.getSelectedIndex() != 0){
                mapImg = StaticMethods.getImage("/maps/" + mapChooser.getSelectedItem());
                startFrame.dispose();
                GameFrame.view(mapImg);
            }
        });
        this.exitButton.addActionListener(e -> {
            startPanel.getCardLayout().show(startPanel, "mainPanel");
        });
    }

    public void addButtonsToPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 500, 800, 100);
        panel.setLayout(new GridLayout(1, 2, 100, 0));
        panel.setBackground(Color.BLACK);
        panel.add(playButton);
        panel.add(exitButton);
        this.add(panel);
    }

    public BufferedImage getMapImg() {
        return mapImg;
    }
}
