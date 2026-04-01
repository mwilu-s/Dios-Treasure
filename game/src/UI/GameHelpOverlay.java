/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import GameStates.GameState;
import GameStates.Addition;
import GameStates.Multiplication;
import GameStates.Subtraction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import main.Game;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class GameHelpOverlay {
    
    private Addition a;
    private Subtraction s;
    private Multiplication m;
    private BufferedImage help1, help2;
    private int imgX1,imgX2, imgY, imgW1,imgW2,imgH2, imgH1;
    
    /**
     This creates a help overlay when playing the game with all the instructions of the game for the addition level
     * @param a : the addition level
     */
    public GameHelpOverlay(Addition a){
        this.a = a;
        loadHelpImgs();
        
    }
    
    /**
     * This creates a help overlay when playing the game with all the instructions of the game for the subtraction level
     * @param s: the subtraction level
     */
    public GameHelpOverlay(Subtraction s){
        this.s = s;
        loadHelpImgs();
        
    }
    
    /**
     * This creates a help overlay when playing the game with all the instructions of the game for the multiplication level
     * @param m: the multiplication level
     */
    public GameHelpOverlay(Multiplication m){
        this.m = m;
        loadHelpImgs();
        
    }
    
    //loads the help images and positions them on the screen
    private void loadHelpImgs(){
        help1 = LoadAndSave.getSprites(LoadAndSave.HELP1);
        help2 = LoadAndSave.getSprites(LoadAndSave.HELP2);
        imgW1 = (int) (help1.getWidth()/5 * Game.SCALE);
        imgH1 = (int) (help1.getHeight()/5 * Game.SCALE);
        imgW2 = (int) (help2.getWidth()/5 * Game.SCALE);
        imgH2 = (int) (help2.getHeight()/5 * Game.SCALE);
        imgX1 = 65;
        imgX2 = 650;
        imgY = (int) (50 * Game.SCALE);
    }
    
    /**
     *
     */
    public void update(){
      
    }
    
    /**
     *Draws the help images as well as dimming the background
     * @param g: the help images to be drawn
     */
    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, Game.GWIDTH, Game.GHEIGHT);
        //background
        g.drawImage(help1,imgX1, imgY, imgW1, imgH1,null);
        g.drawImage(help2,imgX2, imgY, imgW2, imgH2,null);
    }
    
    /**
     *This determines if the escape button has been pressed to exit the help overlay for each level
     * @param e : the action performed by the keyboard
     */
    public void keyPressed(KeyEvent e){
        switch(GameState.state){
            case ADDITION:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    a.setGameHelp(false);
                }
                break;
            case SUBTRACTION:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    s.setGameHelp(false);
                }
                break;
            case MULTIPLICATION:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                   m.setGameHelp(false);
                }
                break;
        }
        
    }
    
    
}
