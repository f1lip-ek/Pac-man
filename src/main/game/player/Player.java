package main.game.player;
import main.game.GamePanel;
import main.game.StaticMethods;
import main.game.levels.Level;

import java.awt.*;

public class Player{

    private Movement lastMovement = Movement.UP;
    private Movement nextMovement = Movement.UP;
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

    public Movement getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(){
        switch (nextMovement) {
            case Movement.UP -> {
                if (!level.isWall(hitbox.x, (int) (hitbox.y - speed)) &&
                        !level.isWall(hitbox.x + hitbox.width - 1, (int) (hitbox.y - speed))){
                    lastMovement = nextMovement;
                }
            }
            case Movement.DOWN -> {
                if (!level.isWall(hitbox.x, (int) (hitbox.y + hitbox.height - 1 + speed)) &&
                        !level.isWall(hitbox.x + hitbox.width - 1, (int) (hitbox.y + hitbox.height - 1 + speed))) {
                    lastMovement = nextMovement;
                }
            }
            case Movement.LEFT -> {
                if (!level.isWall((int) (hitbox.x - speed), hitbox.y) &&
                        !level.isWall((int) (hitbox.x - speed), hitbox.y + hitbox.height - 1)){
                    lastMovement = nextMovement;
                }
            }
            case Movement.RIGHT -> {
                if (!level.isWall((int) (hitbox.x + hitbox.width - 1 + speed), hitbox.y) &&
                        !level.isWall((int) (hitbox.x + hitbox.width - 1 + speed), hitbox.y + hitbox.height - 1)){
                    lastMovement = nextMovement;
                }
            }
        }
    }

    public void setNextMovement(Movement nextMovement){
        this.nextMovement = nextMovement;
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

        setLastMovement();

        switch (lastMovement) {
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

    public void draw(Graphics g){
        switch (lastMovement) {
            case Movement.UP -> g.drawImage(StaticMethods.getImage("/entities/pacman/pacman_up.png"), (int) x+5, (int) y+5, null);
            case Movement.DOWN -> g.drawImage(StaticMethods.getImage("/entities/pacman/pacman_down.png"), (int) x+5, (int) y+5, null);
            case Movement.LEFT -> g.drawImage(StaticMethods.getImage("/entities/pacman/pacman_left.png"), (int) x+5, (int) y+5, null);
            case Movement.RIGHT -> g.drawImage(StaticMethods.getImage("/entities/pacman/pacman_right.png"), (int) x+5, (int) y+5, null);
        }
    }

    public void setHitbox(){
        hitbox = new Rectangle((int)x, (int)y, GamePanel.RECT_SIZE, GamePanel.RECT_SIZE);
    }
    public void updateHitBox(){
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }
}
