/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import main.Game;
import static objects.Cannon.LCANNON;
import static objects.Cannon.RCANNON;
import static objects.Coin.COIN;
import static objects.GameContainer.BARREL;
import static objects.GameContainer.BOX;
import static objects.Potion.PPOTION;
import static utilities.Constants.ANISPEED;

/**
 *
 * @author User
 */
public class GameObject {//parent class
    
    //This class is created to store all the constants of all the game objects that can be found in the game for a level.
    //It also stores all the properties that some (or more) objects will need depending on their interaction with the player or enemies.
    
    private int getObjSpriteAmt(int objType){
        //This method retrieves what type of object it is, using the constants, and will return however many animations that object sprite has.
            switch(objType){
                case COIN:
                    return 4;
                case PPOTION: 
                    return 7;
                case BARREL:
                    return 8;
                case BOX:
                    return 8;
                case LCANNON:
                    return 7;
                case RCANNON:
                    return 7;
            }
            
            return 1;
        }
    
    /**
     * the x-value of the object
     */
    protected int x, 

    /**
     * The y-value of the object
     */
    y, 

    /**
     *
     */
    objType; //This is used obtain the width (x) and height (y) of the object as well as the type of object using its constant value

    /**
     *
     */
    protected Rectangle2D.Float collision; //This draws a collision detection box around the object

    /**
     * whether the object is animating or not
     */
    protected boolean animation, 

    /**
     *
     */
    active = true; //This will determine whether the object is animation (animation) and whether the object is still present in the game (active)

    /**
     * the animation tick of the object and how long it will animate for
     */
    protected int aniTick, 

    /**
     *
     */
    aniIndex; //This will determine the animation tick of the object and how fast it animates and the animation index which determines which animations the object will do based on its interaction in the game

    /**
     *the drawing offset of the object in the horizontal positions
     */
    protected int xDrawOffset, 

    /**
     *the drawing offset of the object in the vertical positions
     */
    yDrawOffset; //This is used for drawing the object in the level correctly, relative to the floor
    
    /**
     *
     * @param x : the x-value of the object
     * @param y: the y-value of the object
     * @param objType : The type of object it is
     */
    public GameObject(int x, int y, int objType){//Constructor for a game object
        this.x = x;
        this.y = y;
        this.objType = objType;
    }
    
    /**
     *
     * @param width: the width of the object
     * @param height: the height of the object
     */
    protected void initCollision(int width, int height){
        //This method creates the collision detection box around the object and accepts the parameters of its height and width
        collision = new Rectangle2D.Float(x, y,(int) (width * Game.SCALE),(int) (height * Game.SCALE));
    }
    
    /**
     *
     * @param g: This is the drawing of the collision detection box
     * @param xLvlOffset : the level offset in the game in the horizontal position
     */
    /*public void drawCollision(Graphics g, int xLvlOffset){
        //for debugging collision
        g.setColor(Color.RED);
        g.drawRect((int)collision.x - xLvlOffset, (int)collision.y,(int) collision.width, (int) collision.height);
    }*/
    
    /**
     * This updates the object and its animations
     */
    protected void updateAT(){
        aniTick++;
	if (aniTick >= ANISPEED) {
	    aniTick = 0;
	    aniIndex++;
	    if (aniIndex >= getObjSpriteAmt(objType)) {
		aniIndex = 0;
                if(objType == BARREL || objType == BOX){
                   animation = false;
                   active = false;
                }else{
                    if(objType == LCANNON || objType == RCANNON){
                       animation = false;
                    }
                }
	    }
	}

    }
   
    /**
     * This resets all the properties of the object
     */
    public void reset(){
       //This resets the object to their default settings  
       aniIndex = 0;
       aniTick = 0;
       active = true;
       
       if(objType == BARREL || objType == BOX || objType == LCANNON || objType == RCANNON){
           animation = false;
       }
       else{
           animation = true;
       }
   } 
   
    /**
     *
     * @return : The animation that the object is currently on
     */
    public int getAniIndex(){
       return aniIndex;
   }

    /**
     *
     * @return : the drawing offset of the object in the horizontal position
     */
    public int getXDrawOffset(){
       return xDrawOffset;
   }
   
    /**
     *
     * @return : The type of object it is
     */
    public int getObjType(){
       return objType;
   }
   
    /**
     *
     * @return : the drawing offset of the object in the vertical position
     */
    public int getYDrawOffset(){
       return yDrawOffset;
   }
   
    /**
     *
     * @return : whether the object is present in the game or not
     */
    public boolean getActive(){
       return active;
   }
   
    /**
     *
     * @return : the collision detection box of the object
     */
    public Rectangle2D.Float getCollision(){
       return collision;
   }
   
    /**
     *This sets whether the object is present or not
     * @param active: Whether the object is present in the game or not
     */
    public void setActive(boolean active){
       this.active = active;
   }
   
    /**
     *This sets whether the object is animating or not
     * @param animation : Whether the object is animating or not
     */
    public void setAnimation(boolean animation){
       this.animation = animation;
   }
   
    /**
     *
     * @return : The animation tick of the object
     */
    public int getAniTick(){
       return aniTick;
   }
   
        
}
