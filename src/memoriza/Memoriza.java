package memoriza;




import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageProducer;
import javax.swing.*;
import javax.swing.border.LineBorder;



public class Memoriza extends JApplet {

    // Indicate which player has a turn, initially it is the X player
    private char x = 'w';
    private int num = 0;
    private JLabel[] vali = new JLabel[2];
    private ImageIcon[] imagenes = new ImageIcon[16];
    private ImageIcon fondos = new ImageIcon("9.PNG");
    private HashMap<Cell, JLabel> conr = new HashMap<>();
    private Cell[][] cells = new Cell[4][4];
    private Cell[][] cells1 = new Cell[4][4];
    JPanel p = new JPanel(new GridLayout(4, 4, 0, 0));
    JPanel p1 = new JPanel(new GridLayout(4, 4, 0, 0));
    private JLabel jlblStatus = new JLabel("X's turn to play");

    public Memoriza() {
        String hey = "2.PNG";
        String t = "";

        for (int i = 0; i < 16; i++) {
            i++;
            if ((i) > 8) {
                t = String.valueOf(i - 8);
            } else {
                t = String.valueOf(i);
            }
            String a = hey.replace(hey.charAt(0), t.charAt(0));
            this.imagenes[i - 1] = new ImageIcon(a);
            i--;
        }

        int y = 0;
        ArrayList<ImageIcon> imagess = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            imagess.add(imagenes[i]);
        }
        int z = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                p1.add(this.cells1[i][j] = new Cell());
                p.add(cells[i][j] = new Cell());
                int RandomNumber = (int) (Math.random() * imagess.size() + 0);
                JLabel backgrounds = new JLabel(imagess.get(RandomNumber));
                JLabel background = new JLabel(fondos);
                cells[i][j].add(background);
                this.cells1[i][j].add(backgrounds);
                imagess.remove(RandomNumber);
                y++;
                conr.put(cells[i][j], backgrounds);
            }

        }

        p.setBorder(new LineBorder(Color.red, 1));
        jlblStatus.setBorder(new LineBorder(Color.yellow, 1));
        add(p);
        add(p1);

        add(jlblStatus, BorderLayout.SOUTH);
        JFrame frame = new JFrame("Memoriza");
        JLabel jlblStatus = new JLabel("Memoriza: tienes 30 segundos");

        frame.add(jlblStatus, BorderLayout.SOUTH);
        frame.setVisible(true);

        frame.layout();

        frame.add(p1, BorderLayout.CENTER);

        frame.layout();
        Calendar calendario = Calendar.getInstance();
        long millis = calendario.getTimeInMillis();
        millis = millis + 30000;
        frame.setSize(500, 700);

        while (calendario.getTimeInMillis() < millis) {
            calendario = Calendar.getInstance();
        }

        frame.setVisible(false);

        JFrame frames = new JFrame("Memoriza");
        frames.setSize(500, 700);

        frames.layout();

        frames.add(p, BorderLayout.CENTER);
        frames.setVisible(true);

        frames.layout();
        frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class Cell extends JPanel {

        // Token used for this cell
        private char token = ' ';

        public Cell() {
            setBorder(new LineBorder(Color.black, 1)); // Set cell's border
            addMouseListener(new MyMouseListener());  // Register listener
        }

        /**
         * Return token
         */
        public char getToken() {
            return token;
        }

        public void setToken(char c) {
            token = c;
            repaint();
        }

        @Override
        /**
         * Paint the cell
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (token == 't') {
                this.remove(this.getComponent(0));
                try {
                this.add(conr.get(this));
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, espera los 30 segundos. Vuelve a cargar el programa e intentalo de nuevo");
                 System.exit(0);
            }

                if (num == 0) {
                    vali[0] = conr.get(this);
                }
                if (num == 1) {
                    vali[1] = conr.get(this);
                    
                    try {
                    if (!vali[0].getIcon().toString().equals(vali[1].getIcon().toString())) {
                        p.removeAll();
                        p.layout();
                        this.layout();
                        JOptionPane.showMessageDialog(this, "Game Over");
                        System.exit(0);
                        p.add(jlblStatus, BorderLayout.CENTER);
                        p.layout();

                    }
                    } catch (NullPointerException ex) {
                        
                        
                    }
                }
                num++;
                if (num > 1) {
                    num = 0;
                }
                this.layout();
                token = 'f';
            }

        }

        private class MyMouseListener extends MouseAdapter {

            @Override
            /**
             * Handle mouse click on a cell
             */
            public void mouseClicked(MouseEvent e) {

                setToken('t');
            }
        }
    }

 
    public static void main(String[] args) {

        Memoriza memoriza = new Memoriza();

    }
}


