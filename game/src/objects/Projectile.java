/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.geom.Rectangle2D;
import main.Game;
/**
 *
 * @author User
 */
public class Projectile {
    
    //Constants for projectiles

    /**
     * The default cannon ball width of the image (15 pixels in width)
     */
    public static final int CBWDEFAULT = 15;

    /**
     * The default cannon ball height of the image (15 pixels in height)
     */
    public static final int CBHDEFAULT = 15;

    /**
     * The cannon ball's width scaled to fit in the game
     */
    public static final int CBWIDTH = (int) (Game.SCALE * CBWDEFAULT);

    /**
     * The cannon ball's height scaled to fit in the game
     */
    public static final int CBHEIGHT = (int) (Game.SCALE * CBHDEFAULT);

    /**
     * The speed at which the cannon ball will travel
     */
    public static final float SPEED = 0.75f * Game.SCALE;
    
    private Rectangle2D.Float collision;//the collision detection box
    private int direction;//direction can only be left or right;
    private boolean active = true; // whether the object is present in the game or not
    
    /**
     *
     * @param x : The x-value of the cannon ball
     * @param y : The y-value of the cannon ball
     * @param direction : The direction the cannon ball is going
     */
    public Projectile(int x, int y, int direction){
        int xOffset = (int)(-3 * Game.SCALE);
        int yOffset = (int) (5 * Game.SCALE);
        
        if(direction == 1){
            xOffset = (int) (29 * Game.SCALE);
        }
        
        collision = new Rectangle2D.Float(x + xOffset,y + yOffset, CBWIDTH,CBHEIGHT);
        this.direction = direction;
        
    }
    
    /**
     * This is updates the position of the cannon ball's collision detection box as it moves in the horizontal
     */
    public void updatePos(){
        collision.x += direction * SPEED; // if 1 = going to right; if -1 then going to left
    }
    
    /**
     *
     * @param x: The x-value position of the cannon ball
     * @param y: The y-value position of the cannon ball
     */
    public void setPos(int x, int y){
        collision.x = x;
        collision.y = y;
    }
    
    /**
     *
     * @return : The collision detection box of the cannon ball
     */
    public Rectangle2D.Float getCollision(){
        return collision;
    }
    
    /**
     *
     * @return : Whether the cannon ball is present in the game or not
     */
    public boolean getActive(){
        return active;
    }
    
    /**
     *This sets the activity of the cannon ball
     * @param active : Whether the cannon ball is present in the game or not
     */
    public void setActive(boolean active){
        this.active = active;
        
    }
}
