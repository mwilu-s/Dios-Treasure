/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilities.Constants.*;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class VolumeButton extends PauseButton{
    
    private BufferedImage [] imgs;
    private BufferedImage slider;
    private int index = 0;
    private boolean mousePressed, mouseOver;
    private int buttonX, minX, maxX;
    private float fValue = 0f;
    
    /**
     *Creates a volume button object with the following properties:
     * @param x : x-value of the volume button
     * @param y : y-value of the volume button
     * @param width : the width of the volume button
     * @param height : the height of the volume button
     */
    public VolumeButton(int x, int y, int width, int height) {
        super(x + width/2, y, VWIDTH, height);
        box.x -= VWIDTH/2;
        buttonX = x + width/2;
        this.x = x;
        this.width = width;
        minX = x + VWIDTH/2;
        maxX = x + width - VWIDTH/2;
        loadImgs();
    }
    
    /**
     *loads the images of the volume button
     */
    public void loadImgs(){
        BufferedImage temp = LoadAndSave.getSprites(LoadAndSave.VOLUMEBTNS);
        imgs = new BufferedImage [3];
        for(int loop = 0; loop < imgs.length; loop++){
            imgs[loop] = temp.getSubimage(loop * VWDEFAULT, 0, VWDEFAULT, VHDEFAULT);
        }
        slider = temp.getSubimage(3*VWDEFAULT, 0, SWDEFAULT, VHDEFAULT);
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
     *
     * @param g : the images to be drawn
     */
    public void draw(Graphics g){//draws the buttons as well as the slider for the volume
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(imgs[index], buttonX - VWIDTH/2, y, VWIDTH, height, null);
    }
    
    /**
     *Changes the position of the button as the user drags it
     * @param x : x-position of button
     */
    public void changeX(int x){
        if(x < minX){
            buttonX = minX;
        }
        else if(x > maxX){
            buttonX = maxX;
        }else{
            buttonX = x;
            updateFloatValue();
        }
        
        box.x = buttonX - VWIDTH/2;
        
    }
    
    private void updateFloatValue(){//changes the values of how far the button can go
        float range = maxX - minX;
        float value = buttonX - minX;
        fValue = value/range;
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
    
    /**
     *
     * @return : the value of the position for where the button is
     */
    public float getFloatValue(){
        return fValue;
    }
    
}
