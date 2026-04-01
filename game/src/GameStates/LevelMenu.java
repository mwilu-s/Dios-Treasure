/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import UI.LMButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class LevelMenu extends State implements StateMethods {
    
    private LMButton [] buttons = new LMButton[3];
    private BufferedImage bgImg;
    private int menuX, menuY, menuH, menuW;

    /**
     *Instantiates the level menu interface 
     * @param game : game object
     */
    public LevelMenu(Game game) {
        super(game);
        loadButtons();
        loadBG();
    }
    
    /**
     *loads all the buttons used for the main menu (addition, subtraction, multiplication)
     */
    public void loadButtons(){
        buttons[0] = new LMButton(Game.GWIDTH/2, (int) (150*Game.SCALE), 0, GameState.ADDITION);
        buttons[1] = new LMButton(Game.GWIDTH/2, (int) (220*Game.SCALE), 1, GameState.SUBTRACTION);
        buttons[2] = new LMButton(Game.GWIDTH/2, (int) (290*Game.SCALE), 2, GameState.MULTIPLICATION);
        
    }
    
    /**
     *loads the background of the menu for the buttons
     */
    public void loadBG(){
        bgImg = LoadAndSave.getSprites(LoadAndSave.LMENUBG);
        menuW = (int) (bgImg.getWidth() * Game.SCALE);
        menuH = (int) (bgImg.getHeight() * Game.SCALE);
        menuX = Game.GWIDTH/2 - menuW/2;
        menuY = (int) (45 * Game.SCALE);
    }
    

    @Override
    public void update() {//updates the menu buttons and its animations
        for(LMButton lmb : buttons){
            lmb.update();
        }
    }

    @Override
    //g: image to be drawn to panel
    public void draw(Graphics g) {//draws the background, buttons and the panel's background
       g.setColor(new Color(25,0,90,100));
       g.fillRect(0, 0, Game.GWIDTH, Game.GHEIGHT);
       g.drawImage(bgImg, menuX, menuY, menuW, menuH, null);
       
       for(LMButton lmb : buttons){
            lmb.draw(g);
            
        }
       
       g.setColor(Color.black);
       g.setFont(new Font("default", Font.BOLD, 20));
       g.drawString("PRESS 'ESCAPE' to return to MENU", 30, 50);
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    // e: the action of the mouse
    public void mousePressed(MouseEvent e) {//determines if the mouse is pressed
        for(LMButton lmb : buttons){
            if(pressed(e,lmb)){
                lmb.setMousePressed(true);
            }
        }
    }

    @Override
    // e: the action of the mouse
    public void mouseReleased(MouseEvent e) {//determines if the mouse has been released
        for(LMButton lmb : buttons){
            if(pressed(e,lmb)){
                if(lmb.getMousePressed()){
                    lmb.applyState();
                }
                switch(GameState.state){
                    case ADDITION:
                        game.getAudioPlayer().setLevelSong(game.getAddition().getLevelManager().getCount());
                        break;
                    case SUBTRACTION:
                        game.getAudioPlayer().setLevelSong(game.getSubtraction().getLevelManager().getCount());
                        break;
                    case MULTIPLICATION:
                        game.getAudioPlayer().setLevelSong(game.getMultiplication().getLevelManager().getCount());
                        break;
                }
                
                break;
            }
        }
        resetButtons();

    }
    
    private void resetButtons(){//resets the menu button properties
        for(LMButton lmb : buttons){
            lmb.resetButtons();
        }
    }

    @Override
    // e: the action of the mouse
    public void mouseMoved(MouseEvent e) {//determines if the mouse has moved
        for(LMButton lmb : buttons){
            lmb.setMouseOver(false);
        }
        for(LMButton lmb : buttons){
            if(pressed(e, lmb)){
                lmb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    // e: the action of the key
    public void keyPressed(KeyEvent e) {//determines if the escape key has been pressed
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
