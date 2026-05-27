package main.game.gamePanels;

import main.game.MyKeyListener;
import main.game.StaticThings;
import main.game.entities.Ghost;
import main.game.entities.Player;
import main.game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents the main game panel of the game
 */
public class MainGamePanel extends JPanel {

    private final Player player;

    private final Ghost[] ghosts;
    private final String[] ghostNames = {"blinky", "pinky", "inky", "clyde"};

    private final MyKeyListener keyListener;
    private Level level;

    public MainGamePanel(BufferedImage mapImg){
        this.player = new Player();
        this.ghosts = new Ghost[4];
        setGhosts();
        this.keyListener = new MyKeyListener(player);
        this.level = new Level(player, ghosts, mapImg);

        player.setLevel(level);
        setGhostsLevel(level);

        this.setFocusable(true);

        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT));
        this.addKeyListener(keyListener);

        this.setOpaque(true);

        level.setImgArray();
        System.out.println(level);
    }

    public void setLevel(int mapIndex){
        this.level = new Level(player, ghosts, StaticThings.getImage("/maps/map_0" + mapIndex + ".png"));
        this.level.setImgArray();
        player.setLevel(level);
        setGhostsLevel(level);
    }

    public void setGhostsLevel(Level level){
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].setLevel(level);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public Level getLevel() {
        return level;
    }

    /**
     * Method that creates the ghosts objects
     */
    public void setGhosts(){
        for (int i = 0; i < ghostNames.length; i++) {
            this.ghosts[i] = new Ghost(ghostNames[i], player);
        }
    }

    /**
     * Method that draws the game
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        level.draw(g);

        player.draw(g);

        drawGhosts(g);
//        g.setColor(new Color(255, 244, 0));
//        g.fillRect((int) player.getX() + 5, (int) player.getY() + 5, player.getSIZE(), player.getSIZE());
    }

    /**
     * Method that draws the ghosts
     * @param g Graphics object from the panel
     */
    public void drawGhosts(Graphics g){
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].draw(g);
        }
    }

    /**
     * Method that updates the movements of the player and the ghosts
     */
    public void updateMovements(){
        player.updateMovement();
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].updateMovement();
        }
    }

}
