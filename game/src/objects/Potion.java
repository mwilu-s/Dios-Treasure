/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import main.Game;

/**
 *
 * @author User
 */
public class Potion  extends GameObject{
    
    //Constants for potion

    /**
     * The colour value the potion will be drawn based on the level data
     */
    public static final int PPOTION = 0;
    
    /**
     * The value the potion gives the player when they come into contact with one another
     */
    public static final int PPOTIONVALUE = 15;

    /**
     * The default width of the potion of each sprite in the image (12 pixels in width)
     */
    public static final int PWDEFAULT = 12;

    /**
     * The default height of the potion of each sprite in the image (16 pixels in height)
     */
    public static final int PHDEFAULT = 16;

    /**
     * The width of the image scaled to fit in the game
     */
    public static final int PWIDTH = (int)(Game.SCALE * PWDEFAULT);

    /**
     * The height of the image scaled to fit in the game
     */
    public static final int PHEIGHT = (int) (Game.SCALE * PHDEFAULT);

    
    private float hoverOffset; // this is the hovering offset of the potion
    private int maxHoverOffset, hoverDir = 1; // the direction of the object (whether its going up = 1 or down = -1) and the maximum the potion can hover
    
    /**
     *
     * @param x : The x-value of the potion
     * @param y : The y-value of the potion
     * @param objType : The object type i.e. potion
     */
    public Potion(int x, int y, int objType) {
        super(x, y, objType);
        animation = true;
        initCollision(7,14);
        xDrawOffset = (int) (3 * Game.SCALE);//3 pixels to the left
        yDrawOffset = (int) (2 * Game.SCALE);//2 pixels above
        
        maxHoverOffset = (int) (10*Game.SCALE);
    }
    
    /**
     * This updates the animations of the potion
     */
    public void update(){
        updateAT();
        updateHover();
    }
    
    //This updates the hover effect of the potion (going up and down)
    private void updateHover(){
        hoverOffset += (0.2f * Game.SCALE * hoverDir);
        
        if(hoverOffset >= maxHoverOffset){
            hoverDir = -1;
        }else{
            if(hoverOffset < 0){
                hoverDir = 1;
            }
        }
        
        collision.y = y + hoverOffset;
    }
    
}
