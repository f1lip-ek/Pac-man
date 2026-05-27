package main.game.entities;

import main.game.StaticThings;
import main.game.levels.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents a ghost of the game
 */
public class Ghost {

    private Movement lastMovement = Movement.NONE;

    private final float speed = 1f;
    private float x = 0;
    private float y = 0;

    private float defaultX = 0;
    private float defaultY = 0;

    private boolean wasHaunted = false;

    private final String name;

    private final BufferedImage[] img = new BufferedImage[5];

    private Rectangle hitbox;
    private Level level;
    private final Player player;
    private final Random rd;

    public Ghost(String name, Player player){
        setHitbox();
        this.name = name;
        this.player = player;
        this.rd = new Random();
        setImgs();
    }

    public void setLevel(Level level){
        this.level = level;
    }

    /**
     * Method that loads all the images of the ghosts
     */
    private void setImgs(){
        String[] arr = {"up", "down", "left", "right"};
        for (int i = 0; i < arr.length; i++) {
            img[i] = StaticThings.getImage("/entities/" + name + "/" + name + "_" + arr[i] + ".png");
        }
        img[4] = StaticThings.getImage("/entities/vulnerable/vulnerable.png");
    }

    /**
     * Method that sets the ghost to the default position
     */
    public void setDefaultXY() {
        this.x = this.defaultX;
        this.y = this.defaultY;
        updateHitBox();
        updateMovement();
    }

    public void setWasHaunted(boolean wasHaunted) {
        this.wasHaunted = wasHaunted;
    }
    public boolean getWasHaunted() {
        return wasHaunted;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    /**
     * Method that changes the x position of the ghost
     * @param num number of pixels to move
     */
    public void setX(float num) {
        if ((x + num) > 0 && (x + num) < StaticThings.PANEL_WIDTH - hitbox.width) {
            this.x += num;
        }
    }

    /**
     * Method that changes the y position of the ghost
     * @param num number of pixels to move
     */
    public void setY(float num) {
        if ((y + num) < StaticThings.PANEL_HEIGHT - hitbox.height && (y + num) > 0) {
            this.y += num;
        }
    }

    /**
     * Method that sets the default position of the ghost
     * @param x position in the map
     * @param y position in the map
     */
    public void setDefaultPosition(float x, float y){
        this.x = x;
        this.y = y;
        this.defaultX = x;
        this.defaultY = y;
        updateHitBox();
    }

    /**
     * Method that updates the movement of the ghost
     */
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

    /**
     * Method that generates a random movement for the ghost
     */
    public void setLastMovement(){
        ArrayList<Movement> list = StaticThings.getMovements(level, hitbox, speed, lastMovement);


        int random = rd.nextInt(list.size());
//        System.out.println(random);
        if (list.size() != 1) {
            lastMovement = list.get(random);
        }
    }

    /**
     * Method that draws the ghost
     * @param g Graphics object from the panel
     */
    public void draw(Graphics g){
        if (!wasHaunted && player.isHunting()) {
            g.drawImage(img[4], (int) x + 5, (int) y + 5, null);
        }else if (!player.isHunting() || wasHaunted) {
            switch (lastMovement) {
                case Movement.UP -> g.drawImage(img[0], (int) x + 5, (int) y + 5, null);
                case Movement.DOWN -> g.drawImage(img[1], (int) x + 5, (int) y + 5, null);
                case Movement.LEFT -> g.drawImage(img[2], (int) x + 5, (int) y + 5, null);
                case Movement.RIGHT -> g.drawImage(img[3], (int) x + 5, (int) y + 5, null);
            }
        }
//        g.setColor(Color.WHITE);
//        g.drawRect((int)x + 5, (int)y + 5, 30, 30);
    }

    /**
     * Method that sets the hitbox of the ghost
     */
    public void setHitbox(){
        hitbox = new Rectangle((int)this.x, (int)this.y, StaticThings.RECT_SIZE, StaticThings.RECT_SIZE);
    }

    /**
     * Method that updates the hitbox of the ghost
     */
    public void updateHitBox(){
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }

}
