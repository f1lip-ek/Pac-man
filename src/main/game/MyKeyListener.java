package main.game;

import main.game.entities.Movement;
import main.game.entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that represents the key listener of the game
 */
public class MyKeyListener implements KeyListener {

    private Player player;

    public MyKeyListener(Player player){
        this.player = player;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Method that sets the next movement of the player depending on the key pressed
     * @param e the event to be processed
     */
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
