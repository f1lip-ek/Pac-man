package main.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class StaticThings {

    public static BufferedImage getImage(String path){
        BufferedImage img = null;
        try(InputStream is = StaticThings.class.getResourceAsStream(path)){
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        return img;
    }
}
