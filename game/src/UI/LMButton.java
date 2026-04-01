/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import GameStates.GameState;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilities.Constants.*;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class LMButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCentre = BWIDTH/2;
    private GameState state;
    private BufferedImage [] imgs; 
    private boolean mouseOver, mousePressed;
    private Rectangle box;
    
    /**
     * Creates all the level buttons (addition, subtraction, multiplication)
     * @param xPos : the position of the button horizontally
     * @param yPos : the position of the button vertically
     * @param rowIndex : he row of the button in the sprite image
     * @param state : the level menu state
     */
    public LMButton(int xPos, int yPos, int rowIndex, GameState state){
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBox();
    }

    //loads the images of the level menu buttons
    private void loadImgs() {
        imgs = new BufferedImage [3];
        BufferedImage temp = LoadAndSave.getSprites(LoadAndSave.LMBTNS);
        for(int loop = 0; loop < imgs.length; loop++){
           imgs[loop] = temp.getSubimage(loop * BWIDTHDEFAULT, rowIndex * BHEIGHTDEFAULT, BWIDTHDEFAULT, BHEIGHTDEFAULT);
        }
    }
    
    /**
     *Draws the buttons
     * @param g : the image of the buttons
     */
    public void draw(Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCentre, yPos, BWIDTH, BHEIGHT, null);
    }
    
    /**
     *updates the animations of the buttons
     */
    public void update(){
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

// creates the box around the buttons
    private void initBox() {
        box = new Rectangle(xPos-xOffsetCentre, yPos, BWIDTH, BHEIGHT);
    }

    /**
     *
     * @return : the box around the button
     */
    public Rectangle getBox() {
        return box;
    }
     
    /**
     * it will take the user to the interface depending on the button pressed
     */
    public void applyState(){
        GameState.state = state;
    }
    
    /**
     *resets the animations of the buttons
     */
    public void resetButtons(){
        mouseOver = false;
        mousePressed = false;
    }
    
    /**
     *
     * @return : the state that the button is currently in
     */
    public GameState getState(){
        return state;
    }

}
