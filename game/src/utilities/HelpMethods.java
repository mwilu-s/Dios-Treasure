/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;


import entities.Enemy;
import static entities.Enemy.ENEMY;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import objects.Cannon;
import static objects.Cannon.LCANNON;
import static objects.Cannon.RCANNON;
import objects.Coin;
import static objects.Coin.COIN;
import objects.GameContainer;
import static objects.GameContainer.BARREL;
import static objects.GameContainer.BOX;
import objects.Potion;
import static objects.Potion.PPOTION;
import objects.Projectile;
import objects.Spikes;
import static objects.Spikes.SPIKES;


/**
 *
 * @author User
 */
public class HelpMethods {
    
    //This class is used to store all methods that will be used for collision detection as well as loading objects and determining interactions between characters

    /**
     *
     * @param x: This gets the x-value of the collision box of the object
     * @param y: This gets the y-value of the collision box of the object
     * @param width : This gets the width of the collision box of the object
     * @param height : This gets the height of the collision box of the object
     * @param lvlData : This gets the values of the level data and determines what tile the object is in contact with. As the object moves (if it can) the array will have the values of where the object is currently
     * @return : It returns true if the object is in contact with a solid part of the game and false if it is not in contact with any solid part of the game. This prevents the object from going through those solid parts
     */
    
