package main.game;

import main.Main;
import main.game.entities.Movement;
import main.game.levels.Level;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class that contains all the static methods and variables of the game
 */
public class StaticThings {

    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_WIDTH = 800;
    public static final int RECT_SIZE = 40;

    /**
     * Method that loads an image from the resources folder
     * @param path to the image
     * @return BufferedImage of the loaded image
     */
    public static BufferedImage getImage(String path){
        BufferedImage img = null;
        try(InputStream is = StaticThings.class.getResourceAsStream(path)){
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        return img;
    }

    /**
     * Method that returns all the possible movements of the player
     * @param level in which the entity is
     * @param hitbox of the entity
     * @param speed of the entity
     * @param lastMovement that the entity did
     * @return ArrayList of possible movements
     */
    public static ArrayList<Movement> getMovements(Level level, Rectangle hitbox, float speed, Movement lastMovement){
        final ArrayList<Movement> list = new ArrayList<>();

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

    /**
     * Method that plays a sound
     * @param path to the sound
     * @author Matěj Chaloupka
     */
    public static Clip playSound(String path){
        try{
            final InputStream fis = Main.class.getResourceAsStream(path);
            assert fis != null;
            final InputStream bufferedIn = new BufferedInputStream(fis);
            final AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            final Clip clip = AudioSystem.getClip();
            clip.open(ais);
            return clip;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that edits the button
     * @param button to edit
     * @param bgColor of the button
     * @param fontColor of the button font
     * @author Michaela Meitnarova
     */
    public static void editButton(JButton button, Color bgColor, Color fontColor){
        button.setBackground(bgColor);
        button.setForeground(fontColor);
        button.setFont(new Font("Arial", Font.BOLD, 25));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

    }
}
