/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilities.Constants.SOUNDSIZEDEFAULT;
import utilities.LoadAndSave;

        

/**
 *
 * @author User
 */
public class SoundButton extends PauseButton{
    
    private BufferedImage [][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;
    
    /**
     *Instantiates a sound button for music and sound effects
     * @param x: x-value of the button
     * @param y: y-value of the button
     * @param width: the width of the button
     * @param height: the height of the button
     */
    public SoundButton(int x, int y, int width, int height) {
        super(x,y,width,height);
        loadSoundImgs();
    }
    
    //loads the sound buttons image sprite
    private void loadSoundImgs(){
        BufferedImage temp = LoadAndSave.getSprites(LoadAndSave.SOUNDBTNS);
        soundImgs = new BufferedImage [2][3];
        for(int loop = 0; loop < soundImgs.length; loop++){
            for(int loop2 = 0; loop2 < soundImgs[loop].length; loop2++){
                soundImgs[loop][loop2] = temp.getSubimage(loop2 * SOUNDSIZEDEFAULT, loop * SOUNDSIZEDEFAULT, SOUNDSIZEDEFAULT, SOUNDSIZEDEFAULT);
            }
        }
    }
    
    /**
     *
     */
    public void resetBtns(){//resets the buttons properties of it being pressed or the mouse hovering over it
        mouseOver = false;
        mousePressed = false;
    }
    
    /**
     *
     */
    public void update(){//updates the animations of the buttons (whether they have been pressed or the mouse is hovering over them)
        
        if(muted){
            rowIndex = 1;
        }
        else{
            rowIndex = 0;
        }
        
        colIndex = 0;
        if(mouseOver){
            colIndex = 1;
        }
        if(mousePressed){
            colIndex = 2;
        }
        
    }
    
    /**
     *draws the sound buttons
     * @param g: the image to be drawn
     */
    public void draw(Graphics g){
        g.drawImage(soundImgs[rowIndex][colIndex],x,y,width,height,null);
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
     * @return : whether the button has been pressed to mute the sound
     */
    public boolean getMuted() {
        return muted;
    }

    /**
     *sets whether the sound has been muted or not
     * @param muted : whether the sound has been muted or not by the button being pressed
     */
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    
}
