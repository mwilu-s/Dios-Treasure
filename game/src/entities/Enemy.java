/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
import java.awt.geom.Rectangle2D;
import main.Game;

import static utilities.HelpMethods.*;
import static utilities.Constants.*;

/**
 *
 * @author User
 */
public class Enemy extends Entity{
    
    private int attackOffset;
    
    //Constants for the enemy

    /**
     *The colour the enemy will be drawn in the level data
     */
    public static final int ENEMY = 0;
        
    /**
     *The animation where the enemy is idle and walking
     */
    protected static final int ENEMYIDLE = 0;

    /**
     *The animation where the enemy is running
     */
    protected static final int ENEMYRUNNING = 1;

    /**
     *The animation where the enemy attacks the player
     */
    protected static final int ENEMYATTACK = 2;

    /**
     *The animation for when the enemy is hit by the player
     */
    protected static final int ENEMYHIT = 3;

    /**
     *The animation for when the enemy is dying
     */
    protected static final int ENEMYDEAD = 4;
        
    /**
     *The default width of the sprite of the enemy image (72 pixels in width)
     */
    protected static final int EWDEFAULT = 72;

    /**
     *The default height of the sprite of the enemy image (32 pixels in height)
     */
    protected static final int EHDEFAULT = 32;
        
    /**
     *The width of the enemy scaled to fit in the game
     */
    protected static final int EWIDTH = (int) (EWDEFAULT * Game.SCALE);

    /**
     *The height of the enemy scaled to fit in the game
     */
    protected static final int EHEIGHT = (int) (EHDEFAULT * Game.SCALE);
        
    /**
     *
     */
    protected static final int EDRAWOFFSETX = (int) (26*Game.SCALE); // This is the difference of where the sprite image starts and 
    //where the collision box will start. The space between the two points in the width (x) and height (y)

    /**
     *
     */
    protected static final int EDRAWOFFSETY = (int) (9*Game.SCALE);


        
    private int getEnemySpriteAmt(int enemyState){//gets the state of the enemy and will return the amount of sprites for that animation
                switch(enemyState){
                    case ENEMYIDLE:
                        return 9;
                    case ENEMYRUNNING:
                        return 6;
                    case ENEMYATTACK:
                        return 7;
                    case ENEMYHIT:
                        return 4;
                    case ENEMYDEAD:
                        return 5;
                }
        
            return 0;
    }
        
    /**
     *checks if the enemy is not in the air at the start
     */
    protected boolean firstUpdate = true;

    /**
     * the direction of the enemy when they are walking
     */
    protected int walkDir = LEFT;

    /**
     *the tile, in the y-position, that the enemy is on
     */
    protected int tileY;

    /**
     * The attacking distance range of tiles
     */
    protected float attackDistance = Game.STILES;

    /**
     *
     */
    protected boolean active = true; //enemy is active when the game has started

    /**
     *checks if the enemy has attacked the player
     */
    protected boolean attackChecked;
    
    /**
     *
     * @param x: the x-value of the enemy
     * @param y: the y-value of the enemy
     */
    public Enemy(float x, float y) {
        super(x, y, EWIDTH, EHEIGHT);
        maxHealth = 20;
        currentHealth = maxHealth;
        walkSpeed = Game.SCALE * 0.35f;
        initCollision(22 ,19);
        initAttackCollision();
    }
    
