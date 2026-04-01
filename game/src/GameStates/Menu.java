/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import UI.MenuButton;
import java.awt.Color;
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
public class Menu extends State implements StateMethods {
    
    private MenuButton [] buttons = new MenuButton[3];
    private BufferedImage bgImg;
    private int menuX, menuY, menuH, menuW;
    
    /**
     *Instantiates the main menu interface 
     * @param game : game object
     */
    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBG();
    }
    
    /**
     *loads all the buttons used for the main menu (play, options, quit)
     */
    public void loadButtons(){
        buttons[0] = new MenuButton(Game.GWIDTH/2, (int) (150*Game.SCALE), 0, GameState.LEVELMENU);
        buttons[1] = new MenuButton(Game.GWIDTH/2, (int) (220*Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GWIDTH/2, (int) (290*Game.SCALE), 2, GameState.QUIT);
    }
    
    /**
     *loads the background of the menu for the buttons
     */
    public void loadBG(){
        bgImg = LoadAndSave.getSprites(LoadAndSave.MENUBG);
        menuW = (int) (bgImg.getWidth() * Game.SCALE);
        menuH = (int) (bgImg.getHeight() * Game.SCALE);
        menuX = Game.GWIDTH/2 - menuW/2;
        menuY = (int) (45 * Game.SCALE);
    }
    
    @Override
    public void update(){//updates the menu buttons and its animations
        for(MenuButton mb : buttons){
            mb.update();
        }
    }
    

    @Override
    //g: image to be drawn to panel
    public void draw(Graphics g) {//draws the background, buttons and the panel's background
        
       g.setColor(new Color(25,0,90,100));
       g.fillRect(0, 0, Game.GWIDTH, Game.GHEIGHT);
       g.drawImage(bgImg, menuX, menuY, menuW, menuH, null);
       for(MenuButton mb : buttons){
            mb.draw(g);
            
        }
       
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    // e: the action of the mouse
    public void mousePressed(MouseEvent e) {//determines if the mouse is pressed
        for(MenuButton mb : buttons){
            if(pressed(e,mb)){
                mb.setMousePressed(true);
            }
        }
        
    }

    @Override
    // e: the action of the mouse
    public void mouseReleased(MouseEvent e) {//determines if the mouse has been released
        for(MenuButton mb : buttons){
            if(pressed(e,mb)){
                if(mb.getMousePressed()){
                    mb.applyState();
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons(){//resets the menu button properties
        for(MenuButton mb : buttons){
            mb.resetButtons();
        }
    }
    
    @Override
    // e: the action of the mouse
    public void mouseMoved(MouseEvent e) {//determines if the mouse has moved
        for(MenuButton mb : buttons){
            mb.setMouseOver(false);
        }
        for(MenuButton mb : buttons){
            if(pressed(e, mb)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    // e: the action of the key
    public void keyPressed(KeyEvent e) {//determines if the p key has been pressed
        if(e.getKeyCode() == KeyEvent.VK_P){
            //GameState.state = GameState.PLAYING;
        }
    }

    @Override
    
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
