package main.game.player;
import main.game.GamePanel;

public class Player {

    private Movement movement = Movement.UP;
    private boolean dead = false;

    private int lives = 3;

    private int speed = 1;
    private int x = 10;
    private int y = 10;

    public Player(){

    }

    public void death(){
        if (lives > 0){
            lives--;
        }else {
            dead = true;
        }
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement){
        this.movement = movement;
    }

    public int getSpeed(){
        return speed;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int num) {
        if ((x + num) > 0 && (x + num) < GamePanel.WIDTH - 50) {
            this.x += num;
        }
    }

    public void setY(int num) {
        if ((y + num) < GamePanel.HEIGHT - 50 && (y + num) > 0) {
            this.y += num;
        }
    }
}
