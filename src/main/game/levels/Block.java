package main.game.levels;

public class Block {

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean isWall;

    public Block(int x1, int x2, int y1, int y2, boolean isWall){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.isWall = isWall;
    }

    public boolean isInside(int x, int y){
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2){
            return isWall;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Block{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 + "\n" +
                ", y2=" + y2 +
                ", isWall=" + isWall +
                "}\n";
    }
}
