/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author chris
 */
public class KeyButton extends JButton implements MouseListener{
    
    private String label;
    private boolean clicked = false;

    public KeyButton(String label) {
        super(label);
        this.label = label;
        this.addMouseListener(this);
    }
    
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean getClicked() {
        return clicked;
    }

    public String getLabel() {
        return label;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.clicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
