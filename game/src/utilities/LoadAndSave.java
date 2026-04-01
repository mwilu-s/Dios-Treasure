/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;


/**
 *
 * @author User
 */
public class LoadAndSave {
    
    //This class is used for loading all sprite images, levels and backgrounds that are used in the game
    
    //Game objects/characters

    /**
     *The sprite for the player
     */
    public static final String PLAYER = "player_sprites.png";

    /**
     *The sprite for the enemy
     */
    public static final String ENEMYONE = "crabby_sprite.png";

    /**
     * The sprite for the potion
     */
    public static final String POTIONS = "potions_sprites.png";

    /**
     * The sprite for the containers
     */
    public static final String CONTAINERS = "objects_sprites.png"; 

    /**
     * The image of the spikes
     */
    public static final String TRAP = "trap.png";

    /**
     * The sprite for the cannons
     */
    public static final String CANNON = "cannon.png";

    /**
     * The image of the cannon ball
     */
    public static final String CANNONBALL = "ball.png";

    /**
     * The sprite for the coin
     */
    public static final String COINS = "coin.png";
    
    //Buttons

    /**
     * The sprite for the menu buttons (play, options, quit)
     */
    public static final String MENUBUTTONS = "menu_buttons.png";

    /**
     * The level menu button sprite (multiplication, addition. subtraction)
     */
    public static final String LMBTNS = "levelmenu_buttons.png";

    /**
     * The sprite for the sound effects and music buttons
     */
    public static final String SOUNDBTNS = "sound_button.png";

    /**
     * The sprite for the unpause, replay and menu buttons
     */
    public static final String URMBTNS = "urm_buttons.png";

    /**
     *The sprite for the profile and help buttons
     */
    public static final String PHBTNS = "ph_Buttons.png";

    /**
     *The sprite for the volume button slider
     */
    public static final String VOLUMEBTNS = "volume_buttons.png";
    
    //Backgrounds

    /**
     * The image of the outside layer of the levels
     */
    public static final String LEVEL = "outside_sprites.png";

    /**
     * The background for the menu buttons
     */
    public static final String MENUBG = "menu_background.png";

    /**
     * The background for the level menu buttons
     */
    public static final String LMENUBG = "levelmenu.png";

    /**
     * The background for the pause menu
     */
    public static final String PAUSEMENU = "pause_menu.png";

    /**
     * The background for the game screen
     */
    public static final String DAYBG = "day_background.png";

    /**
     * The background for the game screen
     */
    public static final String NIGHTBG = "night_background.png";

    /**
     * The image of the clouds in the background for the game screen
     */
    public static final String BIGCLOUD = "big_clouds.png";

    /**
     * The image of the clouds in the background for the game screen
     */
    public static final String SMALLCLOUD = "small_clouds.png";

    /**
     * The image of the health bar
     */
    public static final String HEALTHBAR = "health_bar.png"; 

    /**
     * The death screen background
     */
    public static final String DEATHSCREEN = "deathscreen.png";

    /**
     * The options menu background
     */
    public static final String OPTIONSBG = "options_background.png";
    
    //Help

    /**
     * The first help image that contains the controls of the game
     */
    public static final String HELP1 = "help1.png";

    /**
     * The second help image that contains how to play the game
     */
    public static final String HELP2 = "help2.png";
   
    /**
     *
     * @param file : The file is the image that is needed to be retrieved from all the constant values above
     * @return : It returns a buffered image of that file that was retrieved
     */
    public static BufferedImage getSprites(String file){//this retrieves the file of the background or sprite image
        BufferedImage img = null;
        InputStream is = LoadAndSave.class.getResourceAsStream("/" + file);
        try {
          img = ImageIO.read(is); 
          
           
        }
       catch(IOException e){
           
           e.printStackTrace();
       }
        finally{
            try{
                is.close();
            }
            catch(IOException e){
                
                e.printStackTrace();
            }
        }
        return img;
    }
    
    /**
     *
     * @return : This returns an array of all the levels (three levels) so that the correct image is loaded for the correct level
     */
    public static BufferedImage[] getAllLevels(){//This gets all the levels of the game FIX
        URL url = LoadAndSave.class.getResource("/lvls");
        File file = null;
        try{
        file = new File(url.toURI());
        }catch(URISyntaxException e){
            
        }
        File [] files = file.listFiles();
        File [] filesSort = new File[files.length];
        
        for(int loop = 0; loop < filesSort.length; loop++){//puts the levels in order when extracting the files from the file "lvls"
            for(int loop1 = 0; loop1 < files.length; loop1++){
                if(files[loop1].getName().equals("" + (loop + 1) + ".png")){
                    filesSort[loop] = files[loop1];
                }
            }
        }
        
        BufferedImage[] imgs = new BufferedImage [filesSort.length];
        
        for(int loop = 0; loop < imgs.length; loop++){
            try{
            imgs[loop] = ImageIO.read(filesSort[loop]);
            }catch(IOException e){
                
            }
        }
        
        return imgs;
    }
    
    
    
    
}
