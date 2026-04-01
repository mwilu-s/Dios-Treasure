
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
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import static utilities.Constants.URMPHSIZE;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class GameOverOverlay {
    private Addition a;
    private Subtraction s;
    private Multiplication m;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;
    private URMButton menu, replay;
    
    /**
     *Creates the death screen when the player dies for the addition level
     * @param a : the addition level
     */
    public GameOverOverlay(Addition a){
        this.a = a;
        createImg();
        createButtons();
    }
    
    /**
     *Creates the death screen when the player dies for the subtraction level
     * @param s : the subtraction level
     */
    public GameOverOverlay(Subtraction s){
        this.s = s;
        createImg();
        createButtons();
    }
    
    /**
     *Creates the death screen when the player dies for the multiplication level
     * @param m : the level multiplication
     */
    public GameOverOverlay(Multiplication m){
        this.m = m;
        createImg();
        createButtons();
    }
    
    //creates the buttons and sets the position for each of them
    private void createButtons(){
        int menuX = (int) (335 * Game.SCALE);
        int playX = (int) (440 * Game.SCALE);
        int y = (int) (198 * Game.SCALE);
        menu = new URMButton(menuX, y, URMPHSIZE, URMPHSIZE, 2);
        replay = new URMButton(playX, y, URMPHSIZE, URMPHSIZE, 1);
    }
    
    //creates the background for the death screen menu and sets the position of it
    private void createImg(){
        img = LoadAndSave.getSprites(LoadAndSave.DEATHSCREEN);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GWIDTH/2 - imgW/2; //centres the image
        imgY = (int) (100 * Game.SCALE);
    }
    
    /**
     *Draws the images required for the death overlay
     * @param g: the images of the buttons being drawn as well as the background
     */
    public void draw(Graphics g){
       g.setColor(new Color(0, 0, 0, 150));
       g.fillRect(0, 0, Game.GWIDTH, Game.GHEIGHT);
       
       g.drawImage(img, imgX, imgY, imgW, imgH, null);
       
       menu.draw(g);
       replay.draw(g);
       
       
    }
    
    /**
     *updates the animations of the buttons
     */
    public void update(){
        menu.update();
        replay.update();
    }
    
    /**
     *
     * @param e : the action performed by the keyboard
     */
    public void keyPressed(KeyEvent e){
        
    }
    //@param e : the action performed by the mouse
    //b: button
    	private boolean isIn(URMButton b, MouseEvent e) {//determines whether the mouse is inside the box of the button
		return b.getBox().contains(e.getX(), e.getY());
	}

    /**
     *determines whether the mouse was moved
     * @param e : the action performed by the mouse
     */
    public void mouseMoved(MouseEvent e) {
		replay.setMouseOver(false);
		menu.setMouseOver(false);

		if (isIn(menu, e))
			menu.setMouseOver(true);
		else if (isIn(replay, e))
			replay.setMouseOver(true);
	}

    /**
     *
     * @param e : the action performed by the mouse
     * @param state : the level being played
     */
    public void mouseReleased(MouseEvent e, GameState state) {
		if (isIn(menu, e)) {
			if (menu.getMousePressed() && state == GameState.ADDITION) {
			   a.resetAll();
			   a.setGameState(GameState.MENU);
			}else if(menu.getMousePressed() && state == GameState.SUBTRACTION){
                           s.resetAll();
			   s.setGameState(GameState.MENU);
                        }else if(menu.getMousePressed() && state == GameState.MULTIPLICATION){
                           m.resetAll();
			   m.setGameState(GameState.MENU);
                        }
		} else if (isIn(replay, e)){
			if (replay.getMousePressed() && state == GameState.ADDITION){
				a.resetAll();
                                a.getGame().getAudioPlayer().setLevelSong(a.getLevelManager().getCount());
                        }else if(replay.getMousePressed() && state == GameState.SUBTRACTION){
                                s.resetAll();
                                s.getGame().getAudioPlayer().setLevelSong(s.getLevelManager().getCount());
                        }else if(replay.getMousePressed() && state == GameState.MULTIPLICATION){
                                m.resetAll();
                                m.getGame().getAudioPlayer().setLevelSong(m.getLevelManager().getCount());
                        }
                }
	menu.resetBtns();
	replay.resetBtns();
	}

    /**
     *determines whether the mouse has pressed a button
     * @param e : the action performed by the mouse
     */
    public void mousePressed(MouseEvent e) {
		if (isIn(menu, e))
			menu.setMousePressed(true);
		else if (isIn(replay, e))
			replay.setMousePressed(true);
	}

    
}
