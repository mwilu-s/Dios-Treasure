/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author User
 */
public interface StateMethods {
    //Methods that will be used for the different interfaces (game states)
    /**
     *updates any animations or actions for the interface
     */
    public void update();

    /**
     *Draws the images onto the interface
     * @param g : the image to be drawn
     */
    public void draw(Graphics g);

    /**
     *Determines if the user has clicked their mouse
     * @param e : the action of the mouse
     */
    public void mouseClicked(MouseEvent e);

    /**
     *Determines if the user has pressed their mouse
     *  @param e : the action of the mouse
     */
    public void mousePressed(MouseEvent e);

    /**
     *Determines if the user has released their mouse
     *  @param e : the action of the mouse
     */
    public void mouseReleased(MouseEvent e);

    /**
     *Determines if the user has moved their mouse
     *  @param e : the action of the mouse
     */
    public void mouseMoved(MouseEvent e);

    /**
     *Determines if the user has pressed a key
     *  @param e : the action of the keyboard
     */
    public void keyPressed(KeyEvent e);

    /**
     *Determines if the user has released a key
     *  @param e : the action of the keyboard
     */
    public void keyReleased(KeyEvent e);
}
