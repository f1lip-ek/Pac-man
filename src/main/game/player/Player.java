package main.game.player;
import main.game.GamePanel;
import main.game.levels.Level;

public class Player {

    private Movement movement = Movement.UP;
    private boolean dead = false;

    private int lives = 3;
    private final int SIZE = 38;

    private float speed = 1.5f;
    private float x = 0;
    private float y = 0;

    public Player(){

    }

    public void death(){
        if (lives > 0){
            lives--;
        }else {
            dead = true;
        }
    }

    public boolean isDead(){
        return dead;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement){
        this.movement = movement;
    }

    public float getSpeed(){
        return speed;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getSIZE(){
        return SIZE;
    }

    public void setX(float num) {
        if ((x + num) > 0 && (x + num) < GamePanel.PANEL_WIDTH - 40) {
            this.x += num;
        }
    }

    public void setY(float num) {
        if ((y + num) < GamePanel.PANEL_HEIGHT - 40 && (y + num) > 0) {
            this.y += num;
        }
    }
}
