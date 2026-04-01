/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import GameStates.GameState;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class LevelManager {
    
    private Game g;
    private BufferedImage [] level;
    private ArrayList<Levels> levels;
    private int count;
    
    /**
     *
     * @param g : the game object
     */
    public LevelManager(Game g){
        //This builds every single level in the folder
        this.g = g;
        importOutsideSpirte();
        levels = new ArrayList<>();
        buildAllLevels();
    }
    
    private void buildAllLevels(){
        //This draws components of the level.
        BufferedImage [] allLevels = LoadAndSave.getAllLevels();
        
        for(BufferedImage img : allLevels){
            levels.add(new Levels(img));
        }
    }
    
    /**
     *
     * @param g : the image to be drawn
     * @param lvlOffset: the offset of the level and what can be seen when the player moves
     */
    public void draw(Graphics g, int lvlOffset){
        //This draws components of the level.
        for (int loop = 0; loop < Game.HTILES; loop++){
            for(int loop2 = 0; loop2 < levels.get(count).getLevelData()[0].length; loop2++){
                int index = levels.get(count).getSpriteIndex(loop2, loop);
                g.drawImage(level[index], Game.STILES * loop2 - lvlOffset, Game.STILES * loop, Game.STILES, Game.STILES, null); 
            }
        }
    }
    
    /**
     *
     */
    public void update(){
        
    }

    private void importOutsideSpirte() {
        //This retrieves the border sprites needed for each level
        BufferedImage img = LoadAndSave.getSprites(LoadAndSave.LEVEL);
        level = new BufferedImage[48];
        for (int loop = 0; loop < 4; loop++){
            for (int loop1 = 0; loop1 < 12; loop1++){
                int index = loop *12 + loop1;
                level[index] = img.getSubimage(loop1 * 32, loop * 32, 32, 32);
            }
        }
    }
    
    /**
     *
     * @return : the current level the user is on
     */
    public Levels getCurrentLevel(){
        //returns the level that the player is currently playing
        return levels.get(count);
    }
    
    /**
     *
     * @return : the amount of levels that are available
     */
    public int getLevelAmount(){
        //returns the amount of levels
        return levels.size();
    }
    
    /**
     *
     * @return : the number that will correspond with which level data that is to be drawn
     */
    public int getCount(){
        //this gets the specific level data for the level
        switch(GameState.state){
            case ADDITION:
                count = 0;
                break;
            case SUBTRACTION:
                count = 1;
                break;
            case MULTIPLICATION:
                count = 2;
                break;
        }
        return count;
    }
    
}
