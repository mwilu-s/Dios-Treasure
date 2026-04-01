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
public class GameContainer extends GameObject {
    
    //Constants for game containers

    /**
     *The colour the barrel will be drawn using the level data
     */
    public static final int BARREL = 1;

    /**
     *The colour the box will be drawn using the level data
     */
    public static final int BOX = 2;
    
    /**
     *The default width of each sprite of the image for the containers (40 pixels in width)
     */
    public static final int CWDEFAULT = 40;

    /**
     * The default height of each sprite of the image for the containers (40 pixels in height)
     */
    public static final int CHDEFAULT = 30;

    /**
     *The width of the container scaled to fit in the game
     */
    public static final int CWIDTH = (int)(Game.SCALE * CWDEFAULT);

    /**
     *The height of the container scaled to fit in the game
     */
    public static final int CHEIGHT = (int)(Game.SCALE * CHDEFAULT);
    
    /**
     *
     * @param x: the x-value of the container
     * @param y: the y-value of the container
     * @param objType: object type i.e. box or barrel
     */
    public GameContainer(int x, int y, int objType) {//The constructor that initialises a collison detction box for the containers
        super(x, y, objType);
        createCollision();
    }
    
    //This creates the different types of collision boxes for the different containers as they have different heights and widths
    private void createCollision(){
        if(objType == BOX){
            initCollision(25,17);
            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);
        }
        else{
            initCollision(23,25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
            
        }
        
        collision.y += yDrawOffset + (int)(Game.SCALE * 2); //makes sure that the object touches the floor; 2 is the difference between sprite (30) and tile size (32)
        collision.x += xDrawOffset/2; //places the object in the centre of the tile
    }
    
    /**
     *This updates the animations of the containers
     */
    public void update(){
        if(animation){
            updateAT();
        }
    }
}
