/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import entities.Enemy;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static utilities.HelpMethods.*;
import java.util.ArrayList;
import main.Game;
import objects.Cannon;
import objects.Coin;
import objects.GameContainer;
import objects.Potion;
import objects.Spikes;
import utilities.HelpMethods;

/**
 *
 * @author User
 */
public class Levels {
    
    private BufferedImage img;
    private int [][] lvlData;
    
    private ArrayList<Enemy> enemy;
    
    
    private ArrayList<Potion> potion;
    private ArrayList<Coin> coin;
    private ArrayList<GameContainer> containers;
    private ArrayList<Cannon> cannons;
    private ArrayList<Spikes> spike;
  
    
            
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    
    private Point playerSpawn;
    
    /**
     *
     * @param img : The level data image
     */
    public Levels(BufferedImage img){
        //Instantiates a level based on its data which is based on the level number.
        this.img = img;
        createLevelData();
        createEnemies();
        createPotions();
        createCoins();
        createContainers();
        createSpikes();
        createCannons();
        calcLvlOffsets();
	calcPlayerSpawn();
        
        
    }
    
    private void createCannons() {
        //This creates all the cannons needed in the level
		cannons = HelpMethods.getCannons(img);
	}

	private void createSpikes() {
            //This creates all the spikes needed in the level
		spike = HelpMethods.getSpikes(img);
	}

	private void createContainers() {
            //This creates all the containers needed in the level
		containers = HelpMethods.getContainers(img);
	}

	private void createPotions() {
            //This creates all the potions needed in the level
		potion = HelpMethods.getPotion(img);
	}

	private void calcPlayerSpawn() {
            //This calculates where the player will spawn
		playerSpawn = getPlayerSpawn(img);
	}
        
        private void createEnemies() {
            //This creates all the enemies needed in the level
		enemy = HelpMethods.getEnemies(img);
	}
        
       
        
        private void createCoins(){
            //This creates all the coins needed in the level
            coin = getCoin(img);
        }

	private void createLevelData() {
            //This will return the data of the level needed to draw the level.
		lvlData = getLvlData(img);
	}
        
    /**
     *
     * @param x : the x tile of the level data
     * @param y : the y tile of the level data
     * @return: returns the specific tile of the level data
     */
    public int getSpriteIndex(int x, int y){
        //This retrieves the index of the level data to draw the outside sprite
        return lvlData[y][x];
    }
    
    /**
     *
     * @return : the level data 
     */
    public int [][] getLevelData(){
        //This will return the data of the level needed to draw the level.
        return lvlData;
    }
    
    /**
     *
     * @return : the maximum offset of the level horizontally
     */
    public int getLvlOffset(){
        //This returns the offset of the level
        return maxLvlOffsetX;
    }
    
    /**
     *
     * @return : returns an array list of enemies
     */
    public ArrayList<Enemy> getEnemies(){
        //This returns enemies.
        return enemy;
    }
    
    /**
     *
     * @return : the point where the player will spawn into the level
     */
    public Point getSpawn(){
        //This will return the spawn point at which the player is to spawn.
        return playerSpawn;
    }
    
    
    private void calcLvlOffsets(){
        //This calculates the offset of the level and what the user sees when they move to the left or right of the screen
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.WTILES;
        maxLvlOffsetX = Game.STILES * maxTilesOffset;
    }
    
    /**
     *
     * @return : an array list of potions
     */
    public ArrayList<Potion> getPotions(){
        //returns potions
        return potion;
    }
    
    /**
     *
     * @return : an array list of coins
     */
    public ArrayList<Coin> getCoins(){
        //returns coins
        return coin;
    }
    
    /**
     *
     * @return : an array list of containers
     */
    public ArrayList<GameContainer> getGameContainers(){
        //returns the game containers
        return containers;
    }
    
    /**
     *
     * @return : an array list of spikes
     */
    public ArrayList<Spikes> getSpikes(){
        //returns the spikes
        return spike;
    }
    
    /**
     *
     * @return : an array list of cannons
     */
    public ArrayList<Cannon> getCannon(){
        //returns the cannons
        return cannons;
    }
    
  
    
   
}
