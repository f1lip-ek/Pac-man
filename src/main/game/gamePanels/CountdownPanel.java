package main.game.gamePanels;

import main.game.StaticThings;

import javax.swing.*;
import java.awt.*;

public class CountdownPanel extends JPanel {

    private int countdown = 3;

    private final JLabel label;

    public CountdownPanel(){
        this.label = new JLabel();
        this.label.setText(String.valueOf(countdown));
        this.label.setFont(new Font("Arial", Font.BOLD, 50));
        this.label.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT + StaticThings.RECT_SIZE));
        this.label.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT + StaticThings.RECT_SIZE));
        this.add(label);
        this.setVisible(true);
    }

    public void countdown(){
        System.out.println("Countdown started");
        while(countdown > 0){
            System.out.println(countdown);
            countdown--;
            try {
                Thread.sleep(1000);
                repaint();
                revalidate();

                this.label.setText(String.valueOf(countdown));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
