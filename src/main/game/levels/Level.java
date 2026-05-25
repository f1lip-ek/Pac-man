package main.game.levels;

import main.game.StaticThings;
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

    private static int numOfMaps = 1;

    private CollisionMap collisionMap;

    private BufferedImage[] textures;

    public Level(Player player, Ghost[] ghosts, BufferedImage mapImg){
        this.image = mapImg;
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
        this.textures[0] = StaticThings.getImage("/textures/way.png");
        this.textures[1] = StaticThings.getImage("/textures/smallPoint_way.png");
        this.textures[2] = StaticThings.getImage("/textures/bigPoint_way.png");
    }

    public void setWhereWasPlayer(){
        int x = (int) (player.getX()/StaticThings.RECT_SIZE);
        int y = (int) (player.getY()/StaticThings.RECT_SIZE);

        if (!whereWasPlayer[y][x]){
            whereWasPlayer[y][x] = true;
            if (getBigPoint(x, y)) {
                player.bigIncreaseScore();
                for (int i = 0; i < ghosts.length; i++) {
                    ghosts[i].setWasHaunted(false);
                }
            } else {
                player.increaseScore();
            }
        }
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
                    player.setDefaultPosition(j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE);
                } else if (image.getRGB(j, i) == new Color(255, 0, 0).getRGB()) {
                    imgArray[i][j] = 3;
                    ghosts[ghostIndex].setDefaultPosition(j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE);
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
                    case 1 -> g.fillRect(j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE, StaticThings.RECT_SIZE, StaticThings.RECT_SIZE);
                    case 0, 2, 3 -> {
//                        g.setColor(new Color(0, 0, 0));
//                        g.fillRect(j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE, StaticThings.RECT_SIZE, StaticThings.RECT_SIZE);
//                        g.setColor(new Color(0, 0, 255));

                        g.drawImage(textures[0], j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE, null);
                        if (!whereWasPlayer[i][j]) {
                            g.drawImage(textures[1], j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE, null);
                            if (getBigPoint(j, i)) {
                                g.drawImage(textures[2], j * StaticThings.RECT_SIZE, i * StaticThings.RECT_SIZE, null);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean getBigPoint(int x, int y){
        if ((imgArray[y][x-1] == 1 && imgArray[y-1][x] == 1 && imgArray[y][x+1] != 1 && imgArray[y+1][x] != 1) //left up
                || (imgArray[y][x+1] == 1 && imgArray[y-1][x] == 1 && imgArray[y][x-1] != 1 && imgArray[y+1][x] != 1)  //right up
                || (imgArray[y][x+1] == 1 && imgArray[y+1][x] == 1 && imgArray[y][x-1] != 1 && imgArray[y-1][x] != 1) //right down
                || (imgArray[y-1][x] != 1 && imgArray[y][x+1] != 1 && imgArray[y+1][x] == 1 && imgArray[y][x-1] == 1)) { //right down
            if (player.getDefaultX() != x * StaticThings.RECT_SIZE && player.getDefaultY() != y * StaticThings.RECT_SIZE){
                return true;
            }
        }
        return false;
    }

    public boolean isWall(int x, int y){
        return collisionMap.getBlock(x, y).isInside(x, y);
    }

    public boolean wasPlayerEverywhere(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (imgArray[i][j] != 1 &&!whereWasPlayer[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static String[] getImages(){
        boolean canGo = true;
        while(canGo){
            try{
                StaticThings.getImage("/maps/map_0" + numOfMaps + ".png");
                numOfMaps++;
            }catch (IllegalArgumentException e){
                canGo = false;
            }
        }
        numOfMaps--;
        System.out.println("Maps count: " + numOfMaps);
        String[] imgs = new String[numOfMaps];
        for (int i = 1; i <= numOfMaps; i++) {
            imgs[i-1] = "map_0" + i + ".png";
        }
        return imgs;
    }

    public static int getNumOfMaps() {
        return numOfMaps;
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
