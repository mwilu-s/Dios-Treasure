/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import GameStates.GameState;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import main.Game;
import static utilities.Constants.*;


/**
 *
 * @author User
 */
public class AudioOptions {
    
      private VolumeButton vBtn;
      private SoundButton musicBtn, sfxBtn;
      private Game game;
    
    /**
     *Creates an audio options object with all the volume and sound buttons
     * @param game : the game object
     */
    public AudioOptions(Game game){
        this.game = game;
        createSoundBtns();
        createVBtns();
    }
    
    //creates the volume button
     private void createVBtns(){
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        vBtn = new VolumeButton(vX, vY, SWIDTH, VHEIGHT);
    }
     
     //creates the music button and sound effect button
     private void createSoundBtns(){
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicBtn = new SoundButton(soundX, musicY, SOUNDSIZE, SOUNDSIZE);
        sfxBtn = new SoundButton(soundX, sfxY, SOUNDSIZE, SOUNDSIZE);
    }
     
    /**
     *updates the animations of the buttons
     */
    public void update(){
         musicBtn.update();
         sfxBtn.update();
         vBtn.update();
    }
    
    /**
     *
     * @param g: the images that will be drawn (the buttons : volume, sound)
     */
    public void draw(Graphics g){
        musicBtn.draw(g);
        sfxBtn.draw(g);
        vBtn.draw(g);
    }

    /**
     * This will determine whether the volume button has been dragged when the user is changing the volume
     * @param e : the action performed by the mouse
     */
    public void mouseDragged(MouseEvent e){
        if(vBtn.getMousePressed()){
            float vBefore = vBtn.getFloatValue();
            vBtn.changeX(e.getX());
            float vAfter = vBtn.getFloatValue();
            
            if(vBefore != vAfter){
                game.getAudioPlayer().setVolume(vAfter);
            }
        }
    }
    
    /**
     *This will determine whether mouse was pressed
     * @param e : the action performed by the mouse
     */
    public void mousePressed(MouseEvent e) {
        if(isIn(e, musicBtn)){
            musicBtn.setMousePressed(true);
        }else if(isIn(e,sfxBtn)) {
            sfxBtn.setMousePressed(true);
        }else if(isIn(e, vBtn)){
            vBtn.setMousePressed(true);
        }
             
    }

    /**
     *This will determine whether mouse was released
     *@param e : the action performed by the mouse
     */
    public void mouseReleased(MouseEvent e) {
        if(isIn(e, musicBtn)){
            if(musicBtn.getMousePressed()){
               musicBtn.setMuted(!musicBtn.getMuted());
               game.getAudioPlayer().toggleSongMute();
            }
        }else if(isIn(e,sfxBtn)){
            if(sfxBtn.getMousePressed()){
               sfxBtn.setMuted(!sfxBtn.getMuted());
               game.getAudioPlayer().toggleEffectMute();
            }
        }
       
        musicBtn.resetBtns();
        sfxBtn.resetBtns();
        vBtn.resetBtns();
    }

    /**
     *This will determine whether mouse has moved
     * @param e : the action performed by the mouse
     */
    public void mouseMoved(MouseEvent e) {
        musicBtn.setMouseOver(false);
        sfxBtn.setMouseOver(false);
        vBtn.setMouseOver(false);
        
        if(isIn(e, musicBtn)){
            musicBtn.setMouseOver(true);
        }else if(isIn(e,sfxBtn)){
            sfxBtn.setMouseOver(true);
        }else if(isIn(e, vBtn)){
            vBtn.setMouseOver(true);
        }
        
        
    }
    
    //@param e : the action performed by the mouse
    //b : the button
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBox().contains(e.getX(),e.getY());
    }
     

}
