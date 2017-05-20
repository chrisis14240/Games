package testpaintcompenent;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestPaintCompenent extends JFrame {

    public TestPaintCompenent() {
        add(new NewPanel());
    }

    public static void main(String[] args) {
        TestPaintCompenent frame = new TestPaintCompenent();
        frame.setTitle("Juego");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

class NewPanel extends JPanel implements ActionListener {

    private Timer Timer;
    private int x, y, t, xd, xi, yd, yi, td, sec;
    private int caida_moneda;
    private int caida_piedra;
    private int coor_x_moneda;
    private int coor_x_piedra;
    private int puntos;

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
    }

    public NewPanel() {
        this.caida_moneda = 0;
        this.coor_x_moneda = 60;
        this.coor_x_piedra = 400;
        this.xd = 1100;
        this.yd = 510;
        this.xi = -150;
        this.yi = 510;
        this.x = 450;
        this.y = 476;
        this.Timer = new Timer(100, this);
        initBoard();
        Timer.start();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int Key = e.getKeyCode();
            if (Key == KeyEvent.VK_LEFT) {

                x -= 15;
                t += 1;
                if (t == 4) {
                    t = 0;
                }
            }
            if (Key == KeyEvent.VK_RIGHT) {

                x += 15;
                t += 1;
                if (t == 4) {
                    t = 0;
                }
            }
            if (Key == KeyEvent.VK_DOWN) {

            }
            if (Key == KeyEvent.VK_UP) {
                y = 356;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int Key = e.getKeyCode();
            if (Key == KeyEvent.VK_UP) {
                y = 476;
            }
        }
    }

    public Image Fondo(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for (int i = 0; i < 4000; i = i + 22) {
//            Image fondo = Fondo("blue_background.PNG");
//            g.drawImage(fondo, i, 0, null);
//        }
        for (int i = 0; i < 4000; i = i + 335) {
            Image nube = Fondo("clouds.PNG");
            g.drawImage(nube, i, 100, this);
        }

        Image moneda = Fondo("coin.png");
        g.drawImage(moneda, coor_x_moneda, caida_moneda, this);
        
        Image piedra = Fondo("stone.png");
        g.drawImage(piedra, coor_x_piedra, caida_piedra, this);
        
        for (int i = 0; i < 4000; i = i + 111) {
            Image piso = Fondo("ground_loop.png");
            g.drawImage(piso, i, 610, this);
        }
        Image enemigod = Fondo("enemy_run_d.png");
        g.drawImage(enemigod, xd, yd, 105 + xd, yd + 101, sec * 105, 0, (sec * 105) + 105, 101, this);

        Image enemigoi = Fondo("enemy_run_i.png");
        g.drawImage(enemigoi, xi, yi, 105 + xi, yi + 101, sec * 105, 0, (sec * 105) + 105, 101, this);

        Image personaje = Fondo("walking.png");
        g.drawImage(personaje, x, y, 116 + x, y + 133, t * 116, 0, (t * 116) + 116, 133, this);

        g.drawString("Puntos:" + puntos, 900, 20);
    }

    public Rectangle Personaje() {
        return new Rectangle(x + 15, y+18, 78, 114);
    }

    public Rectangle Enemigod() {
        return new Rectangle(xd+5, yd, 105, 101);
    }

    public Rectangle Enemigoi() {
        return new Rectangle(xi, yi, 105, 101);
    }

    public Rectangle Moneda() {
        return new Rectangle(coor_x_moneda, caida_moneda, 55, 55);
    }

    public Rectangle Piedra() {
        return new Rectangle(coor_x_piedra, caida_piedra, 65, 59);
    }

    public void Colision() {
        this.Personaje();
        this.Enemigod();
        this.Enemigoi();
        this.Moneda();
        this.Piedra();
        if (Personaje().intersects(Enemigod()) || Personaje().intersects(Enemigoi()) || Personaje().intersects(Piedra())) {
            Timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over");
        }
        if (Personaje().intersects(Moneda())) {
            caida_moneda = 700;
            this.puntos += 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xd -= 10;
        if (xd <= -150) {
            xd = 1100;
        }
        xi += 12;
        if (xi >= 1100) {
            xi = -150;
        }
        sec += 1;
        if (sec == 2) {
            sec = 0;
        }
        caida_moneda += 6;
        caida_piedra += 4;
        if (caida_moneda >= 700) {
            caida_moneda = 0;
            coor_x_moneda = (int) (Math.random() * 945 + 1);

        }
        if (puntos >= 4 && puntos <= 8) {
            caida_piedra += 8;
            if (caida_piedra >= 700) {
                caida_piedra = 0;
                coor_x_piedra = (int) (Math.random() * 935 + 1);
            }
        } else if (puntos > 8) {
            caida_piedra += 12;
            if (caida_piedra >= 700) {
                caida_piedra = 0;
                coor_x_piedra = (int) (Math.random() * 1000 + 1);
            }
        }
        Colision();
        repaint();
    }

}
