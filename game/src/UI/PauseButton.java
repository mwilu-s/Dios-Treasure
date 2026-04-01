/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class PauseButton {
    
    /**
     *the x-value of the button
     */
    protected int x,

    /**
     * the y-value of the button
     */
    y,

    /**
     * the width of the button
     */
    width,

    /**
     * the height of the button
     */
    height;

    /**
     * the box around the button
     */
    protected Rectangle box;
    
    /**
     *Creates a pause button type
     * @param x : x-value of the button
     * @param y: y-value of the button
     * @param width : width of the button
     * @param height : height of the button
     */
    public PauseButton(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBox();
    }
    
    /**
     *creates the boxes around the buttons
     */
    public void createBox(){
        box = new Rectangle(x,y,width,height);
    }

    /**
     *sets the x-value of the button
     * @param x: the x-value of the button
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *sets the y-value of the button
     * @param y : y-value of the button
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *sets the box around the specific button
     * @param box : box around the button
     */
    public void setBox(Rectangle box) {
        this.box = box;
    }

    /**
     *
     * @return : the x-value of the button
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return : the y-value of the button
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return : the box around the button
     */
    public Rectangle getBox() {
        return box;
    }
    
    
}
