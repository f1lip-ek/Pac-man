package main.start;

import main.game.GameFrame;
import main.game.StaticThings;
import main.game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the panel from which the user can choose the map to play
 */
public class MapChoose extends JPanel {

    private String[] maps;
    private BufferedImage mapImg;
    private BufferedImage mapChoosen;
    private int mapChoosenIndex;

    private JComboBox<String> mapChooser;
    private final JButton playButton;
    private final JButton exitButton;

    private JPanel viewMap;

    private final StartFrame startFrame;
    private final StartGamePanel startPanel;

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
        this.setBackground(Color.BLACK);
        this.add(mapChooser);
        this.add(viewMap);
        addButtonsToPanel();
    }

    /**
     * Method to set the preview of the choosen map
     */
    private void setViewMap(){
        this.viewMap = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapChoosen, 100, 10, 600, 450, null);
            }
        };
        this.viewMap.setBounds(0, 31, 800, 469);
        this.viewMap.setBackground(Color.BLACK);
    }

    /**
     * Method to set the JComboBox with all of the aviable maps
     */
    private void setMapChooser(){
        String[] mapNames = new String[maps.length+1];
        mapNames[0] = " ";
        for (int i = 1; i <= maps.length; i++) {
            mapNames[i] = maps[i-1];
        }
        this.mapChooser = new JComboBox<>(mapNames);
        this.mapChooser.setBounds(300, 0, 200, 30);
        this.mapChooser.addActionListener(e -> {
            if (mapChooser.getSelectedIndex() != 0){
                mapChoosen = StaticThings.getImage("/maps/" + mapChooser.getSelectedItem());
            }
            viewMap.repaint();
        });
    }

    /**
     * Method to set the action listeners of the buttons
     */
    private void setButtons(){
        this.playButton.addActionListener(e -> {
            if (mapChooser.getSelectedIndex() != 0){
                mapImg = StaticThings.getImage("/maps/" + mapChooser.getSelectedItem());
                mapChoosenIndex = mapChooser.getSelectedIndex();
                startFrame.dispose();
                GameFrame.view(mapImg, 2);
            }
        });
        this.exitButton.addActionListener(e -> {
            startPanel.getCardLayout().show(startPanel, "gameType");
        });
    }

    /**
     * Method to add the buttons to the panel
     */
    private void addButtonsToPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 500, 800, 100);
        panel.setLayout(new GridLayout(1, 2, 100, 0));
        panel.setBackground(Color.BLACK);
        panel.add(playButton);
        panel.add(exitButton);
        this.add(panel);
    }

    public JComboBox<String> getMapChooser() {
        return mapChooser;
    }

    public int getMapChoosenIndex() {
        return mapChoosenIndex;
    }

    public JPanel getViewMap() {
        return viewMap;
    }
}
