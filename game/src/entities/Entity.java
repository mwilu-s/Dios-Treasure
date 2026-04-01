/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import main.Game;

/**
 *
 * @author User
 */
public class Entity {
    
    /**
     * the x-value of the object
     */
    protected float x,

    /**
     * the y-value of the object
     */
    y;

    /**
     * the width of the object
     */
    protected int width,

    /**
     * the height of the object
     */
    height;

    /**
     * the collision detection box for the object
     */
    protected Rectangle2D.Float collision;

    /**
     * the animation tick for the object
     */
    protected int aniTick,

    /**
     * the index of what sprite the object is at
     */
    aniIndex;

    /**
     *
     */
    protected int state;

    /**
     * the state of the object
     */
    protected float airSpeed;

    /**
     * the speed of the object in the air
     */
    protected float walkSpeed;

    /**
     * the speed of the object when walking
     */
    protected boolean inAir = false;

    /**
     * if the object is in the air or not
     */
    protected int maxHealth;

    /**
     * the maximum health the object can have
     */
    protected int currentHealth;
    //Attacking -- this will check to see if the enemy is within the attacking area

    /**
     *the attack collision detection box for the object
     */
    protected Rectangle2D.Float attackCollision;    
    
    /**
     *
     * @param x : the x-value for the object
     * @param y : the y-value for the object
     * @param width : the width of the object
     * @param height : the height of the object
     */
    public Entity(float x, float y, int width, int height){
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
    }
    
    /**
     *
     * @param g : 
     * @param xLvlOffset :
     */
    protected void drawCollision(Graphics g, int xLvlOffset){
        //for debugging collision
        g.setColor(Color.RED);
        g.drawRect((int)(collision.x - xLvlOffset), (int)collision.y,(int) collision.width, (int) collision.height);
    }
    
    /**
     *Creates the collision detection box for the object
     * @param width : The width of the object
     * @param height : the height of the object
     */
    protected void initCollision(int width, int height){
        collision = new Rectangle2D.Float(x, y,(int) (width * Game.SCALE),(int) (height * Game.SCALE));
    }
    
    /**
     *
     * @param g
     * @param xLvlOffset
     */
    protected void drawAttackCollision(Graphics g, int xLvlOffset){
       g.setColor(Color.red);
       g.drawRect((int) (attackCollision.x - xLvlOffset),(int) attackCollision.y ,(int) attackCollision.width,(int) attackCollision.height);
    }
    
    /**
     *
     * @return : the index of the animation
     */
    public int getAniIndex(){
        return aniIndex;
    }
    
    /**
     *
     * @return : the collision detection box of the object
     */
    public Rectangle2D.Float getCollision(){
        return collision;
    }
    
    /**
     *
     * @return : the state of the object
     */
    public int getState(){
        return state;
    }
     
    /**
     *
     * @return : the current health of the object
     */
    public int getCurrentHealth(){
        return currentHealth;
    }

    
     
}
