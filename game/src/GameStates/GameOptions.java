/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import GUIs.Profile;
import UI.AudioOptions;
import UI.PHButton;
import UI.PauseButton;
import UI.URMButton;
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
public class GameOptions extends State implements StateMethods {

    private AudioOptions audioOpt;
    private BufferedImage optionBGImg;
    private int bgX, bgY, bgW, bgH;
    private URMButton menuB;
    private PHButton profileB;
    
    /**
     *Instantiates the options menu interface
     * @param game : game object
     */
    public GameOptions(Game game) {
        super(game);
        loadBtns();
        loadImgs();
        audioOpt = game.getAudioOptions();
    }
    
    //loads all the images for the options menu interface
    private void loadImgs(){
        optionBGImg = LoadAndSave.getSprites(LoadAndSave.OPTIONSBG);
        bgW = (int) (Game.SCALE * optionBGImg.getWidth());
        bgH = (int) (Game.SCALE * optionBGImg.getHeight());
        bgX = Game.GWIDTH / 2 - bgW / 2;
        bgY = (int) (Game.SCALE * 33);
    }
    
    //loads the help and profile buttons
    private void loadBtns(){
        int menuX = (int) (335 * Game.SCALE);
        int menuY = (int) (325 * Game.SCALE);
        
        int profileX = (int) (440 * Game.SCALE);
        int profileY = (int) (325 * Game.SCALE);
        
        menuB = new URMButton(menuX, menuY, URMPHSIZE, URMPHSIZE, 2);
        profileB = new PHButton(profileX, profileY, URMPHSIZE, URMPHSIZE, 0);
    }

    @Override
    public void update() {//updates the buttons and volume slider animations
        menuB.update();
        profileB.update();
        audioOpt.update();
    }

    @Override
    //g: the image that will be drawn
    public void draw(Graphics g) {//draws all the images
       g.setColor(new Color(25,0,90,100));
       g.fillRect(0, 0, Game.GWIDTH, Game.GHEIGHT);
       
       g.drawImage(optionBGImg, bgX, bgY, bgW, bgH, null);
       menuB.draw(g);
       profileB.draw(g);
       audioOpt.draw(g);
    }
    
    /**
     *The mouse will be dragged when the user changes the volume
     * @param e : the action of the mouse
     */
    public void mouseDragged(MouseEvent e){
        audioOpt.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    //determines if the mouse has been pressed
    // * @param e : the action of the mouse
    public void mousePressed(MouseEvent e) {
        if(isIn(e, menuB)){
            menuB.setMousePressed(true);
        }else if(isIn(e, profileB)){
            profileB.setMousePressed(true);
        }else{
            audioOpt.mousePressed(e);
        }
    }

    @Override
    //determines if the mouse has been released
    // * @param e : the action of the mouse
    public void mouseReleased(MouseEvent e) {
        if(isIn(e, menuB)){
            if(menuB.getMousePressed()){
                GameState.state = GameState.MENU;
            } 
        }else if(isIn(e,profileB)){
            if(profileB.getMousePressed()){
                Profile p = new Profile();
                p.show();

            }
        }else{
            audioOpt.mouseReleased(e);
        }
        
        menuB.resetBtns();
        profileB.resetBtns();
    }

    @Override
    //determines if the mouse has been moved
    // * @param e : the action of the mouse
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        profileB.setMouseOver(false);
        if(isIn(e, menuB)){
            menuB.setMouseOver(true);
        }else if(isIn(e,profileB)){
            profileB.setMouseOver(true);
        }else{
            audioOpt.mouseMoved(e);
        }
    }

    @Override
    //determines if the key has been pressed
    // * @param e : the action of the keyboard
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    //b: the pause button object
    // * @param e : the action of the mouse
    private boolean isIn(MouseEvent e, PauseButton b){//returns the box of the button
        return b.getBox().contains(e.getX(),e.getY());
    }
    
    
}
