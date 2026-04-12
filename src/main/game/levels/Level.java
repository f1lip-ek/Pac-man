package main.game.levels;

import main.game.GamePanel;
import main.game.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Level {

    private BufferedImage image;
    private Player player;

    private int[][] imgArray;

    private int width;
    private int height;

    public Level(Player player){
        this.image = getImage();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.imgArray = new int[height][width];
        this.player = player;
        setImgArray();
    }

    public void setImgArray(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(image.getRGB(j, i) == new Color(255, 0, 0).getRGB()){
                    imgArray[i][j] = 1;
                }else if (image.getRGB(j, i) == new Color(0, 0, 0).getRGB()){
                    imgArray[i][j] = 0;
                } else if (image.getRGB(j, i) == new Color(255, 244, 0).getRGB()) {
                    imgArray[i][j] = 2;
                    player.setX(j * GamePanel.RECT_SIZE + 1);
                    player.setY(i * GamePanel.RECT_SIZE + 1);
                }
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(imgArray[i][j] == 1){
                    g.fillRect(j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, GamePanel.RECT_SIZE, GamePanel.RECT_SIZE);
                }else if (imgArray[i][j] == 0){
                    g.setColor(Color.BLACK);
                    g.fillRect(j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, GamePanel.RECT_SIZE, GamePanel.RECT_SIZE);
                    g.setColor(Color.BLUE);
                }
            }
        }
    }

    public BufferedImage getImage(){
        BufferedImage img = null;
        try(InputStream is = getClass().getResourceAsStream("/test.png")){
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        return img;
    }
}
