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
public class Spikes extends GameObject {
    
    //Constants for spikes

    /**
     * The colour that the spike will use to be drawn to in the level data
     */
    public static final int SPIKES = 3;
    
    /**
     * The default width the image is (32 pixels in width)
     */
    public static final int SWDEFAULT = 32;

    /**
     * The default height the image is (32 pixels in height)
     */
    public static final int SHDEFAULT = 32;

    /**
     * The width of the spikes scaled to fit in the game
     */
    public static final int SWIDTH = (int) (Game.SCALE * SWDEFAULT);

    /**
     * The height of the spike scaled to fit in the game
     */
    public static final int SHEIGHT = (int) (Game.SCALE * SHDEFAULT);
    
    /**
     *
     * @param x : This is the x-value of the spikes
     * @param y : This is the y-value of the spikes
     * @param objType : This the object type i.e. spikes
     */
    public Spikes(int x, int y, int objType) {
        //The constructor to initialise a collision detection box for the spikes as well as how it will be drawn in the game with the offsets
        super(x, y, objType);
        
        initCollision(32,16);
        xDrawOffset = 0;
        yDrawOffset = (int) (Game.SCALE * 16);
        collision.y += yDrawOffset;
    }
    
}