    public static boolean detection(float x, float y, float width, float height, int[][] lvlData){
       //This method detects whether the object is touching the ground or any solid part of the game
        if(!solid(x,y,lvlData)){
            if(!solid(x+width, y+height, lvlData)){
                if(!solid(x+width, y, lvlData)){
                    if(!solid(x,y+height,lvlData)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param x: The x-value of the collision detection box of the object
     * @param y: The y-value of the collision detection box of the object
     * @param lvlData: The current tile the object is on with the use of the double array (height and width of the tile)
     * @return: It returns whether the tile ,that object is on, is solid or not
     */
    public static boolean solid(float x, float y, int[][] lvlData){
        //This method determines whether the object is on solid ground
        int maxWidth = lvlData[0].length * Game.STILES;
        if(x < 0 || x >= maxWidth){
            return true;
        }
        if(x<0 || y >= Game.GHEIGHT){
            return true;
        }
        
        float xIndex = x / Game.STILES;
        float yIndex = y / Game.STILES;
        
        return getSolidTile((int) xIndex, (int) yIndex, lvlData);
    }
    
    /**
     *
     * @param xTile: This gets the position of the tile in the horizontal
     * @param yTile: This gets the position of the tile in the vertical
     * @param lvlData: The current tile the object is on with the use of the double array (height and width of the tile)
     * @return : It returns whether the current tile is a solid tile (true) or not (false)
     */
    public static boolean getSolidTile(int xTile, int yTile, int [][] lvlData){
        //This determines if the game tile that the player is on is solid for the player to stand on and not sink through it
        int value = lvlData[yTile][xTile];
        if(value >= 48 || value < 0 || value != 11){
            return true;
        }
        return false;
    }

    /**
     *
     * @param p : This is a cannon ball object
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @return : It will return whether the cannon ball object has hit the wall or any solid part of the game
     */
    public static boolean getProjectileHitWall(Projectile p, int [][]lvlData){
        //This determines if a cannon ball has hit the wall, meaning it will disappear and not go through the outside barrier
        return solid(p.getCollision().x + p.getCollision().width/2, p.getCollision().y + p.getCollision().height/2, lvlData);
    }
    
    /**
     *
     * @param collision : This gets the collision detection box of the object
     * @param xSpeed : This is the speed at which the object is going in the horizontal
     * @return : This returns the position where the object will be (if it can move)
     */
    public static float getEntityXPosNextToWall(Rectangle2D.Float collision, float xSpeed){
        //This will make sure that the player cannot run through walls
        int cTile = (int) (collision.x /Game.STILES);
        if(xSpeed > 0){
            //Right
            int tileXPos = cTile * Game.STILES;
            int xOffset = (int) (Game.STILES - collision.width);
            return tileXPos + xOffset - 1;
        }
        else{
            //left
            return cTile * Game.STILES;
        }
    }
    
    /**
     *
     * @param collision : This gets the collision detection box of the object
     * @param airSpeed : This gets the speed of the object when it is in the air (vertical)
     * @return : Returns where the object will be if it jumps or falls
     */
    public static float getEntityYPosUnderROrAboveF(Rectangle2D.Float collision, float airSpeed){
        //This determines if characters are under and game tiles or above so that they dont go through the tiles when they jump or fall.
        int cTile = (int) (collision.y /Game.STILES);
        if(airSpeed > 0){
            //down or falling
            int tileYPos = cTile * Game.STILES;
            int yOffset = (int) (Game.STILES - collision.height);
            return tileYPos + yOffset - 1;
        }
        else{
            //jumping
            return cTile * Game.STILES;
        }
    }
    
    /**
     *
     * @param collision : This gets the collision detection box of the object
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @return : This returns whether the object is on the ground or in the air
     */
    public static boolean notOnFloor(Rectangle2D.Float collision, int [][] lvlData){
        //check pixel below bottom left and right
        if(!solid(collision.x, collision.y + collision.height + 1, lvlData)){//bottom left
            if(!solid(collision.x + collision.width, collision.y + collision.height + 1, lvlData)){//bottom right
                return false;
            }
        }
        return true;
    }
    
    /**
     *
     * @param collision : The collision detection box of the object
     * @param xSpeed : The speed of the object in the x-position
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @return : It will return a boolean value and whether it is on the floor or not
     */
    public static boolean isFloor(Rectangle2D.Float collision, float xSpeed, int [][] lvlData){
        //This checks to see if the player is on the floor and not in the air
        if(xSpeed > 0){
            return solid(collision.x + collision.width + xSpeed, collision.y + collision.height + 1, lvlData);
        }
        else{
            return solid(collision.x + xSpeed, collision.y + collision.height + 1, lvlData);
        }
        
    }
    
    /**
     *
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @param collision : This gets the collision detection box of the object
     * @param collision2 : This gets the collision detection box of the second object
     * @param yTile : This gets the position of the tile, in the vertical, that the object is on
     * @return : This returns whether the object is in line with the second object and they are both in the same y-position. One object cannot be above or below the other
     */
    public static boolean canSeePlayer(int [][] lvlData, Rectangle2D.Float collision, Rectangle2D.Float collision2, int yTile){
        //this checks if a cannon or an enemy can see the player within a certain distance range
         int xTile = (int) (collision.x / Game.STILES);
         int xTile2 = (int) (collision2.x / Game.STILES);
        
        if(xTile > xTile2){
            return getAllTilesClear(xTile2, xTile, yTile, lvlData);
            
        }else{
            return getAllTilesClear(xTile, xTile2, yTile, lvlData);
        }
     }
    
    /**
     *
     * @param x1 : This gets the x-position of the object
     * @param x2 : This gets the x-position of the second object
     * @param y : This is the y-position of both objects
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @return : This returns a boolean value determining whether the tiles are clear between the two objects and no other object is between them
     */
    public static boolean getAllTilesClear(int x1, int x2, int y, int [][] lvlData){
         for(int loop = 0; loop < x2 - x1; loop++){
             if(getSolidTile(x1 + loop, y, lvlData)){
                 return false;
             }
         }
         return true;
     }
    
    /**
     *
     * @param x1 : This gets the x-position of the object
     * @param x2 : This gets the x-position of the second object
     * @param y : This is the y-position of both objects
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @return : This returns a boolean value determining whether the tiles are solid
     */
    public static boolean getWalkableTiles(int x1, int x2, int y, int [][] lvlData){
        if(getAllTilesClear(x1, x2, y, lvlData)){
          for(int loop = 0; loop < x2 - x1; loop++){
              if(!getSolidTile(x1 + loop, y + 1, lvlData)){
                    return false;
               }
            }
         }
        return true;
    }
    
    /**
     *
     * @param lvlData : The current tile the object is on with the use of the double array (height and width of the tile)
     * @param eCollision : This gets the collision detection box of the enemy
     * @param pCollision : This gets the collision detection box of the player
     * @param yTile : This gets the y-position of the tile that they are currently on
     * @return : This returns whether the enemy is able to see the player and go to them
     */
    public static boolean getSight(int [][] lvlData, Rectangle2D.Float eCollision, Rectangle2D.Float pCollision, int yTile){
        int firstXTile = (int) (eCollision.x / Game.STILES);
        int secondXTile = (int) (pCollision.x / Game.STILES);
	
        if(firstXTile > secondXTile){
            return getWalkableTiles(secondXTile, firstXTile, yTile, lvlData);
        }else{
            return getWalkableTiles(firstXTile, secondXTile, yTile, lvlData);
        }
    }
    
    /*The following methods will basically 'draw' the levels the way I design them. I'll design them using GIMP and these methods will
    read the value of the pixel from GIMP and draw the object based on the constant value that has been assigned to each object.
    */
    
    /**
     *
     * @param img : The level data image of the specific image
     * @return : This returns the level data and draws the level the way it was designed in GIMP
     */
    public static int[][] getLvlData(BufferedImage img){
        //This is used to load the outside barrier image as well as the ground and platforms into the game correctly, the way the level was designed
   //It will read all the red pixels in the level data and load which pieces are supposed to appear in their designated space in the level
        int [][] lvlData = new int [img.getHeight()][img.getWidth()];
    
    for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getRed();
            if(num >= 48){
                num = 0;
            }
            lvlData [loop][loop1] = num;
        }
    }
    
    return lvlData;
}
    
    /**
     *
     * @param img : This will get the enemy image sprite
     * @return : It will return an array of all the images that are present in the level data for the enemy to be drawn into the level
     */
    public static ArrayList<Enemy> getEnemies(BufferedImage img){
        //This is used to load the enemies images into the game correctly, the way the level was designed
   //It will read all the green pixels in the level data and load enemies wherever they appear in their designated space in the level by looking at the constant value of the enemy
        ArrayList<Enemy> list = new ArrayList<>();
        
        for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getGreen();
            if(num == ENEMY){
                list.add(new Enemy(loop1 * Game.STILES, loop * Game.STILES));
            }
            
        }
     }
        return list;
    }
    
    /**
     *
     * @param img : The level data image of the specific level
     * @return : This will return the point at where the player will spawn into the game
     */
    public static Point getPlayerSpawn(BufferedImage img){
        //This sets the position of where the player will spawn in the level. It retreives the pixel's green value 
        //and that is where the player will spawn in the level 
       for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getGreen();
            if(num == 100){
                return new Point(loop1 * Game.STILES, loop * Game.STILES);
            }
            
        }
     } 
       return new Point(1* Game.STILES, 1 * Game.STILES);
    }
    
    /**
     *
     * @param img : The sprite image of the potion
     * @return :It will return an array of all the images that are present in the level data for the potions to be drawn into the level
     */
    public static ArrayList<Potion> getPotion(BufferedImage img){
        //This will load potions into the level based on its constant value. This will read the pixels that have the corresponding
        //blue value to the constant value and will place the potion there.
        ArrayList<Potion> list = new ArrayList<>();
        
        for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getBlue();
            if(num == PPOTION){
                list.add(new Potion(loop1 * Game.STILES, loop * Game.STILES, PPOTION));
            }
            
        }
     }
        return list;
    }
    
    /**
     *
     * @param img : The sprite image of the coin
     * @return : It will return an array of all the images that are present in the level data for the coins to be drawn into the level
     */
    public static ArrayList<Coin> getCoin(BufferedImage img){
        //This will load coins into the level based on its constant value. This will read the pixels that have the corresponding
        //blue value to the constant value and will place the coin there in the level.
        ArrayList<Coin> list = new ArrayList<>();
        
        for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getBlue();
            if(num == COIN){
                list.add(new Coin(loop1 * Game.STILES, loop * Game.STILES, PPOTION));
            }
            
        }
     }
        return list;
    }
        
    /**
     *
     * @param img : The sprite image of the containers
     * @return : It will return an array of all the images that are present in the level data for the containers to be drawn into the level
     */
    public static ArrayList<GameContainer> getContainers(BufferedImage img){
        //This will load containers into the level based on its constant value. This will read the pixels that have the corresponding
        //blue value to the constant value and will place the containers there.
        ArrayList<GameContainer> list = new ArrayList<>();
        
        for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getBlue();
            if(num == BOX || num == BARREL){
                list.add(new GameContainer(loop1 * Game.STILES, loop * Game.STILES, num));
            }
            
        }
     }
        return list;
    }
    
    /**
     *
     * @param img : The image of the spikes
     * @return :It will return an array of all the images that are present in the level data for the spikes to be drawn into the level
     */
    public static ArrayList<Spikes> getSpikes(BufferedImage img){
         //This will load spikes into the level based on its constant value. This will read the pixels that have the corresponding
        //blue value to the constant value and will place the spike there.
        ArrayList<Spikes> list = new ArrayList<>();
        
        for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getBlue();
            if(num == SPIKES){
                list.add(new Spikes(loop1 * Game.STILES, loop * Game.STILES, SPIKES));
            }
            
        }
     }
        return list;
    }
     
    /**
     *
     * @param img : The sprite image of the cannons
     * @return : It will return an array of all the images that are present in the level data for the cannons to be drawn into the level
     */
    public static ArrayList<Cannon> getCannons(BufferedImage img){
         //This will load cannons into the level based on its constant value. This will read the pixels that have the corresponding
        //blue value to the constant value and will place the cannon there.
        ArrayList<Cannon> list = new ArrayList<>();
        
        for(int loop = 0; loop < img.getHeight(); loop++){
        for(int loop1 = 0; loop1 < img.getWidth(); loop1++){
            Color colour = new Color(img.getRGB(loop1, loop));
            int num = colour.getBlue();
            if(num == LCANNON || num == RCANNON){
                list.add(new Cannon(loop1 * Game.STILES, loop * Game.STILES, num));
            }
            
        }
     }
        return list;
    }
     
     
     
    
}
