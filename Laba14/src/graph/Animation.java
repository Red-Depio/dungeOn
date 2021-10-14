package graph;

import javax.swing.*;
import java.awt.*;

public class Animation {
    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 15;
    private int oneY = 15;
    private static final long serialVersionUID = 1L;
    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;

    void go() {
        frame = new JFrame("Анімація");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setLocation(390, 390);
        moveIt();
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(new Color(138, 43, 226));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(new Color(138, 43, 226));
            g.fillRect(10, 10, this.getWidth()-6, this.getHeight()-6);
            g.setColor(new Color(138, 43, 226));
            g.fillRect(0, 0, this.getWidth()-12, this.getHeight()-12);
            g.setColor(Color.WHITE);
            g.fillRect(oneX, oneY, 30, 30);
        }
    }

    private void moveIt() {
        while(true){
            if(oneX >= 360){
                right = false;
                left = true;
            }

            if(oneX <= 1){
                right = true;
                left = false;
            }

            if(oneY >= 335){
                up = true;
                down = false;
            }

            if(oneY <= 1){
                up = false;
                down = true;
            }

            if(up) oneY--;

            if(down) oneY++;

            if(left) oneX--;

            if(right) oneX++;

            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
    public static void main(String[] args) {
        new Animation().go();
    }
}