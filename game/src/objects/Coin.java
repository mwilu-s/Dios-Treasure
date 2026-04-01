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
public class Coin extends GameObject {
    
    //Constants for coin

    /**
     * The colour the coin will be drawn using the level data
     */
     public static final int COIN = 6;
     
    /**
     * The default width of each sprite image of the coin (16 pixels in width)
     */
    public static final int COINWDEFAULT = 16;

    /**
     * The default height of each sprite image of the coin (16 pixels in height)
     */
    public static final int COINHDEFAULT = 16;

    /**
     *The width of the coin scaled to fit in the game
     */
    public static final int COINWIDTH = (int) (Game.SCALE * COINWDEFAULT);

    /**
     *The height of the coin scaled to fit in the game
     */
    public static final int COINHEIGHT = (int) (Game.SCALE * COINHDEFAULT);
    
    private float hoverOffset;// this is the hovering offset of the coin
    private int maxHoverOffset, hoverDir = 1; // the direction of the object (whether its going up = 1 or down = -1) and the maximum the coin can hover
    
    /**
     *
     * @param x : The x-value of the coin
     * @param y : The y-value of the coin
     * @param objType : The object type i.e. coin
     */
    public Coin(int x, int y, int objType) {//Constructor that initialises a collision detection box as well as how the coin is drawn with the offsets
        super(x, y, objType);
        animation = true;
        initCollision(10,10);
        xDrawOffset = (int) (3 * Game.SCALE);//3 pixels to the left
        yDrawOffset = (int) (3 * Game.SCALE);//3 pixels above
      
        maxHoverOffset = (int) (10*Game.SCALE);
    }
    
    /**
     * This updates the animations of the coin
     */
    public void update(){
        updateAT();
        updateHover();
    }
    
    private void updateHover(){//This updates the hover effects of the coin (going up and down)
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
