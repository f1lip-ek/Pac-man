package main.game.player;
import main.game.GamePanel;
import main.game.levels.Level;

import java.awt.*;

public class Player{

    private Movement movement = Movement.UP;
    private boolean dead = false;

    private int lives = 3;
    private final int SIZE = 30;

    private float speed = 1f;
    private float x = 0;
    private float y = 0;

    private Rectangle hitbox;
    private Level level;

    public Player(){
        setHitbox();
    }

    public void death(){
        if (lives > 0){
            lives--;
        }else {
            dead = true;
        }
    }

    public void setLevel(Level level){
        this.level = level;
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
        if ((x + num) > 0 && (x + num) < GamePanel.PANEL_WIDTH - hitbox.width) {
            this.x += num;
        }
    }

    public void setDefaultPositionX(float num){
        this.x = num;
        updateHitBox();
    }

    public void setY(float num) {
        if ((y + num) < GamePanel.PANEL_HEIGHT - hitbox.height && (y + num) > 0) {
            this.y += num;
        }
    }
    public void setDefaultPositionY(float num){
        this.y = num;
        updateHitBox();
    }

    public void updateMovement(){
        switch (movement) {
            case Movement.UP -> {
                if (!level.isWall(hitbox.x, (int) (hitbox.y - speed)) &&
                    !level.isWall(hitbox.x + hitbox.width - 1, (int) (hitbox.y - speed))){
                    setY(-speed);
                }
            }
            case Movement.DOWN -> {
                if (!level.isWall(hitbox.x, (int) (hitbox.y + hitbox.height - 1 + speed)) &&
                    !level.isWall(hitbox.x + hitbox.width - 1, (int) (hitbox.y + hitbox.height - 1 + speed))){
                    setY(speed);
                }
            }
            case Movement.LEFT -> {
                if (!level.isWall((int) (hitbox.x - speed), hitbox.y) &&
                    !level.isWall((int) (hitbox.x - speed), hitbox.y + hitbox.height - 1)){
                    setX(-speed);
                }
            }
            case Movement.RIGHT -> {
                if (!level.isWall((int) (hitbox.x + hitbox.width - 1 + speed), hitbox.y) &&
                    !level.isWall((int) (hitbox.x + hitbox.width - 1 + speed), hitbox.y + hitbox.height - 1)){
                    setX(speed);
                }
            }
        }
        updateHitBox();
    }

    public void setHitbox(){
        hitbox = new Rectangle((int)x, (int)y, GamePanel.RECT_SIZE-1, GamePanel.RECT_SIZE-1);
    }
    public void updateHitBox(){
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }
}
