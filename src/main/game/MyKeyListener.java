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
            case KeyEvent.VK_W, KeyEvent.VK_UP -> player.setNextMovement(Movement.UP);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setNextMovement(Movement.DOWN);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setNextMovement(Movement.LEFT);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setNextMovement(Movement.RIGHT);
        }
        System.out.println(player.getLastMovement());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
