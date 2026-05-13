package main.game.entities;
import main.game.GamePanel;
import main.game.StaticThings;
import main.game.levels.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player{

    private Movement lastMovement = Movement.UP;
    private Movement nextMovement = Movement.UP;
    private boolean dead = false;

    private BufferedImage[] img = new BufferedImage[4];

    private int lives = 3;
    private final int SIZE = 30;

    private float speed = 1f;
    private float x = 0;
    private float y = 0;

    private float defaultX = 0;
    private float defaultY = 0;

    private int score = 0;

    private boolean isHunting = false;

    private Rectangle hitbox;
    private Level level;

    public Player(){
        setHitbox();
        setImgs();
    }

    private void setImgs(){
        String[] arr = {"up", "down", "left", "right"};
        for (int i = 0; i < img.length; i++) {
            img[i] = StaticThings.getImage("/entities/pacman/pacman_" + arr[i] + ".png");
        }
    }

    public void increaseScore(){
        score += 10;
    }

    public void bigIncreaseScore(){
        this.score += 50;
        this.isHunting = true;
    }

    public void ghostsIncreaseScore(){
        this.score += 200;
    }

    public int getScore(){
        return score;
    }

    public void decreaseLives(){
        if (lives > 0) lives--;
    }

    public void death(){
        if (lives == 0) dead = true;
    }

    public int getLives() {
        return lives;
    }

    public Rectangle getHitbox() {
        return hitbox;
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

    public boolean isHunting() {
        return isHunting;
    }

    public void setHunting(boolean hunting) {
        isHunting = hunting;
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

    public void setDefaultPosition(float x, float y){
        this.x = x;
        this.y = y;
        this.defaultX = x;
        this.defaultY = y;
        updateHitBox();
    }

    public float getDefaultX() {
        return defaultX;
    }

    public float getDefaultY() {
        return defaultY;
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
            case Movement.UP -> g.drawImage(img[0], (int) x+5, (int) y+5, null);
            case Movement.DOWN -> g.drawImage(img[1], (int) x+5, (int) y+5, null);
            case Movement.LEFT -> g.drawImage(img[2], (int) x+5, (int) y+5, null);
            case Movement.RIGHT -> g.drawImage(img[3], (int) x+5, (int) y+5, null);
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
