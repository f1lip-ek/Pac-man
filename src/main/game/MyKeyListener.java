package main.game;

import main.game.player.Movement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    private GamePanel p;

    public MyKeyListener(GamePanel p){
        this.p = p;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> p.getPlayer().setMovement(Movement.UP);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> p.getPlayer().setMovement(Movement.DOWN);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> p.getPlayer().setMovement(Movement.LEFT);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> p.getPlayer().setMovement(Movement.RIGHT);
        }
        System.out.println(p.getPlayer().getMovement());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
