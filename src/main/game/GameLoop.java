package main.game;

public class GameLoop implements Runnable{

    private GamePanel panel;
    private GameFrame gameFrame;

    private final int FPS = 120;

    public GameLoop(GamePanel panel, GameFrame gameFrame){
        this.panel = panel;
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {

        double timePerTick = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();

        int frames = 0;
        long timer = System.currentTimeMillis();

        while(!panel.getPlayer().isDead()){

            if (System.nanoTime() - lastTime >= timePerTick){
                panel.repaint();
                lastTime = System.nanoTime();
                frames++;
                collide();
                panel.getPlayer().death();
            }


            if (System.currentTimeMillis() - timer >= 1000){
                timer = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }


        }

        System.err.println("Game Over");
        gameFrame.viewEnd();
    }

    public void collide(){
        for (int i = 0; i < panel.getGhosts().length; i++) {
            if (panel.getPlayer().getHitbox().intersects(panel.getGhosts()[i].getHitbox())){
                panel.getPlayer().decreaseLives();
                System.err.println(panel.getPlayer().getLives());
                panel.getLevel().setImgArray();
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
