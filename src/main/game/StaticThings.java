package main.game;

import main.game.entities.Movement;
import main.game.levels.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StaticThings {

    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_WIDTH = 800;
    public static final int RECT_SIZE = 40;

    public static BufferedImage getImage(String path){
        BufferedImage img = null;
        try(InputStream is = StaticThings.class.getResourceAsStream(path)){
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        return img;
    }

    public static ArrayList<Movement> getMovements(Level level, Rectangle hitbox, float speed, Movement lastMovement){
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
