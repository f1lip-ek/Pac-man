package main.game.levels;

import main.game.GamePanel;

public class CollisionMap {

    private Block[][] collisionMap;
    private int[][] map;
    private int width;
    private int height;


    public CollisionMap(int[][] map, int width, int height){
        this.map = map;
        this.width = width;
        this.height = height;
        this.collisionMap = new Block[height][width];
        System.out.println("height: " + height + " width: " + width);
        setCollisionMap();
    }

    public void setCollisionMap(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1){
                    collisionMap[i][j] = new Block(j* GamePanel.RECT_SIZE, (j*GamePanel.RECT_SIZE) + GamePanel.RECT_SIZE,
                            i*GamePanel.RECT_SIZE, (i*GamePanel.RECT_SIZE) + GamePanel.RECT_SIZE, true);
                }else if (map[i][j] == 0 || map[i][j] == 2){
                    collisionMap[i][j] = new Block(j* GamePanel.RECT_SIZE, (j*GamePanel.RECT_SIZE) + GamePanel.RECT_SIZE,
                            i*GamePanel.RECT_SIZE, (i*GamePanel.RECT_SIZE) + GamePanel.RECT_SIZE, false);
                }else{
                    System.err.println("Invalid map value: " + map[i][j]);
                }
            }
        }
    }

    public Block getBlock(int x, int y){
        return collisionMap[y/GamePanel.RECT_SIZE][x/GamePanel.RECT_SIZE];
    }

    @Override
    public String toString(){
        String text = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                text += collisionMap[i][j] + " ";
            }
            text += "\n";
        }
        return text;
    }

}
