package main.game;

public class GameLoop implements Runnable{

    private GamePanel panel;

    private final int FPS = 120;

    public GameLoop(GamePanel panel){
        this.panel = panel;
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
            }


            if (System.currentTimeMillis() - timer >= 1000){
                timer = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }

    }
}
