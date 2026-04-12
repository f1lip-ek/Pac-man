package main.game;

import main.game.levels.Level;
import main.game.player.Movement;
import main.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_WIDTH = 800;
    public static final int RECT_SIZE = 40;

    private Player player;

    private MyKeyListener keyListener;

    private Level level;

    public GamePanel(){
        player = new Player();
        this.keyListener = new MyKeyListener(this);
        this.level = new Level(player);

        this.setFocusable(true);

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.addKeyListener(keyListener);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        level.draw(g);

        g.setColor(new Color(255, 244, 0));

        switch (player.getMovement()){
            case Movement.UP -> player.setY(-player.getSpeed());
            case Movement.DOWN -> player.setY(player.getSpeed());
            case Movement.LEFT -> player.setX(-player.getSpeed());
            case Movement.RIGHT -> player.setX(player.getSpeed());
        }

        g.fillRect((int) player.getX(), (int) player.getY(), player.getSIZE(), player.getSIZE());
    }
}
