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
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import static main.Game.*;
import static utilities.Constants.URMPHSIZE;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class PausedOverlay {
    
    private Addition a;
    private Subtraction s;
    private Multiplication m;
    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private URMButton menu, replay, unpause;
    private AudioOptions audioOpt;
    
    /**
     *This creates a pause menu when playing the game with all the necessary buttons for the addition level
     * @param a : the addition level
     */
    public PausedOverlay(Addition a){
        this.a = a;
        loadBackground();
        audioOpt = a.getGame().getAudioOptions();
        createURMBtns();
        
    }
    
    /**
     *This creates a pause menu when playing the game with all the necessary buttons for the subtraction level
     * @param s : the subtraction level
     */
    public PausedOverlay(Subtraction s){
        this.s = s;
        loadBackground();
        audioOpt = s.getGame().getAudioOptions();
        createURMBtns();
        
    }
    
    /**
     *This creates a pause menu when playing the game with all the necessary buttons for the multiplication level
     * @param m : the multiplication level
     */
    public PausedOverlay(Multiplication m){
        this.m = m;
        loadBackground();
        audioOpt = m.getGame().getAudioOptions();
        createURMBtns();
        
    }
    
    //creates the unpause, replay and menu buttons
    private void createURMBtns(){
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);
        
        menu = new URMButton(menuX, bY, URMPHSIZE, URMPHSIZE, 2);
        replay = new URMButton(replayX, bY, URMPHSIZE, URMPHSIZE, 1);
        unpause = new URMButton(unpauseX, bY, URMPHSIZE, URMPHSIZE, 0);
    }
    
    
    //loads the background for the pause menu
    private void loadBackground(){
        background = LoadAndSave.getSprites(LoadAndSave.PAUSEMENU);
        bgW = (int) (background.getWidth() * Game.SCALE);
        bgH = (int) (background.getHeight() * Game.SCALE);
        bgX = GWIDTH/2 - bgW/2;
        bgY = (int) (25 * Game.SCALE);
    }
    
    /**
     *updates the animations of the buttons
     */
    public void update(){
        menu.update();
        replay.update();
        unpause.update();
        
        audioOpt.update();
    }
    
    /**
     *draws the images into the game
     * @param g : the images that are to be drawn (background, buttons)
     */
    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, Game.GWIDTH, Game.GHEIGHT);
        //background
        g.drawImage(background,bgX, bgY, bgW, bgH,null);
        
        //URM
        menu.draw(g);
        replay.draw(g);
        unpause.draw(g);
        
        audioOpt.draw(g);
        
    }
    
    /**
     *This will determine whether the volume is being changed by the user by dragging the volume button
     * @param e : the action performed by the mouse
     */
    public void mouseDragged(MouseEvent e){
        audioOpt.mouseDragged(e);
    }
    
    /**
     *This determines if a button has been pressed
     * @param e : the action performed by the mouse
     */
    public void mousePressed(MouseEvent e) {
       if(isIn(e, menu)){
            menu.setMousePressed(true);
        }else if(isIn(e, replay)){
            replay.setMousePressed(true);
        }else if(isIn(e, unpause)){
            unpause.setMousePressed(true);
        }else{
            audioOpt.mousePressed(e);
        }
             
    }

    /**
     *This determines if the button has been released
     * @param e : the action performed by the mouse
     * @param state : the level being played
     */
    public void mouseReleased(MouseEvent e, GameState state) {
        if(isIn(e,menu)){
            if(menu.getMousePressed() && state == GameState.ADDITION ){
               a.resetAll();
               a.setGameState(GameState.MENU);
               a.unpauseGame();
               
        }else if(menu.getMousePressed() && state == GameState.SUBTRACTION){
               s.resetAll();
               s.setGameState(GameState.MENU);
               s.unpauseGame();
               
        }else if(menu.getMousePressed() && state == GameState.MULTIPLICATION){
               m.resetAll();
               m.setGameState(GameState.MENU);
               m.unpauseGame();
        }
        }else if(isIn(e,replay)){
            if(replay.getMousePressed() && state == GameState.ADDITION){
                a.resetAll();
                a.unpauseGame();
            }else if(replay.getMousePressed() && state == GameState.SUBTRACTION){
                s.resetAll();
                s.unpauseGame();
            }else if(replay.getMousePressed() && state == GameState.MULTIPLICATION){
                m.resetAll();
                m.unpauseGame();
            }
               
        }else if(isIn(e,unpause)){
            if(unpause.getMousePressed() && state == GameState.ADDITION){
                a.unpauseGame();
            }else if(unpause.getMousePressed() && state == GameState.SUBTRACTION){
                s.unpauseGame();
            }else if(unpause.getMousePressed() && state == GameState.MULTIPLICATION){
                m.unpauseGame();
            }
               
        }else{
            audioOpt.mouseReleased(e);
        }
       
       
        menu.resetBtns();
        unpause.resetBtns();
        replay.resetBtns();
       
    }

    /**
     *This determines if the mouse is moving inside the button or over it
     * @param e : the action performed by the mouse
     */
    public void mouseMoved(MouseEvent e) {
        
        menu.setMouseOver(false);
        unpause.setMouseOver(false);
        replay.setMouseOver(false);
     
        
        if(isIn(e,menu)){
            menu.setMouseOver(true);
        }else if(isIn(e,replay)){
            replay.setMouseOver(true);
        }else if(isIn(e,unpause)){
            unpause.setMouseOver(true);
        }else {
            audioOpt.mouseMoved(e);
        }
        
            
        
    }
    
    //b: button
    //@param e : the action performed by the mouse
    private boolean isIn(MouseEvent e, URMButton b){//determines if the mouse is inside the box of the button
        return b.getBox().contains(e.getX(),e.getY());
            
        
    }
    
}
