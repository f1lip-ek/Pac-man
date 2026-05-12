package main.game;

public class GameLoop implements Runnable{

    private GamePanel panel;
    private GameFrame gameFrame;

    private final int FPS = 120;
    private final int TIMER_HUNTING = 20*FPS;

    public GameLoop(GamePanel panel, GameFrame gameFrame){
        this.panel = panel;
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {

        panel.repaint();

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        double timePerTick = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();

        int frames = 0;
        long timer = System.currentTimeMillis();

        int counter = 0;
        while(!panel.getMainGamePanel().getPlayer().isDead() && !panel.getMainGamePanel().getLevel().wasPlayerEverywhere()){

            if (System.nanoTime() - lastTime >= timePerTick){
                panel.getMainGamePanel().updateMovements();
                panel.getMainGamePanel().getLevel().setWhereWasPlayer();
                panel.getMainGamePanel().repaint();

//                panel.getHealthPanel().setHealth(panel.getMainGamePanel().getPlayer().getLives());
//                panel.getHealthPanel().repaint();

                panel.getPointPanel().setPoints(panel.getMainGamePanel().getPlayer().getScore());
                panel.getPointPanel().repaint();

                lastTime = System.nanoTime();
                frames++;
                collide();
                panel.getMainGamePanel().getPlayer().death();
                if (panel.getMainGamePanel().getPlayer().isHunting() && counter < TIMER_HUNTING){
//                    System.out.println("Hunting");
                    counter++;
                } else if (counter >= TIMER_HUNTING) {
                    panel.getMainGamePanel().getPlayer().setHunting(false);
                    for (int i = 0; i < panel.getMainGamePanel().getGhosts().length; i++) {
                        panel.getMainGamePanel().getGhosts()[i].setWasHaunted(false);
                    }
                    counter = 0;
                } else {
//                    System.out.println("Not hunting");
                }
            }


            if (System.currentTimeMillis() - timer >= 1000){
                timer = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }


        }

        if (panel.getMainGamePanel().getPlayer().isDead()) {
            System.err.println("Game Over");
            gameFrame.viewEnd(panel.getMainGamePanel().getPlayer().getScore(), 2);
        } else {
            System.err.println("You Win");
            gameFrame.viewEnd(panel.getMainGamePanel().getPlayer().getScore(), 1);
        }
    }

    public void collide(){
        for (int i = 0; i < panel.getMainGamePanel().getGhosts().length; i++) {
            if (panel.getMainGamePanel().getPlayer().getHitbox().intersects(panel.getMainGamePanel().getGhosts()[i].getHitbox())){
                if (panel.getMainGamePanel().getPlayer().isHunting() && !panel.getMainGamePanel().getGhosts()[i].getWasHaunted()){
                    panel.getMainGamePanel().getPlayer().ghostsIncreaseScore();
                    try{
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    panel.getMainGamePanel().getGhosts()[i].setDefaultXY();
                    panel.getMainGamePanel().getGhosts()[i].setWasHaunted(true);
                } else {
                    panel.getMainGamePanel().getPlayer().decreaseLives();
                    panel.getHealthPanel().setHealth(panel.getMainGamePanel().getPlayer().getLives());
                    panel.getHealthPanel().repaint();
                    panel.getMainGamePanel().getPlayer().setHunting(false);
                    System.err.println(panel.getMainGamePanel().getPlayer().getLives());
                    panel.getMainGamePanel().getLevel().setImgArray();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                }
            }
        }
    }
}
