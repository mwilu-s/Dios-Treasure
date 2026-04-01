/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import GameStates.Addition;
import GameStates.Multiplication;
import GameStates.Subtraction;
import Levels.Levels;
import static entities.Enemy.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utilities.LoadAndSave;


/**
 *
 * @author User
 */

public class EnemyManager {
    
    private Addition a;
    private Subtraction s;
    private Multiplication m;
    private BufferedImage[][] enemyArr;
    private ArrayList<Enemy> enemy = new ArrayList<>();
   
    /**
     *
     * @param a : The addition level
     */
    public EnemyManager(Addition a){
       this.a = a;
       loadEnemyImgs();
      
    }
    
    /**
     *
     * @param s : The subtraction level
     */
    public EnemyManager(Subtraction s){
       this.s = s;
       loadEnemyImgs();
      
    }
    
    /**
     *
     * @param m : The multiplication level
     */
    public EnemyManager(Multiplication m){
       this.m = m;
       loadEnemyImgs();
      
    }
    
    /**
     *Loads the enemies into the level
     * @param level : the level that the enemies will be loaded into
     */
    public void loadEnemies(Levels level){
        enemy = level.getEnemies();
    }
    
    /**
     * Updates the enemy and its states and animations
     * @param lvlData : the level data of the game
     * @param player : the player object
     */
    public void update(int [][] lvlData, Player player){
        boolean activeEnemies = false;
        
        for(Enemy e : enemy){
            if(e.getActive()){
                e.update(lvlData, player);
                activeEnemies = true;
            }
        }
        
        if(!activeEnemies){
            resetAllEnemies();
        }
         
        
    }
    
    /**
     *Draws the enemy in the game
     * @param g : the enemy image that will be drawn
     * @param xLvlOffset : the offset of the level in the horizontal
     */
    public void draw(Graphics g, int xLvlOffset){
        drawEnemy1(g, xLvlOffset); 
    }
      
      
      private void drawEnemy1(Graphics g, int xLvlOffset){//draws multiple enemies and not just one
          for(Enemy e : enemy){
              if(e.getActive()){
              g.drawImage(enemyArr[e.getState()][e.getAniIndex()], (int) e.getCollision().x - xLvlOffset - EDRAWOFFSETX + e.flipX(), (int) e.getCollision().y - EDRAWOFFSETY, EWIDTH * e.flipW(), EHEIGHT, null);
              
             
          }
         }
      }
      
    /**
     *Checks if the player has hit the enemy
     * @param attackCollision : the attack collision detection box of the player
     */
    public void checkEnemyHit(Rectangle2D.Float attackCollision){
          for(Enemy e: enemy){
              
            if(e.getActive()){
                if(e.getCurrentHealth() > 0){  
                   if(attackCollision.intersects(e.getCollision())){
                      e.damage(10);
                      return;//should only be allowed to hit 1 enemy at a time
                    }
                }
            }
        }
         
          
    }
      
    /**
     *Resets the enemies properties
     */
    public void resetAllEnemies(){
          for(Enemy e: enemy){
              e.resetEnemy();
          }
      }
    
    //loads the images of the enemy
    private void loadEnemyImgs(){
        enemyArr = new BufferedImage[5][9];
	BufferedImage temp = LoadAndSave.getSprites(LoadAndSave.ENEMYONE);
	for (int j = 0; j < enemyArr.length; j++){
	    for (int i = 0; i < enemyArr[j].length; i++){
		enemyArr[j][i] = temp.getSubimage(i * EWDEFAULT, j * EHDEFAULT, EWDEFAULT, EHDEFAULT);
            }
        }
        
        
        
    }
    
    

}
