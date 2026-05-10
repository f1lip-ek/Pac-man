package main.game.gamePanels;

import main.game.GamePanel;
import main.game.MyKeyListener;
import main.game.entities.Ghost;
import main.game.entities.Player;
import main.game.levels.Level;

import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends JPanel {

    private Player player;

    private Ghost[] ghosts;
    private String[] ghostNames = {"blinky", "pinky", "inky", "clyde"};

    private MyKeyListener keyListener;
    private Level level;

    public MainGamePanel(){
        this.player = new Player();
        this.ghosts = new Ghost[4];
        setGhosts();
        this.keyListener = new MyKeyListener(player);
        this.level = new Level(player, ghosts);

        player.setLevel(level);
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].setLevel(level);
        }

        this.setFocusable(true);

        this.setPreferredSize(new Dimension(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT));
        this.addKeyListener(keyListener);

        this.setOpaque(true);

        level.setImgArray();
        System.out.println(level);
    }

    public Player getPlayer() {
        return player;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public Level getLevel() {
        return level;
    }

    public void setGhosts(){
        for (int i = 0; i < ghostNames.length; i++) {
            this.ghosts[i] = new Ghost(ghostNames[i], player);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        level.draw(g);

        player.draw(g);

        drawGhosts(g);
//        g.setColor(new Color(255, 244, 0));
//        g.fillRect((int) player.getX() + 5, (int) player.getY() + 5, player.getSIZE(), player.getSIZE());
    }

    public void drawGhosts(Graphics g){
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].draw(g);
        }
    }

    public void updateMovements(){
        player.updateMovement();
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].updateMovement();
        }
    }

}
