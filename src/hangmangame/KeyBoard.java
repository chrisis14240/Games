/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class KeyBoard extends JPanel {
    
    
    private ArrayList<KeyButton> keyButtons;
    private Hangman man;
    private char[] tempWord;
    private String word;
    private boolean playing;
    private SecretWord secretWord;
    private int nMistakes = 0;

    public KeyBoard(String word) {
        
        this.secretWord = new SecretWord(word);
        this.man =  new Hangman();
        this.word = word;
        this.keyButtons = new ArrayList<>();
        this.tempWord = new char[this.word.length()];
        int asciiCode;
        this.playing = true;
        
        for (int i = 0; i < 26; i++) {
            asciiCode = 65 + i;
            this.keyButtons.add(new KeyButton(Character.toString((char) asciiCode)));
            this.add(this.keyButtons.get(i));
        }
    }
    
    public void checkLetters() {
        for (int i = 0; i < this.keyButtons.size(); i++) {
            
            if (this.keyButtons.get(i).getClicked() && this.word.contains(this.keyButtons.get(i).getLabel())) {
                int charIndex = this.word.indexOf(this.keyButtons.get(i).getLabel());
                this.tempWord[charIndex] = this.keyButtons.get(i).getLabel().charAt(0);
                this.keyButtons.get(i).setClicked(false);
                try {
                this.secretWord.getLetters().get(charIndex).setText(this.keyButtons.get(i).getLabel());
                } catch (NullPointerException ex) {
                    
                }
                
             } else if (this.keyButtons.get(i).getClicked()) {
                 this.keyButtons.get(i).setClicked(false);
                 this.nMistakes++;
                 this.man.setnMistakes(this.nMistakes);
             }
            }
        
        if(this.nMistakes == 9) {
            for (int i = 0; i < tempWord.length; i++) {
                tempWord[i] = word.toCharArray()[i];
                try {
                this.secretWord.getLetters().get(i).setText(String.valueOf(this.word.toCharArray()[i]));
                } catch (NullPointerException ex) {
                    
                }
            }
        }
        
        if (String.valueOf(tempWord).equals(word)) {
                for (int j = 0; j < tempWord.length; j++) {
                    System.out.print(tempWord[j]);
                }
                this.man.setIsWinner(true);
                this.playing = false;
        }
        
        
    }

    public boolean isPlaying() {
        return playing;
    }

    public SecretWord getSecretWord() {
        return secretWord;
    }

    public Hangman getMan() {
        return man;
    }
}
