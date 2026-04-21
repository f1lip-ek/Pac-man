package main.game;

import main.game.levels.Level;
import main.game.player.Ghost;
import main.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_WIDTH = 800;
    public static final int RECT_SIZE = 40;

    private Player player;

    private Ghost[] ghosts;
    private String[] ghostNames = {"blinky", "pinky", "inky", "clyde"};

    private MyKeyListener keyListener;

    private Level level;

    public GamePanel(){
        player = new Player();
        this.ghosts = new Ghost[4];
        setGhosts();
        this.keyListener = new MyKeyListener(player);
        this.level = new Level(player, ghosts);

        player.setLevel(level);
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].setLevel(level);
        }

        this.setFocusable(true);

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.addKeyListener(keyListener);

        level.setImgArray();
        System.out.println(level);
    }

    public Player getPlayer() {
        return player;
    }

    public void setGhosts(){
        for (int i = 0; i < ghostNames.length; i++) {
            this.ghosts[i] = new Ghost(ghostNames[i]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        level.draw(g);

        player.updateMovement();

        player.draw(g);

        updateGhosts(g);
//        g.setColor(new Color(255, 244, 0));
//        g.fillRect((int) player.getX() + 5, (int) player.getY() + 5, player.getSIZE(), player.getSIZE());
    }

    public void updateGhosts(Graphics g){
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].draw(g);
            ghosts[i].updateMovement();
        }
    }
}
