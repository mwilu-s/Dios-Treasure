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
public class Cannon extends GameObject {
    
    //Constants for cannons

    /**
     *The colour of the left facing cannon drawn using the level data
     */
    public static final int LCANNON = 4; //the direction of the cannon

    /**
     *The colour of the right facing cannon drawn using the level data
     */
    public static final int RCANNON = 5;
    
    /**
     *The default width of each sprite of the image for the cannon (40 pixels in width)
     */
    public static final int CAWDEFAULT = 40;//40 x 26

    /**
     *The default height of each sprite of the image for the cannon (26 pixels in height)
     */
    public static final int CAHDEFAULT = 26;

    /**
     *The width of the cannon scaled to fit in the game
     */
    public static final int CAWIDTH = (int) (CAWDEFAULT * Game.SCALE);

    /**
     *The height of the cannon scaled to fit in the game
     */
    public static final int CAHEIGHT = (int) (CAHDEFAULT * Game.SCALE);
    
    private int yTile; // the tile that the cannon is on in the vertical/y-position
    
    /**
     *
     * @param x: The x-value of the cannon
     * @param y: The y-value of the cannon
     * @param objType: The object type i.e. left cannon or right cannon
     */
    public Cannon(int x, int y, int objType) {//The constructor initialises collision detection boxes for the cannons as well as its position in the vertical
        super(x, y, objType);
        yTile = y / Game.STILES;
        initCollision(40,26);
        collision.x -= (int)(4 * Game.SCALE);
        collision.y += (int) (6 * Game.SCALE);
    }
    
    /**
     *This updates the animations of the cannons
     */
    public void update(){
        if(animation){
            updateAT();
        }
    }
    
    /**
     *
     * @return: This retrieves which tile the cannon is on in the vertical/y-position
     */
    public int getYTile(){
        return yTile;
    }
    
    
}
