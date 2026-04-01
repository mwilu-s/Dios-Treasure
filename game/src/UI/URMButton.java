/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilities.Constants.*;
import utilities.LoadAndSave;


/**
 *
 * @author User
 */
public class URMButton extends PauseButton{
    
    
    private BufferedImage [] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;
    
    /**
     *Creates the unpause, replay and menu buttons
     * @param x : x-value of the button
     * @param y : y-value of the button
     * @param width : the width of the button
     * @param height : the height of the button
     * @param rowIndex : the row of the button
     */
    public URMButton(int x, int y, int width, int height, int rowIndex) {
        super(x,y,width,height);
        this.rowIndex = rowIndex;
        loadImgs();
        
    }
    
    /**
     *loads the images of the buttons
     */
    public void loadImgs(){
        BufferedImage temp = LoadAndSave.getSprites(LoadAndSave.URMBTNS);
        imgs = new BufferedImage [3];
        for(int loop = 0; loop < imgs.length; loop++){
            imgs[loop] = temp.getSubimage(loop * URMPHDEFAULT, rowIndex * URMPHDEFAULT, URMPHDEFAULT, URMPHDEFAULT);
        }
        
    }
    
    /**
     *
     */
    public void update(){//updates the animation of the volume button(whether the mouse is on the button or it is being pressed)
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
    }
    
    /**
     *draws the buttons
     * @param g: the image to be drawn
     */
    public void draw(Graphics g){
        g.drawImage(imgs[index], x, y, URMPHSIZE, URMPHSIZE, null);
    }
    
    /**
     *
     */
    public void resetBtns(){//resets the buttons to default properties
    mouseOver = false;
    mousePressed = false;
}

    /**
     *
     * @return: whether the mouse is on the button
     */
    public boolean getMouseOver() {
        return mouseOver;
    }

    /**
     *sets whether the mouse is on the button or not
     * @param mouseOver : whether the mouse is on the button
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     *
     * @return : whether the mouse has pressed the button
     */
    public boolean getMousePressed() {
        return mousePressed;
    }

    /**
     *sets whether the mouse has pressed the button or not
     * @param mousePressed : whether the mouse has pressed the button
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    
}
