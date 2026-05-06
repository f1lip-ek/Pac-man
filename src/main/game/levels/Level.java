package main.game.levels;

import main.game.GamePanel;
import main.game.StaticMethods;
import main.game.entities.Ghost;
import main.game.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level {

    private BufferedImage image;
    private Player player;
    private Ghost[] ghosts;

    private int[][] imgArray;
    private boolean[][] whereWasPlayer;

    private int width;
    private int height;

    private CollisionMap collisionMap;

    private BufferedImage[] textures;

    public Level(Player player, Ghost[] ghosts){
        this.image = StaticMethods.getImage("/maps/map_01.png");
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.imgArray = new int[height][width];
        this.whereWasPlayer = new boolean[height][width];
        this.player = player;
        this.ghosts = ghosts;
        this.textures = new BufferedImage[3];
        setTextures();
    }

    public void setTextures(){
        this.textures[0] = StaticMethods.getImage("/textures/way.png");
        this.textures[1] = StaticMethods.getImage("/textures/smallPoint_way.png");
        this.textures[2] = StaticMethods.getImage("/textures/bigPoint_way.png");
    }

    public void setWhereWasPlayer(){
        whereWasPlayer[(int) (player.getY()/GamePanel.RECT_SIZE)][(int) (player.getX()/GamePanel.RECT_SIZE)] = true;
    }

    public void setImgArray(){
        int ghostIndex = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(image.getRGB(j, i) == new Color(0, 0, 255).getRGB()){
                    imgArray[i][j] = 1;
                }else if (image.getRGB(j, i) == new Color(255, 255, 255).getRGB()){
                    imgArray[i][j] = 0;
                } else if (image.getRGB(j, i) == new Color(255, 244, 0).getRGB()) {
                    imgArray[i][j] = 2;
                    player.setDefaultPositionX(j * GamePanel.RECT_SIZE);
                    player.setDefaultPositionY(i * GamePanel.RECT_SIZE + 1);
                } else if (image.getRGB(j, i) == new Color(255, 0, 0).getRGB()) {
                    imgArray[i][j] = 3;
                    ghosts[ghostIndex].setDefaultPositionX(j * GamePanel.RECT_SIZE);
                    ghosts[ghostIndex].setDefaultPositionY(i * GamePanel.RECT_SIZE + 1);
                    ghostIndex++;
                }
            }
        }
        collisionMap = new CollisionMap(imgArray, width, height);
    }

    public void draw(Graphics g){
        g.setColor(new Color(0, 0, 255));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (imgArray[i][j]){
                    case 1 -> g.fillRect(j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, GamePanel.RECT_SIZE, GamePanel.RECT_SIZE);
                    case 0, 2, 3 -> {
//                        g.setColor(new Color(0, 0, 0));
//                        g.fillRect(j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, GamePanel.RECT_SIZE, GamePanel.RECT_SIZE);
//                        g.setColor(new Color(0, 0, 255));

                        g.drawImage(textures[0], j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, null);
                        if (!whereWasPlayer[i][j]) {
                            g.drawImage(textures[1], j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, null);
                            if ((imgArray[i][j-1] == 1 && imgArray[i-1][j] == 1 && imgArray[i][j+1] != 1 && imgArray[i+1][j] != 1) //left up
                                    || (imgArray[i][j+1] == 1 && imgArray[i-1][j] == 1 && imgArray[i][j-1] != 1 && imgArray[i+1][j] != 1)  //right up
                                    || (imgArray[i][j+1] == 1 && imgArray[i+1][j] == 1 && imgArray[i][j-1] != 1 && imgArray[i-1][j] != 1) //right down
                                    || (imgArray[i-1][j] != 1 && imgArray[i][j+1] != 1 && imgArray[i+1][j] == 1 && imgArray[i][j-1] == 1) ) { //right down
                                g.drawImage(textures[2], j * GamePanel.RECT_SIZE, i * GamePanel.RECT_SIZE, null);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isWall(int x, int y){
        return collisionMap.getBlock(x, y).isInside(x, y);
    }

    @Override
    public String toString(){
        String text = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                text += imgArray[i][j] + " ";
            }
            text += "\n";
        }
        return text;
    }
}
