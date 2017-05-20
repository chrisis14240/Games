/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class SecretWord extends JPanel {
    
    
    private ArrayList<JButton> letters;

    public SecretWord(String word) {
        letters = new ArrayList<>();
        try {
        for (int i = 0; i < word.length(); i++) {
            this.letters.add(new JButton(""));
            this.letters.get(i).setPreferredSize(new Dimension(50,50));
            this.letters.get(i).setBackground(Color.lightGray);
            this.add(this.letters.get(i));
        }
        } catch (NullPointerException ex) {
            
        }
    }

    public ArrayList<JButton> getLetters() {
        return letters;
    }
}
