package main.game.entities;

import main.game.GamePanel;
import main.game.StaticMethods;
import main.game.levels.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost {

    private Movement lastMovement = Movement.DOWN;
    private Movement nextMovement = Movement.DOWN;

    private final int SIZE = 30;

    private float speed = 1f;
    private float x = 0;
    private float y = 0;

    private String name;

    private Rectangle hitbox;
    private Level level;
    private Random rd;

    public Ghost(String name){
        setHitbox();
        this.name = name;
        this.rd = new Random();
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public Rectangle getHitbox(){
        return hitbox;
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

    public void setLastMovement(){
        ArrayList<Movement> list = getPossibleMovements();


        int random = rd.nextInt(list.size());
//        System.out.println(random);
        if (list.size() != 1) {
            lastMovement = list.get(random);
        }
    }

    public void draw(Graphics g){
        g.drawImage(StaticMethods.getImage("/entities/" + name + ".png"), (int)x + 5, (int)y + 5, null);
    }

    public void setHitbox(){
        hitbox = new Rectangle((int)this.x, (int)this.y, GamePanel.RECT_SIZE, GamePanel.RECT_SIZE);
    }
    public void updateHitBox(){
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }

    public ArrayList<Movement> getPossibleMovements(){
        ArrayList<Movement> list = new ArrayList<>();

        if (!level.isWall(hitbox.x, (int) (hitbox.y - speed)) &&
                !level.isWall(hitbox.x + hitbox.width - 1, (int) (hitbox.y - speed))){
            list.add(Movement.UP);
        }
        if (!level.isWall(hitbox.x, (int) (hitbox.y + hitbox.height - 1 + speed)) &&
                !level.isWall(hitbox.x + hitbox.width - 1, (int) (hitbox.y + hitbox.height - 1 + speed))) {
            list.add(Movement.DOWN);
        }
        if (!level.isWall((int) (hitbox.x - speed), hitbox.y) &&
                !level.isWall((int) (hitbox.x - speed), hitbox.y + hitbox.height - 1)){
            list.add(Movement.LEFT);
        }
        if (!level.isWall((int) (hitbox.x + hitbox.width - 1 + speed), hitbox.y) &&
                !level.isWall((int) (hitbox.x + hitbox.width - 1 + speed), hitbox.y + hitbox.height - 1)){
            list.add(Movement.RIGHT);
        }

        switch (lastMovement) {
            case Movement.UP -> list.remove(Movement.UP);
            case Movement.DOWN -> list.remove(Movement.DOWN);
            case Movement.LEFT -> list.remove(Movement.LEFT);
            case Movement.RIGHT -> list.remove(Movement.RIGHT);
        }
        return list;
    }

}
