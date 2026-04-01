/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import Levels.LevelManager;
import UI.GameHelpOverlay;
import UI.GameOverOverlay;
import UI.PHButton;
import UI.PausedOverlay;
import entities.EnemyManager;
import entities.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.Game;
import objects.ObjectManager;
import Admin.Scores;
import static utilities.Constants.BCHEIGHT;
import static utilities.Constants.BCWIDTH;
import static utilities.Constants.SCHEIGHT;
import static utilities.Constants.SCWIDTH;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class Multiplication extends State implements StateMethods{
    
   private Player player;
   private LevelManager lm;
   private ObjectManager om;
   private boolean paused = false;
   private PausedOverlay po;
   private EnemyManager em;
   private boolean dying;
   
   int rndNum = (int) (Math.random() *2) +1;
   
   private int xLvlOffset;
   private int leftBorder = (int) (0.2 * Game.GWIDTH);
   private int rightBorder = (int) (0.8 * Game.GWIDTH);
   private int maxLvlOffsetX;
   
   private BufferedImage bgImg, bigCloud, smallCloud;
   private int [] smallCloudsPos;
   private Random rnd = new Random();
   
   private boolean gameOver;
   private GameOverOverlay gOver;
   
   private boolean gameHelp;
   private GameHelpOverlay gHelp;
   
    /**
     *
     * @param game
     */
    public Multiplication(Game game){
        super(game);
        initClasses();
        
        
        if(rndNum == 1){
            bgImg = LoadAndSave.getSprites(LoadAndSave.DAYBG);
        }else{
            bgImg = LoadAndSave.getSprites(LoadAndSave.NIGHTBG);
        }
        
        bigCloud = LoadAndSave.getSprites(LoadAndSave.BIGCLOUD);
        smallCloud = LoadAndSave.getSprites(LoadAndSave.SMALLCLOUD);
        smallCloudsPos = new int [8];
        for(int loop = 0; loop < smallCloudsPos.length; loop++){
            smallCloudsPos[loop] = (int) (90*Game.SCALE) + rnd.nextInt((int) (100 * Game.SCALE));
        }
        
        loadStartLevel();
        calcLvlOffset();
        
        
        
    }
    
    /**
     *this saves the score of the user
     */
    public void saveScore(){
        if(gameOver){
          om.saveScore();
        }
    }
    
    //loads all the objects and enemies for the start of the level with their default properties
    private void loadStartLevel(){
        em.loadEnemies(lm.getCurrentLevel());
        om.loadObjects(lm.getCurrentLevel());
    }
    
    
    //this calculates the level offset when the player moves left or right and what they see
    private void calcLvlOffset(){
        maxLvlOffsetX = lm.getCurrentLevel().getLvlOffset();
    }
    
    //initialises all the classes needed for the game
    private void initClasses(){
        lm = new LevelManager(game);
        em = new EnemyManager(this);
        om = new ObjectManager(this);
        
                
        player = new Player(200, 200, (int) (64*Game.SCALE), (int) (40 * Game.SCALE), this);
        player.setSpawn(lm.getCurrentLevel().getSpawn());
        player.loadLvlData(lm.getCurrentLevel().getLevelData());
        po = new PausedOverlay(this);
        gHelp = new GameHelpOverlay(this);
        gOver = new GameOverOverlay(this);
        
    }
    
    /**
     *resets all variables for the level
     */
    public void resetAll(){//resets player, enemies and level
        gameOver = false;
        paused = false;
        dying = false;
        gameHelp = false;
        rndNum = (int)(Math.random() * 2) + 1;
        player.resetAll();
        em.resetAllEnemies();
        om.resetAllObjects();
    }
    
    /**
     *sets whether the game is over or not
     * @param gameOver : determines whether the game is over because the player has died
     */
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    
    /**
     *sets whether the user is in the help section or not
     * @param gameHelp : determines whether the user is clicking the help button
     */
    public void setGameHelp(boolean gameHelp){
        this.gameHelp = gameHelp;
    }
    
    /**
     *checks to see if the player has hit an object
     * @param ac : the attack collision detection box of the player
     */
    public void checkObjectHit(Rectangle2D.Float ac){
        om.checkObjectHit(ac);
    }
    
    /**
     *checks to see if the player has hit the enemy
     * @param ac : the attack collision detection box of the player
     */
    public void checkEnemyHit(Rectangle2D.Float ac){
        em.checkEnemyHit(ac);
    }
    
    /**
     *checks to see if the player has touched a potion
     * @param collision : the collision detection box of the player
     */
    public void checkPotionTouched(Rectangle2D.Float collision){
        om.checkObjectTouched(collision, GameState.MULTIPLICATION);
    }
    
    /**
     *checks to see if the player has touched a coin
     * @param collision : the collision detection box of the player
     */
    public void checkCoinTouched(Rectangle2D.Float collision){
        om.checkObjectTouched(collision, GameState.MULTIPLICATION);
    }

    @Override
    public void update() {//updates all the overlays and objects as well as the player and enemies and all their animations and states
        
        if(paused){
            po.update();
        }else if(gameHelp){
            gHelp.update();
        }else if(gameOver){
            gOver.update();
            
        }else if(dying){
            player.update(GameState.MULTIPLICATION);
        }else {
            lm.update();
            om.update(lm.getCurrentLevel().getLevelData(), player);
            player.update(GameState.MULTIPLICATION);
            em.update(lm.getCurrentLevel().getLevelData(), player);
            checkBorderProx();
        }
      
      
    }
    //checks how far the player is from the border walls of the leve
    private void checkBorderProx(){
        int playerX = (int) player.getCollision().x;
        int diff = playerX - xLvlOffset;
        
        if(diff > rightBorder){
            xLvlOffset += diff - rightBorder;
        }else if(diff < leftBorder){
            xLvlOffset += diff - leftBorder;
        }
        
        if(xLvlOffset > maxLvlOffsetX){
            xLvlOffset = maxLvlOffsetX;
        }else if(xLvlOffset < 0){
            xLvlOffset = 0;
        }
    }

    @Override
    //g: the images to be drawn
    //draws all the images needed for the game
    public void draw(Graphics g){
        g.drawImage(bgImg, 0, 0, Game.GWIDTH, Game.GHEIGHT, null);
        drawClouds(g);
        
        lm.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        em.draw(g, xLvlOffset);
        om.draw(g, xLvlOffset);
        
        if(paused){
            po.draw(g);
        }else{
            if(gameOver){
                gOver.draw(g);
            }else if(gameHelp){
                gHelp.draw(g);
            }
        }
       
    }
    
    //g: the small clouds and big cloud images
    //draws the clouds in the background of the game
    private void drawClouds(Graphics g){
        for(int loop = 0; loop < 3; loop++){
         g.drawImage(bigCloud, loop * BCWIDTH - (int)(xLvlOffset * 0.3), (int)(204 * Game.SCALE),BCWIDTH, BCHEIGHT, null);
        }
        
        for(int loop2 = 0; loop2 < smallCloudsPos.length; loop2++){
        g.drawImage(smallCloud, SCWIDTH * 4 * loop2 - (int)(xLvlOffset * 0.65), smallCloudsPos[loop2], SCWIDTH, SCHEIGHT, null);
        }
    }
    
    //b: button
    //e: the action performed by the mouse
    private boolean isIn(PHButton b, MouseEvent e) {
	return b.getBox().contains(e.getX(), e.getY());
    }

    @Override
    //e: the action performed by the mouse
    //determines if the mouse has been clicked
    public void mouseClicked(MouseEvent e) {
        if(!gameOver){
        if (e.getButton() == MouseEvent.BUTTON1)
       {
           player.setAttack(true);
       }
      }
    }

    @Override
    //e: the action performed by the mouse
    //determines if the mouse has been pressed
    public void mousePressed(MouseEvent e) {
       if(!gameOver){
        if(isIn(player.getHelpBtn(),e)){
            player.mousePressed(e);
        }else if(paused){
            po.mousePressed(e);
        }
       }else{
           gOver.mousePressed(e);
       }
    }

    @Override
    //e: the action performed by the mouse
    //determines if the mouse has been released after being pressed
    public void mouseReleased(MouseEvent e) {
        if(!gameOver){
        if(isIn(player.getHelpBtn(),e)){
            player.mouseReleased(e, GameState.MULTIPLICATION);
        }
        if(paused){
            po.mouseReleased(e, GameState.MULTIPLICATION);
        }
        }else{
            gOver.mouseReleased(e, GameState.MULTIPLICATION);
        }
    }
    
    /**
     *determines if the mouse is being dragged for the volume button
     * @param e:the action performed by the mouse
     */
    public void mouseDragged(MouseEvent e){
        if(!gameOver){
        if(paused){
            po.mouseDragged(e);
        }
        }
    }

    @Override
    //e: the action performed by the mouse
    //determines if the mouse has moved
    public void mouseMoved(MouseEvent e) {
        if(!gameOver){
            if(isIn(player.getHelpBtn(),e)){
                player.mouseMoved(e);
            }else if(paused){
            po.mouseMoved(e);
        }   
        }else{
            gOver.mouseMoved(e);
        }
        
    }

    @Override
    //e: the action performed by the keyboard
    //determines if the key has been pressed
    public void keyPressed(KeyEvent e) {
        if(gameHelp){
           gHelp.keyPressed(e);
       }else if(gameOver){
           gOver.keyPressed(e);
       }else{
       switch(e.getKeyCode()){
                   
                   
                   case KeyEvent.VK_LEFT:
                       player.setLeft(true);
                       break;
                   case KeyEvent.VK_RIGHT:
                       player.setRight(true);
                       break;
                    case KeyEvent.VK_SPACE:
                       player.setJump(true);
                       break;
                    case KeyEvent.VK_ENTER:
                        paused = !paused;
                        break;
               }
       
        }

    }

    @Override
    //e: the action performed by the keyboard
    //determines if the keys for the game have been released after being pressed
    public void keyReleased(KeyEvent e) {
        if(!gameOver){
       switch(e.getKeyCode()){
                   
                   
                   case KeyEvent.VK_LEFT:
                       player.setLeft(false);
                       break;
                   case KeyEvent.VK_RIGHT:
                       player.setRight(false);
                       break;
                   case KeyEvent.VK_SPACE:
                       player.setJump(false);
                       break;
               }
        }
    }
    
    /**
     *
     * @return the player object
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     *resets the player's direction
     */
    public void windowFocusLost(){
        player.resetDirBoolean();
    }
    
    /**
     * unpauses the game when the unpause button is clicked
     */
    public void unpauseGame(){
        paused = false;
    }
    
    /**
     *
     * @return : the object manager
     */
    public ObjectManager getObjectManager(){
        return om;
    }
    
    /**
     *
     * @return : the level manager
     */
    public LevelManager getLevelManager(){
        return lm;
    }
    
    /**
     *checks if the player touches the traps
     * @param p : the player object
     */
    public void checkTrapsTouched(Player p){
        om.checkTrapTouched(p);
    }
    
    /**
     *sets whether the player is dying or not
     * @param dying : whether the player is dying (player has 0 health)
     */
    public void setDying(boolean dying){
        this.dying = dying;
    }
}