     private void initAttackCollision(){//creates the attack collision detection box for the enemy when it attacks
        attackCollision = new Rectangle2D.Float(x, y,(int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackOffset = (int)(Game.SCALE * 30);
    }
     
    /**
     *
     */
    public void resetEnemy(){ //resets all the properties of the enemy
        collision.x = x;
        collision.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(ENEMYIDLE);
        active = true;
        airSpeed = 0;
    }
    
    /**
     *
     */
    protected void updateAT(){//updates the animation of the enemy
        aniTick++;
        if (aniTick >= ANISPEED) {
	    aniTick = 0;
	    aniIndex++;
	    if (aniIndex >= getEnemySpriteAmt(state)) {
		aniIndex = 0;
		    switch (state) {
			case ENEMYHIT:
                            state = ENEMYIDLE;
                            break;
                        case ENEMYATTACK:
                            state = ENEMYIDLE;
                            break;
		        case ENEMYDEAD:
                            active = false;
                            break;
				}
                             
			} 
                        
		}

    }
    
    /**
     *
     * @param lvlData: The level data of the game
     */
    protected void firstUpdateCheck(int [][] lvlData){
        if(!notOnFloor(collision, lvlData)){//this will make the enemy touch the ground
                inAir = true;
            }
        firstUpdate = false;
    }
    
    /**
     *
     * @param lvlData : the level data of the game
     */
    protected void move(int [][] lvlData){
        float xSpeed = 0;
        if(walkDir == LEFT){
            xSpeed = -walkSpeed;
            }else{
                xSpeed = walkSpeed;
             }
                  
        if(detection(collision.x + xSpeed, collision.y, collision.width, collision.height, lvlData)){
            if(isFloor(collision, xSpeed, lvlData)){
                    collision.x += xSpeed;
                    return; //To get out of this if statement
                }
            }
                  
        changeWalkDir();
    }
    
    /**
     *
     * @param lvlData : the level data of the game
     * @param player : the player object
     * @return : A boolean value to determines if the enemy can see the player
     */
    protected boolean seePlayer(int [][] lvlData, Player player){
        int playerTileY = (int) (player.getCollision().y / Game.STILES);
        if(playerTileY == tileY){
            if(getPlayerRange(player)){
                if(getSight(lvlData, collision, player.collision, tileY)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param player : the player object
     */
    protected void goToPlayer(Player player){//check if player is on the left or right side of enemy
        if(player.collision.x > collision.x){
            walkDir = RIGHT;
        }else{
            walkDir = LEFT;
        }
    }
    
    /**
     *
     * @param player : the player object
     * @return : whether the player is in attacking distance for the enemy to attack
     */
    protected boolean getAttackRange(Player player){
        int num = (int) Math.abs(player.collision.x - collision.x);
        return num <= attackDistance;
    }
    
    /**
     *
     * @param player : the player object
     * @return : whether the player is in range of the enemy
     */
    protected boolean getPlayerRange(Player player){
        int num = (int) Math.abs(player.collision.x - collision.x);
        return num <= attackDistance * 5;
    }
    
    /**
     *
     * @param enemyState : the state of the animation the enemy is in
     */
    protected void newState(int enemyState){//change the current enemy state it has to the one we give it
        this.state = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }
    
    /**
     *
     * @param amt : the amount of damage the enemy will receive, affecting its health
     */
    public void damage(int amt){
        currentHealth -= amt;
        if(currentHealth <= 0){
            newState(ENEMYDEAD);
        }else{
            newState(ENEMYHIT);
        }
    }

    /**
     *Checks to see if the enemy is attacking the player
     * @param ac : the attack collision detection box of the enemy
     * @param player : the player object
     */
    protected void checkPlayerHit(Rectangle2D.Float ac, Player player){
        if(ac.intersects(player.collision)){
            player.setHealth(-10);
        }
        attackChecked = true;
    }
    
    /**
     *Changes the direction the enemy is walking in
     */
    protected void changeWalkDir(){
        if(walkDir == LEFT){
            walkDir = RIGHT;
        }else{
            walkDir = LEFT;
        }
    }
    
    /**
     *
     * @return : whether the enemy is present in the game or not
     */
    public boolean getActive(){
        return active;
    }
    
    /**
     *Checks if the enemy is in the air 
     * @param lvlData : the level data of the game
     */
    protected void updateInAir(int [][] lvlData){
        if(detection(collision.x, collision.y + airSpeed, collision.width, collision.height, lvlData)){
            collision.y += airSpeed;
            airSpeed += GRAVITY;
        }else{
            inAir = false;
            collision.y = getEntityYPosUnderROrAboveF(collision, airSpeed);
            tileY = (int) (collision.y/Game.STILES);
        }
    }
    
    /**
     *Updates the state of the enemy
     * @param lvlData : the level data of the game
     * @param player : the player object
     */
    public void updateBehaviour(int[][] lvlData, Player player ){
        if(firstUpdate){
            firstUpdateCheck(lvlData);
        }
        
        if(inAir){
            updateInAir(lvlData);
        }else{
            switch(state){
                case ENEMYIDLE:
                    newState(ENEMYRUNNING);
                    break;
                case ENEMYRUNNING:
                    if(seePlayer(lvlData, player)){
                        goToPlayer(player);
                        if(getAttackRange(player)){
                            newState(ENEMYATTACK);
                        }
                    }
                    move(lvlData);
                    break;
                case ENEMYATTACK:
                    if(aniIndex == 0){
                        attackChecked = false;
                    }
                    if(aniIndex == 3 && !attackChecked){
                        checkPlayerHit(attackCollision, player);
                        
                    }
                    break;
                case ENEMYHIT:
                    break;
            }
        }
          
        
    }
    
    /**
     *
     * @return : the image flip of the enemy
     */
    public int flipX(){
        if(walkDir == RIGHT){
            return width;
        }
        else{
            return 0;
        }
    }
    
    /**
     *
     * @return : the image flip of the enemy
     */
    public int flipW(){
        if(walkDir == RIGHT){
            return -1;
        }
        else{
            return 1;
        }
    }
    
    /**
     *Updates the animations and states of the enemy
     * @param lvlData: the level data of the game
     * @param player: the player object
     */
    public void update(int[][] lvlData, Player player){
        updateBehaviour(lvlData, player);
        updateAT();
        updateAttackCollision();
    }
    
    //moves the attack collision detection box when the enemy moves
    private void updateAttackCollision(){
        attackCollision.x = collision.x - attackOffset;
        attackCollision.y = collision.y;
    }
        
  
    
    
}
