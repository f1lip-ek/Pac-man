package main.game;

import main.game.player.Movement;
import main.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    private Player player;

    private MyKeyListener keyListener;

    public GamePanel(){
        player = new Player();
        this.keyListener = new MyKeyListener(this);

        this.setFocusable(true);

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyListener);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);

        switch (player.getMovement()){
            case Movement.UP -> player.setY(-player.getSpeed());
            case Movement.DOWN -> player.setY(player.getSpeed());
            case Movement.LEFT -> player.setX(-player.getSpeed());
            case Movement.RIGHT -> player.setX(player.getSpeed());
        }


        g.fillRect((int) player.getX(), (int) player.getY(), 50, 50);
        repaint();
    }
}
