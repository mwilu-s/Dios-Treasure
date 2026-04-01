/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;



import GameStates.GameOptions;
import GameStates.GameState;
import GameStates.LevelMenu;
import GameStates.Menu;
import GameStates.Addition;
import GameStates.Multiplication;
import GameStates.Subtraction;
import UI.AudioOptions;
import audio.AudioPlayer;
import java.awt.Graphics;


/**
 *
 * @author User
 */
public class Game implements Runnable{
    
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS = 60;
    private final int UPS = 90;
    
    
   
    private Addition a;
    private Subtraction s;
    private Multiplication m;
    private Menu menu;
    private GameOptions gOptions;
    private LevelMenu lmenu;
    private AudioOptions audioOpt;
    private AudioPlayer audioPlayer;
    
    /**
     * The default size of the tiles (32 pixels)
     */
    public static final int DTILES = 32;

    /**
     * The scale that everything will be drawn to
     */
    public static final float SCALE = 1.5f;

    /**
     * The width of the tiles
     */
    public static final int WTILES = 26;

    /**
     * The height of the tiles
     */
    public static final int HTILES = 14;

    /**
     * The size of the tiles (32 x 32)
     */
    public static final int STILES = (int) (DTILES * SCALE);

    /**
     * The width of the game (however many tiles in the x-position)
     */
    public static final int GWIDTH = STILES * WTILES; 

    /**
     * The height of the game (however many tiles in the y-position)
     */
    public static final int GHEIGHT = STILES * HTILES;
    
    /**
     * The constructor that initialises everything for the game and makes sure the panels are focused when the user is on it
     */
    public Game(){
     
        initClasses();
        gamePanel = new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.requestFocusInWindow();
        
        
        startGameLoop();
        
        
    }
    
    /**
     *Makes sure that all activity (mouse and keyboard) is kept on the specific panel of the game
     */
    public void windowFocusLost(){
        switch(GameState.state){
            case ADDITION:
                a.getPlayer().resetDirBoolean();
                break;
            case SUBTRACTION:
                s.getPlayer().resetDirBoolean();
                break;
            case MULTIPLICATION:
                m.getPlayer().resetDirBoolean();
                break;
                
        }
        
    }
    
    //initialises all the classes needed for the game (the interfaces and the sound)
    private void initClasses(){
        audioOpt = new AudioOptions(this);
        audioPlayer = new AudioPlayer();
        menu = new Menu(this);
        lmenu = new LevelMenu(this);
        a = new Addition(this);
        m = new Multiplication(this);
        s = new Subtraction(this);
        gOptions = new GameOptions(this);
        
        
    }

    /**
     *Updates any animations or actions happening on that specific interface
     */
    public void update(){
      
        switch(GameState.state){
            case MENU:
                menu.update();
                break;
            case LEVELMENU:
                lmenu.update();
                break;
            case ADDITION:
                a.update();
                break;
            case SUBTRACTION:
                s.update();
                break;
            case MULTIPLICATION:
                m.update();
                break;
            case OPTIONS:
                gOptions.update();
                break;
            case QUIT:
                System.exit(0);
                break;
                 
        }
    }

    /**
     *
     * @param g : The images to be drawn
     */
    public void render(Graphics g){
        switch(GameState.state){
            case MENU:
                menu.draw(g);
                break;
            case LEVELMENU:
                lmenu.draw(g);
                break;
            case ADDITION:
               a.draw(g);
                break;
            case SUBTRACTION:
                s.draw(g);
                break;
            case MULTIPLICATION:
                m.draw(g);
                break;
            case OPTIONS:
                gOptions.draw(g);
                break;
            default:
                break;
                
        }
        
        
        
        
    }
    private void startGameLoop(){//This is a continuous loop for the threads of the game
        gameThread = new Thread(this);
        gameThread.start();
    }
    

    @Override
    public void run() {//this runs the game at the specified frame rates and updates per second
       
        double timePFrame = 1000000000.0/FPS;
        double timePUpdate = 1000000000.0/UPS;
       
        
        long prevTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        
       
        
        while(true){
           
            long currentTime = System.nanoTime();
           
            deltaU += (currentTime - prevTime) / timePUpdate;
            deltaF +=  (currentTime - prevTime) / timePFrame;
            prevTime = currentTime;
             
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            
            if(deltaF >= 1)
            {
                gamePanel.repaint();
                frames++;
                deltaF--;
                
            }
         
        
        if(System.currentTimeMillis() - lastCheck >= 1000){
            
            lastCheck = System.currentTimeMillis();
            
            frames = 0;
            updates = 0;
        }
       }
      
    }

    /**
     *
     * @return : The addition level object
     */
    public Addition getAddition(){
        return a;
    }
    
    /**
     *
     * @return : The subtraction level object
     */
    public Subtraction getSubtraction(){
        return s;
    }
    
    /**
     *
     * @return : The multiplication level object
     */
    public Multiplication getMultiplication(){
        return m;
    }
    
    /**
     *
     * @return : The menu interface object
     */
    public Menu getMenu(){
        return menu;
    }
    
    /**
     *
     * @return : the level menu interface object
     */
    public LevelMenu getLevelMenu(){
        return lmenu;
    }
        
    /**
     *
     * @return : the options interface object
     */
    public GameOptions getGameOptions(){
        return gOptions;
    }
    
    /**
     *
     * @return : the audio options object
     */
    public AudioOptions getAudioOptions(){
        return audioOpt;
    }
   
    /**
     *
     * @return : the audio player object
     */
    public AudioPlayer getAudioPlayer(){
        return audioPlayer;
    }
    
}
