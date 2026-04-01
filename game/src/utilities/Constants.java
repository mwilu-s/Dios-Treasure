/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import main.Game;

/**
 *
 * @author User
 */
public class Constants {
    
    //This class is used to store constants of an object image's (that will be used in the game) dimensions, width and height, 
    //and will be used to scale it to the size of the game so that everything is in proportion.
    
    public static final float GRAVITY = 0.04f * Game.SCALE;//How fast the player will fall after jumping or falling from a platform.
    public static final int ANISPEED = 6; //animation speed
    
    //Constants for the big and small clouds
    public static final int BCWDEFAULT = 448; //The default amount of pixels the big cloud is in width
    public static final int BCHDEFAULT = 101; //The default amount of pixels the big cloud is in height
    public static final int BCWIDTH = (int) (BCWDEFAULT * Game.SCALE); //The big cloud being scaled to the size of the game scale in width
    public static final int BCHEIGHT = (int) (BCHDEFAULT * Game.SCALE); //The big cloud being scaled to the size of the game scale in height
        
    public static final int SCWDEFAULT = 74; //The default amount of pixels the small cloud is in width
    public static final int SCHDEFAULT = 24; //The default amount of pixels the small cloud is in height
    public static final int SCWIDTH = (int) (SCWDEFAULT * Game.SCALE); //The small cloud being scaled to the size of the game scale in width
    public static final int SCHEIGHT = (int) (SCHDEFAULT * Game.SCALE); //The small cloud being scaled to the size of the game scale in height
      
    //Constants for the quit, play and options buttons as well as the level buttons
    public static final int BWIDTHDEFAULT = 140; //The default amount of pixels the button is in width
    public static final int BHEIGHTDEFAULT = 56; //The default amount of pixels the button is in width
    public static final int BWIDTH = (int) (BWIDTHDEFAULT * Game.SCALE); //The button being scaled to the size of the game scale in width
    public static final int BHEIGHT = (int) (BHEIGHTDEFAULT * Game.SCALE); //The button being scaled to the size of the game scale in height
        
        
    //Mute and unmute for sound effects and music buttons constants
    public static final int SOUNDSIZEDEFAULT = 42; //The default amount of pixels the sound buttons are in width and height
    public static final int SOUNDSIZE = (int) (SOUNDSIZEDEFAULT * Game.SCALE); //The sound button being scaled to the size of the game scale in width and height
        
        
    //Unpause, replay, menu, profile, help buttons constants
    public static final int URMPHDEFAULT = 56; //The default amount of pixels the urmph buttons are in width and height
    public static final int URMPHSIZE = (int) (URMPHDEFAULT * Game.SCALE); //The urmph buttons being scaled to the size of the game scale in width and height
        
    //Volume button & Slider constants
    public static final int VWDEFAULT = 28; //The default amount of pixels the volume button is in width
    public static final int VHDEFAULT = 44; //The default amount of pixels the volume button is in height
    public static final int SWDEFAULT = 215; //The default amount of pixels the volume slider is in width
            
    public static final int VWIDTH = (int) (VWDEFAULT * Game.SCALE); //The volume button being scaled to the size of the game scale in width
    public static final int VHEIGHT = (int) (VHDEFAULT * Game.SCALE); //The volume button being scaled to the size of the game scale in height
    public static final int SWIDTH = (int) (SWDEFAULT * Game.SCALE); //The volume slider being scaled to the size of the game scale in width
       
    //Constants for directions
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int UP = 1;
    public static final int DOWN = 3;
    
    
}
