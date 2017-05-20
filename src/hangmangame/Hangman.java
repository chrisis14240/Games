/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author chris
 */
public class Hangman extends JPanel implements ActionListener{
    
    private Rectangle[] hangMan;
    private int nMistakes;
    private Timer timer;
    private boolean isWinner;

    public Hangman() {
        
        this.hangMan = new Rectangle[9];
        this.hangMan[0] = new Rectangle(592, 50, 40, 340);
        this.hangMan[1] = new Rectangle(325, 70, 375, 30);
        this.hangMan[2] = new Rectangle(380, 70, 3, 60);
        this.hangMan[3] = new Rectangle(365, 130, 35, 35);
        this.hangMan[4] = new Rectangle(355, 165, 55, 80);
        this.hangMan[5] = new Rectangle(340, 165, 15, 65);
        this.hangMan[6] = new Rectangle(410, 165, 15, 65);
        this.hangMan[7] = new Rectangle(365, 245, 15, 75);
        this.hangMan[8] = new Rectangle(385, 245, 15, 75);
        timer = new Timer(5, this);
        timer.start();
    }

    public void setnMistakes(int nMistakes) {
        this.nMistakes = nMistakes;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (this.nMistakes == 9) {
            g.setColor(Color.red);
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString("Game over", 400, 100);
            timer.stop();
        } else if (this.isWinner) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString("You Won!", 400, 100);
            timer.stop();
        }
        
        for (int i = 0; i < this.nMistakes; i++) {
            
            g2.draw(this.hangMan[i]);
            
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }  
    
}
