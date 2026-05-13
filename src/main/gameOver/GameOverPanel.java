package main.gameOver;

import main.game.StaticThings;
import main.start.StartFrame;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    private JLabel label;
    private JLabel label2;

    private JButton playAgainButton;
    private JButton exitButton;
    private JButton goMainMenuButton;

    private GameOverFrame gameOverFrame;

    private int score;
    private int ending;

    private String[] endingText;

    public GameOverPanel(GameOverFrame gameOverFrame){
        this.endingText = new String[]{"Game", "Over", "You Win!"};
        this.gameOverFrame = gameOverFrame;
        this.label = new JLabel();
        this.label2 = new JLabel();
        setEndingText();

        this.playAgainButton = new JButton("Play Again");
        this.exitButton = new JButton("Exit");
        this.goMainMenuButton = new JButton("Go to Main Menu");

        this.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLabels();
        this.setButtons();
    }

    public void setEndingText(){
        if (ending == 2) { // lose
            this.label.setText(endingText[0]);
            this.label2.setText(endingText[1]);
        } else if (ending == 1) { // win
            this.label.setText(endingText[2]);
            this.label2.setText(Integer.toString(score));
        }
    }

    public void setLabels(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, (StaticThings.PANEL_HEIGHT/5)*4));
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.BLACK);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(Color.BLACK);

        JPanel labelPanel2 = new JPanel();
        labelPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel2.setBackground(Color.BLACK);

        this.label.setForeground(Color.WHITE);
        this.label2.setForeground(Color.WHITE);
        this.label.setFont(new Font("Arial", Font.BOLD, 100));
        this.label2.setFont(new Font("Arial", Font.BOLD, 100));
        labelPanel.add(label);
        labelPanel2.add(label2);


        panel.add(labelPanel);
        panel.add(labelPanel2);

        this.add(panel);
    }

    public void setNums(int score, int ending){
        this.score = score;
        this.ending = ending;
    }

    public void setButtons(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(StaticThings.PANEL_WIDTH, StaticThings.PANEL_HEIGHT/5));
        panel.setLayout(new GridLayout(1, 3, 50, 0));
        panel.setBackground(Color.BLACK);

        this.playAgainButton.addActionListener(_ -> {
            this.gameOverFrame.dispose();
            StartFrame.playAgain();
        });
        this.goMainMenuButton.addActionListener(_ -> {
            this.gameOverFrame.dispose();
            StartFrame.view();
        });
        this.exitButton.addActionListener(_ -> {
            System.exit(0);
        });

        panel.add(playAgainButton);
        panel.add(goMainMenuButton);
        panel.add(exitButton);
        this.add(panel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
