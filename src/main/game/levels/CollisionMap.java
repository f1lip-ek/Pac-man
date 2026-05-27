package main.game.levels;

import main.game.StaticThings;

/**
 * Class that represents the collision map of the level
 */
public class CollisionMap {

    private final Block[][] collisionMap;
    private final int[][] map;
    private final int width;
    private final int height;


    public CollisionMap(int[][] map, int width, int height){
        this.map = map;
        this.width = width;
        this.height = height;
        this.collisionMap = new Block[height][width];
        System.out.println("height: " + height + " width: " + width);
        setCollisionMap();
    }

    /**
     * Method that sets the collision map
     */
    public void setCollisionMap(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1){
                    collisionMap[i][j] = new Block(j* StaticThings.RECT_SIZE, (j*StaticThings.RECT_SIZE) + StaticThings.RECT_SIZE,
                            i*StaticThings.RECT_SIZE, (i*StaticThings.RECT_SIZE) + StaticThings.RECT_SIZE, true);
                }else if (map[i][j] == 0 || map[i][j] == 2 || map[i][j] == 3){
                    collisionMap[i][j] = new Block(j* StaticThings.RECT_SIZE, (j*StaticThings.RECT_SIZE) + StaticThings.RECT_SIZE,
                            i*StaticThings.RECT_SIZE, (i*StaticThings.RECT_SIZE) + StaticThings.RECT_SIZE, false);
                }else{
                    System.err.println("Invalid map value: " + map[i][j]);
                }
            }
        }
    }

    /**
     * Method that returns the block at the given coordinates
     * @param x position in the map
     * @param y position in the map
     * @return Block object at the given coordinates
     */
    public Block getBlock(int x, int y){
        return collisionMap[y/StaticThings.RECT_SIZE][x/StaticThings.RECT_SIZE];
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
