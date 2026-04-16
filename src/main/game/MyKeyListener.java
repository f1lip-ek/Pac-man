package main.game;

import main.game.player.Movement;
import main.game.player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    private Player player;

    public MyKeyListener(Player player){
        this.player = player;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> player.setMovement(Movement.UP);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setMovement(Movement.DOWN);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setMovement(Movement.LEFT);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setMovement(Movement.RIGHT);
        }
        System.out.println(player.getMovement());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
