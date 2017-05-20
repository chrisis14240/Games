/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author chrisis14240
 */
public class HangmanGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        String[] words = {"QUAINT","CHILD","SHRINK","CLUSTER","HARVEST","HOUSE","LUNCH"};
        int wordIndex = (int)(Math.random() * 7 + 0);
        KeyBoard keyBoard = new KeyBoard(words[wordIndex]);
        
        frame.setLayout(null);
        keyBoard.setBounds(0,510,800,150);
        keyBoard.getSecretWord().setBounds(0, 450, 800, 60);
        
        keyBoard.getMan().setBounds(0, 0, 800, 450);
        
        keyBoard.getSecretWord().setBackground(Color.darkGray);
        keyBoard.setBackground(Color.lightGray);
        
        
        
        keyBoard.repaint();
        keyBoard.getSecretWord().repaint();
        keyBoard.getMan().repaint();
        
        frame.add(keyBoard);
        frame.add(keyBoard.getSecretWord());
        frame.add(keyBoard.getMan());
        
        
        
        frame.setSize(800, 650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (keyBoard.isPlaying()) {
            keyBoard.checkLetters();
        }
    }
    
}
