import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JComponent {

    private int x = 100; // начальные координаты окружности
    private int y = 100;
    private int radius = 50; // радиус окружности
    private int dx = 1; // смещение окружности по оси X
    private int dy = 1; // смещение окружности по оси Y

    public Main() {
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveCircle();
                repaint();
            }
        });
        timer.start();
    }

    private void moveCircle() {
        if (x + radius >= getWidth() || x - radius <= 0) {
            dx = -dx;
        }

        if (y + radius >= getHeight() || y - radius <= 0) {
            dy = -dy;
        }

        x += dx;
        y += dy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (x + radius >= getWidth() || x - radius <= 0 || y + radius >= getHeight() || y - radius <= 0) {
            g2d.setPaint(Color.PINK);
            g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);

            int newRadius = radius - 10;
            if (newRadius > 0) {
                g2d.setColor(Color.YELLOW);
                g2d.drawOval(x - newRadius, y - newRadius, newRadius * 2, newRadius * 2);
                g2d.fillOval(x - newRadius, y - newRadius, newRadius * 2, newRadius * 2);
            }
        } else {
            g2d.setPaint(Color.pink);
            g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

        g2d.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Circle");
        Main animatedCircle = new Main();
        frame.getContentPane().add(animatedCircle);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
