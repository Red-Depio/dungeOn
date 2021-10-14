package graph;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TestGraph {
    private Polygon poly;
    private JFrame frame;
    private Shape sh;
   /* private URL url = TestWithFoto.class.getResource("/other/KAPTUHKA.jpg");
    private BufferedImage img = null;
    {
        try {
            img = ImageIO.read(url);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private JPanel panel = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d =(Graphics2D) g;
            Image scaledImg = img.getScaledInstance(getWidth(),
                    getHeight(),Image.SCALE_SMOOTH);
            g2d.drawImage(scaledImg, 0, 0, this);
        }
    };*/

    private void testShape() {
            Container c = frame.getContentPane();
            Graphics2D g = (Graphics2D) c.getGraphics();

            int w = c.getWidth();
            int h = c.getHeight();
            g.clearRect(0, 0, w, h);

            Shape line = new Line2D.Float(40, 20, w-40, h-20);
            g.draw(line);

            Rectangle rk = line.getBounds();
            g.setColor(Color.RED);
            g.drawRect(rk.x,rk. y, rk.width, rk.height);
        }

    private void testShape1(){
        Container c = frame.getContentPane();
        Graphics2D g = (Graphics2D) c.getGraphics();

            int w = c.getWidth();
            int h = c.getHeight();
            g.clearRect(0, 0, w, h);

            GeneralPath gp = new GeneralPath();
            gp.moveTo(100, 100);
            gp.lineTo(200, 100);
            gp.lineTo(200, 200);
            gp.lineTo(100, 200);
            gp.closePath();
            gp.curveTo(150, 100, 125, 200 , 200, 150);
            g.draw(gp);
        }

    private void testXOR() {
        Container c = frame.getContentPane();
        Graphics2D g = (Graphics2D) c.getGraphics();
        int w = c.getWidth();
        int h = c.getHeight();
        g.clearRect(0, 0, w, h);
        int size = 50;
        int x = 0, y = (h - size) / 2, dx = 5;
        g.setXORMode(altColor(g, Color.magenta));
        while (true) {
            g.fillOval(x, y, size, size);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            g.fillOval(x, y, size, size);
            x += dx;
            if (x > w - size || x <= 0 && dx < 0)
                dx = -dx;
        }
    }

    private Color altColor(Graphics2D g, Color c) {
        Color back = g.getBackground();
        int rgb = back.getRGB() ^ c.getRGB();
        return new Color(rgb);
    }

    public TestGraph() {
        frame = new JFrame();
        //frame.setContentPane(panel);
        frame.setTitle(" Test Graph ");
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //movingRect();
                //testXOR();
                //testShape1();
                //testGraph();
                //testShape();
                testGraph();
            }
        });
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void movingRect() {
        Container c = frame.getContentPane();
        Graphics2D g = (Graphics2D) c.getGraphics();
        g.setColor(Color.GREEN);
        int w = c.getWidth();
        int h = c.getHeight();
        g.clearRect(0, 0, w, h);
        new Thread(() -> {
            // Створюємо прямокутник
            sh = new Rectangle2D.Float(0, h / 2 - h / 8, w / 4, h / 4);
            while (sh.getBounds().getWidth() > 5) {
                Rectangle r = sh.getBounds();
                // Відображення
                EventQueue.invokeLater(() -> g.fill(sh));
                // Затримка у часі
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                // Відновлення фонового зображення панелі
                c.repaint(r.x, r.y, r.width, r.height);
                // Формування афінного перетворення
                AffineTransform at = new AffineTransform();
                at.translate(40, 0);
                at.scale(0.9, 0.95);
                at.rotate(0.08, r.x + r.width / 2, r.y + r.height / 2);
                // Перетворення зображення
                sh = at.createTransformedShape(sh);
            }
        }).start();
    }

    public void testGraph() {
        System.out.println("This is testGraph");
        Container c = frame.getContentPane();
        Graphics2D g = (Graphics2D) c.getGraphics();
        int w = c.getWidth();
        int h = c.getHeight();
        /*

        Graphics2D g2 = (Graphics2D) c.getGraphics();
        Graphics2D g3 = (Graphics2D) c.getGraphics();
        Graphics2D g4 = (Graphics2D) c.getGraphics();

        int xPoly[] = {650, 550, 325};
        int yPoly[] = {650, 200, 125};

        poly = new Polygon(xPoly, yPoly, xPoly.length);
                g.setColor(Color.BLUE);
                g.drawPolygon(poly);
                g.fill(poly);

        g2.setPaint(Color.black);
        g3.setPaint(Color.magenta);
        g4.setPaint(Color.yellow);
        Rectangle2D rect = new Rectangle2D.Float(25, 25, 150, 150);
        Ellipse2D elipse = new Ellipse2D.Float(200, 200, 150, 150);
        Line2D line = new Line2D.Double(100, 400, 500, 550);
        g3.fill(rect);
        g4.fill(elipse);
        g2.draw(line);

        c.setBackground(Color.blue);
        g.setColor(Color.yellow);
        g.setFont(new Font(Font.DIALOG, Font.ITALIC, 64));
        g.drawString("Слава Україні", 5, 100);*/

        int size = h / 4;
        int top = h / 16;
        int left = w / 16;

        g.setColor(Color.ORANGE);
        g.fillOval(left, top, size, size);

        g.setColor(Color.YELLOW.darker());
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER, 1, new float[]{ 10, 5 }, 0));

        int x1, x2, y1, y2, r1 = size / 2, r2 = w * 2 / 3;
        for (double fi = 0; fi < 2 * Math.PI; fi += Math.PI / 12) {
            x1 = (int) (left + size / 2 + r1 * Math.cos(fi));
            y1 = (int) (top + size / 2 + r1 * Math.sin(fi));
            x2 = (int) (left + size / 2 + r2 * Math.cos(fi));
            y2 = (int) (top + size / 2 + r2 * Math.sin(fi));
            g.drawLine(x1, y1, x2, y2);
        }

        int penSize = size / 16;
        g.setStroke(new BasicStroke(penSize));
        g.setColor(Color.ORANGE.brighter());
        g.drawOval(left, top, size, size);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestGraph window = new TestGraph();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}