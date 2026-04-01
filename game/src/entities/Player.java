/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
import GameStates.GameState;
import GameStates.Addition;
import GameStates.Multiplication;
import GameStates.Subtraction;
import UI.PHButton;
import audio.AudioPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import main.Game;
import static utilities.Constants.*;
import static utilities.HelpMethods.detection;
import static utilities.HelpMethods.notOnFloor;
import static utilities.HelpMethods.getEntityXPosNextToWall;
import static utilities.HelpMethods.getEntityYPosUnderROrAboveF;
import utilities.LoadAndSave;

/**
 *
 * @author User
 */
public class Player extends Entity{
    
    // Constants for the player

    /**
     * The animation where the player is idle 
     */
    
    public static final int IDLE = 0;

    /**
     *The animation where the player is running
     */
    public static final int RUNNING = 1;

    /**
     * The animation where the player is jumping
     */
    public static final int JUMP = 2;

    /**
     *The animation where the player is falling
     */
    public static final int FALLING = 3;

    /**
     *The animation where the player is attacking objects
     */
    public static final int ATTACK = 4;

    /**
     *The animation where the player is being hit by the enemy
     */
    public static final int HIT = 5;

    /**
     *The animation where the player is dying
     */
    public static final int DEAD = 6;
    
    //playerAction : the animation of the player
    private int getPlayerSpriteAmt(int playerAction){
        //returns the amount of sprites depending on the action of the player
        switch(playerAction){
            
            case DEAD:
                return 8;
            case RUNNING:
                return 6;
            case IDLE:
                return 5;
            case HIT:
                return 4;
            case JUMP:
            case ATTACK:
                return 3;
            case FALLING:
            default:
                return 1;
        }
    }
    
    private BufferedImage[][] animations;
    
    private boolean moving = false, attack = false;
    private boolean right, left, jump;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    private int yTile = 0;
    
    //Jumping/Gravity
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeed = 0.5f * Game.SCALE;
   
    
    //Health bar UI
    private BufferedImage healthBarImg;
    //where the health will be decreasing
    private int hbWidth = (int) (150 * Game.SCALE);
    private int hbHeight = (int) (4 * Game.SCALE);
    private int hbX = (int) (34 * Game.SCALE);
    private int hbY = (int) (14 * Game.SCALE);
    //whole image bar
    private int sbWidth = (int) (192 * Game.SCALE);
    private int sbHeight = (int) (58 * Game.SCALE);
    private int sbX = (int) (10 * Game.SCALE);
    private int sbY = (int) (10 * Game.SCALE);
   
    private int healthWidth = hbWidth;
    private PHButton help;
    
    
    //Flipping the character
    private int flipX = 0;
    private int flipW = 1;
    
    private boolean attackChecked;
    private Addition a;
    private Multiplication m;
    private Subtraction s;
    
    /**
     *
     * @param x : the x-value for the player
     * @param y: the y-value for the player
     * @param width: the width of the player
     * @param height: the height of the player
     * @param a : the addition level
     */
    public Player(float x, float y, int width, int height, Addition a){
        //Instantiates the character and being drawn in the game as well as receiving its animations and detections. This is for the addition level.
        super(x,y,width,height);
        this.a = a;
        this.state = IDLE;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.walkSpeed = 1.0f * Game.SCALE;
        loadAnimations();
        initCollision(20,27);
        initAttackCollision();
        createHelpBtn();
        
        
    }
    
    /**
     *
     * @param x : the x-value for the player
     * @param y: the y-value for the player
     * @param width: the width of the player
     * @param height: the height of the player
     * @param m : the multiplication level
     */
    public Player(float x, float y, int width, int height, Multiplication m){
        //Instantiates the character and being drawn in the game as well as receiving its animations and detections. This is for the multiplication level.
        super(x,y,width,height);
        this.m = m;
        this.state = IDLE;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.walkSpeed = 1.0f * Game.SCALE;
        loadAnimations();
        initCollision(20,27);
        initAttackCollision();
        createHelpBtn();
        
        
    }

    /**
     *
     * @param x : the x-value for the player
     * @param y: the y-value for the player
     * @param width: the width of the player
     * @param height: the height of the player
     * @param s : the subtraction level
     */
    public Player(float x, float y, int width, int height, Subtraction s){
        //Instantiates the character and being drawn in the game as well as receiving its animations and detections. This is for the subtraction level.
        super(x,y,width,height);
        this.s = s;
        this.state = IDLE;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.walkSpeed = 1.0f * Game.SCALE;
        loadAnimations();
        initCollision(20,27);
        initAttackCollision();
        createHelpBtn();
        
    }

    /**
     *Sets the player at the spawn point
     * @param spawn : the spawn point of the player in the game
     */
    public void setSpawn(Point spawn){
        //
        this.x = spawn.x;
        this.y = spawn.y;
        collision.x = x;
        collision.y = y;
    }
    
    /**
     *Updates the players animations and behaviors based on the level the player is playing
     * @param gState : the level that the player is in
     */
    public void update(GameState gState){
        //This updates all the components that need to be updated for the character.
        help.update();
        updateHealthBar();
        
        if(currentHealth <= 0){
            if(state != DEAD){
                state = DEAD;
                aniTick = 0;
                aniIndex = 0;
                switch (gState) {
                    case ADDITION:
                        a.setDying(true);
                        a.getGame().getAudioPlayer().playEffect(AudioPlayer.die);
                        break;
                    case SUBTRACTION:
                        s.setDying(true);
                        s.getGame().getAudioPlayer().playEffect(AudioPlayer.die);
                        break;
                    case MULTIPLICATION:
                        m.setDying(true);
                        m.getGame().getAudioPlayer().playEffect(AudioPlayer.die);
                        break;
                }
                
            }else if(aniIndex == getPlayerSpriteAmt(DEAD) - 1 && aniTick >= ANISPEED -1){//checks for last sprite and last animation tick
                switch(gState){
                    case ADDITION:
                        a.setGameOver(true);
                        a.getGame().getAudioPlayer().stopSong();
                        a.getGame().getAudioPlayer().playEffect(AudioPlayer.gameOver);
                        a.saveScore();
                        break;
                    case SUBTRACTION:
                        s.setGameOver(true);
                        s.getGame().getAudioPlayer().stopSong();
                        s.getGame().getAudioPlayer().playEffect(AudioPlayer.gameOver);
                        s.saveScore();
                        break;
                    case MULTIPLICATION:
                        m.setGameOver(true);
                        m.getGame().getAudioPlayer().stopSong();
                        m.getGame().getAudioPlayer().playEffect(AudioPlayer.gameOver);
                        m.saveScore();
                        break;
                }
                
            }else{
                updateAnimationTick();
                
            }
            
            return;
        }
        
        updateAttackCollision();
        updatePos(gState);        
        if(moving){
            checkPotionTouched(gState);
            checkTrapTouched(gState);
            checkCoinTouched(gState);
            
            yTile = (int)(collision.y / Game.STILES);
        }
        if(attack){
            checkAttack(gState);
        }
        
        updateAnimationTick();
        setAnimation();
       
        
    }
    
    //state: the level the user is playing
    private void checkTrapTouched(GameState state){
        //this checks if the player has touched a spike
        switch(state){
            case ADDITION:
                a.checkTrapsTouched(this);
                break;
            case SUBTRACTION:
                s.checkTrapsTouched(this);
                break;
            case MULTIPLICATION:
                m.checkTrapsTouched(this);
                break;
        }
        
    }
    
    //state: the level the user is playing
    private void checkCoinTouched(GameState state){
        //this checks if the player has touched the coin
        switch(state){
            case ADDITION:
                a.checkCoinTouched(collision);
                break;
            case SUBTRACTION:
                s.checkCoinTouched(collision);
                break;
            case MULTIPLICATION:
                m.checkCoinTouched(collision);
                break;
        }
        
    }
    
    /**
     *this sets the health of the player to 0 when the player dies
     */
    public void death(){
        //this sets the death of the player
        currentHealth = 0;
        
    }
    
    //state: the level the user is playing
    private void checkPotionTouched(GameState state){
        //checks if the player has touched a potion
        switch(state){
            case ADDITION:
                a.checkPotionTouched(collision);
                break;
            case SUBTRACTION:
                s.checkPotionTouched(collision);
                break;
            case MULTIPLICATION:
                m.checkPotionTouched(collision);
                break;
        }
            }
    //state: the level the user is playing
    private void checkAttack(GameState state){
        //checks if the player has performed an attack action
        if(attackChecked || aniIndex != 1){
            return;
        }
        attackChecked = true;
        switch(state){
            case ADDITION:
                a.checkEnemyHit(attackCollision);
                a.checkObjectHit(attackCollision);
                a.getGame().getAudioPlayer().playAttackSound();
                break;
            case SUBTRACTION:
                s.checkEnemyHit(attackCollision);
                s.checkObjectHit(attackCollision);
                s.getGame().getAudioPlayer().playAttackSound();
                break;
            case MULTIPLICATION:
                m.checkEnemyHit(attackCollision);
                m.checkObjectHit(attackCollision);
                m.getGame().getAudioPlayer().playAttackSound();
                break;
        }
        
    }
    
    //moves the attack collision detection box for the player when the player moves
   private void updateAttackCollision() {
       if(right && left){
           if(flipW == 1){
               attackCollision.x = collision.x + collision.width + (int) (Game.SCALE * 10);
           }else{
               attackCollision.x = collision.x - collision.width - (int) (Game.SCALE * 10);
           }
       }else if (right){
		attackCollision.x = collision.x + collision.width + (int) (Game.SCALE * 10);
        }else if (left){
		attackCollision.x = collision.x - collision.width - (int) (Game.SCALE * 10);
        }	
        attackCollision.y = collision.y + (Game.SCALE * 10);

	}

    //moves the health of the player on the bar based on the health of the player (whether they are losing health or gaining it)
    private void updateHealthBar(){
        healthWidth = (int) ((currentHealth/ (float)maxHealth) * hbWidth);
    }
    
    /**
     *it resets the directions of the player
     */
    public void resetDirBoolean(){
        left = false;
        right = false;
    }
    
    /**
     *
     * @param g: the image of the player to be drawn
     * @param lvlOffset: the offset of the level
     */
    public void render(Graphics g,int lvlOffset){
        //this draws the player, help button and health bar
          g.drawImage(animations[state][aniIndex],(int) (collision.x - xDrawOffset) - lvlOffset + flipX, (int) (collision.y - yDrawOffset), width * flipW, height, null);
        
          drawUI(g);
          help.draw(g);
    }
   
    //g: the images to be drawn(health bar, level and score)
    private void drawUI(Graphics g){
        //this draws the animation of the health bar
        g.drawImage(healthBarImg, sbX, sbY, sbWidth, sbHeight, null);
        g.setColor(Color.red);
        g.fillRect(hbX + sbX, hbY + sbY, healthWidth, hbHeight);
        
        switch(GameState.state){
            case ADDITION:
                g.setColor(Color.white);
                g.setFont(new Font("Monospaced", Font.BOLD, 20));
                g.drawString("LEVEL: " + a.getObjectManager().getLvl(), 350, 47);
                
                g.setColor(Color.white);
                g.setFont(new Font("Monospaced", Font.BOLD, 20));
                g.drawString("SCORE: " + a.getObjectManager().getPoints(), 350, 70);
                break;
            case SUBTRACTION:
                g.setColor(Color.white);
                g.setFont(new Font("Monospaced", Font.BOLD, 20));
                g.drawString("LEVEL: " + s.getObjectManager().getLvl(), 350, 47);
        
                g.setColor(Color.white);
                g.setFont(new Font("Monospaced", Font.BOLD, 20));
                g.drawString("SCORE: " + s.getObjectManager().getPoints(), 350, 70);
                break;
            case MULTIPLICATION:
                g.setColor(Color.white);
                g.setFont(new Font("Monospaced", Font.BOLD, 20));
                g.drawString("LEVEL: " + m.getObjectManager().getLvl(), 350, 47);
        
                g.setColor(Color.white);
                g.setFont(new Font("Monospaced", Font.BOLD, 20));
                g.drawString("SCORE: " + m.getObjectManager().getPoints(), 350, 70);
                break;
                
        }
        
    }
    
    private void createHelpBtn(){
        //this creates the help button for the game
        int helpX = (int) (750 * Game.SCALE);
        int helpY = (int) (380 * Game.SCALE);
        
        help = new PHButton(helpX, helpY, URMPHSIZE, URMPHSIZE, 1);
    }

    /**
     *This resets every property of the player
     */
    public void resetAll(){
        //this resets the player to default properties
        resetDirBoolean();
        inAir = false;
        attack = false;
        moving = false;
        airSpeed = 0f;
        state = IDLE;
        currentHealth = maxHealth;
        
        collision.x = x;
        collision.y = y;
        
        resetAttackCollision();
        
        if(!notOnFloor(collision, lvlData)){
            inAir = true;
        }
    }
    
    private void resetAttackCollision(){
        //this draws the attack collision box every time the player changes direction
        if(flipW == 1){
            attackCollision.x = collision.x + collision.width + (int) (Game.SCALE * 10);
        }else{
            attackCollision.x = collision.x - collision.width - (int)(Game.SCALE * 10);
        }
    }
    
    
    private void initAttackCollision(){
        //this creates an attack collision box for the enemy to attack different objects
        attackCollision = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
        resetAttackCollision();
    }
    
    /**
     *
     * @param num : the amount of health lost or gained
     */
    public void setHealth(int num){//Checks to see if player is dead i.e. health = 0 and that the health does not go over the max health (100)
   //this sets the health of the player if it gets damage from enemies or cannons
        currentHealth += num;
        if(currentHealth <= 0){
            currentHealth = 0;
        }else if(currentHealth>= maxHealth){
            currentHealth = maxHealth;
        }
     
}
    
    
     private void loadAnimations() {
         //this loads the animations of the player and the healthbar
          BufferedImage img = LoadAndSave.getSprites(LoadAndSave.PLAYER); 
          
           animations = new BufferedImage [7][8];
       
       for(int i  = 0; i < animations.length; i++)
       {
       for(int loop = 0; loop < animations[i].length; loop++){
           animations[i][loop] = img.getSubimage(loop*64, i*40, 64, 40);
       }
       }
       
       healthBarImg = LoadAndSave.getSprites(LoadAndSave.HEALTHBAR);
         
    }
    
    /**
     *
     * @param lvlData : the level data of the game
     */
    public void loadLvlData(int [][] lvlData) {
        //this loads the level data so that the player can walk on solid ground
        this.lvlData = lvlData;
        if(!notOnFloor(collision,lvlData)){
            inAir = true;
        }
    }
     
   
   
    private void updateAnimationTick() {
        //update the animation tick based on the different animations the player does
        aniTick++;
        
        if(aniTick >= ANISPEED)
        {
            aniTick = 0;
            aniIndex++;
            
            if(aniIndex >= getPlayerSpriteAmt(state))
            {
                aniIndex= 0;
                attack = false;
                attackChecked = false;
            }
        }
    }

    private void setAnimation() {
      //this sets the animation of the player based on its interactions and actions in the game
      int startAni = state;
        
      if(moving)
      {
          state = RUNNING;
          
      }
      else
      {
          state = IDLE;
      }
      
      if(inAir){
          if(airSpeed < 0){
              state = JUMP;
          }
          else{
              state = FALLING;
          }
      }
      
      if(attack)
      {
          state = ATTACK;
          if(startAni != ATTACK){
              aniIndex = 1;
              aniTick = 0;
              return;
          }
      }
      if(startAni != state)
      {
          resetAniTick();
      }
}

    private void resetAniTick(){
        //resets the animation tick and animation index to the defualt sprites
        aniTick = 0;
        aniIndex = 0;
    }
    private void updatePos(GameState state) {
        //this updates the positions of the player in the level
       
        moving = false;
        
        if(jump){
            jump(state);
        }
        
        if(!inAir){
           if((!left && !right)||(right && left)){
            return;
           }
        }
        float xSpeed = 0;
        
        
        
       if(left && !right)
       {
           xSpeed -= walkSpeed;
           flipX = width;
           flipW = -1;
       }
       
        if(right && !left)
        {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }
        
        if(!inAir){
            if(!notOnFloor(collision, lvlData)){
                inAir = true;
            }
        }
        
       
       if(inAir){
          if(detection(collision.x, collision.y + airSpeed, collision.width, collision.height, lvlData)){
              collision.y += airSpeed;
              airSpeed += GRAVITY;
              UpdateXPos(xSpeed);
          }
          else{
              collision.y = getEntityYPosUnderROrAboveF(collision, airSpeed);
              if(airSpeed > 0){
                  resetInAir();
              }
              else{
                  airSpeed = fallSpeed;
              }
              UpdateXPos(xSpeed);
          }
       }
       else{
           UpdateXPos(xSpeed);
       }
        moving = true;
    }
        
    /**
     *
     * @param attack : whether the player is attacking or not
     */
    public void setAttack(boolean attack)
    {//this sets the attack to true or false based on whether the player is attacking or not
        this.attack = attack;
    }
    
    //xSpeed : the speed of the player in the horizontal
    private void UpdateXPos(float xSpeed) {
        //this updates the position of the player when it moves
        if(detection(collision.x+xSpeed, collision.y, collision.width, collision.height, lvlData)){
           collision.x += xSpeed;
       }
        else{
            collision.x = getEntityXPosNextToWall(collision, xSpeed);
        }
    }
    
    //state: the level the user is playing
    private void jump(GameState state){
        //this determines if the player is in the air or not
        if(inAir){
            return;
        }
        switch(state){
            case ADDITION:
                a.getGame().getAudioPlayer().playEffect(AudioPlayer.jump);
                break;
            case SUBTRACTION:
                s.getGame().getAudioPlayer().playEffect(AudioPlayer.jump);
                break;
            case MULTIPLICATION:
                m.getGame().getAudioPlayer().playEffect(AudioPlayer.jump);
                break;
        }
        
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        //this resets the player in the air to false
        inAir = false;
        airSpeed = 0;
    }
    
    /**
     *
     * @param left: if the direction of the player is heading left
     */
    public void setLeft(boolean left)
    {//this sets the direction to left and whether the player is going left
        this.left = left;
    }

    /**
     *
     * @param right: if the direction of the player is heading right
     */
    public void setRight(boolean right)
    {//this sets the direction right and whether the player is going right
        this.right = right;
    }
             
    /**
     *
     * @param jump: if the player is jumping
     */
    public void setJump(boolean jump)  {
         //this sees if the player is jumping or not
         this.jump = jump;
     }      
             
    /**
     *
     * @return
     */
    public boolean getLeft()   {
        //this returns the direction left
        return left;
    }     

    /**
     *
     * @return
     */
    public boolean getRight()   {
        //this returns the direction right
        return right;
    }

    /**
     *
     * @return
     */
    public int getYTile(){
        //this returns the tile the player is on in the vertical
        return yTile;
    }
    
    //Help Button

    /**
     *
     * @return
     */
    
    public PHButton getHelpBtn(){
        //this returns the help button
        return help;
    }
    
    private boolean isIn(PHButton b, MouseEvent e) {
	//This checks whether the cursor of the mouse is in the button and will animate to a certain action depending on the action of the user.
        return b.getBox().contains(e.getX(), e.getY());
    }

    /**
     *
     * @param e: the action of the mouse
     */
    public void mouseMoved(MouseEvent e) {
        //This checks if the cursor is inside the button and will animate as the mouse over effect.
	help.setMouseOver(false);
	if (isIn(help, e)){
	    help.setMouseOver(true);
        }
    }

    /**
     *
     * @param e: the action of the mouse
     * @param state: the level the user is playing
     */
    public void mouseReleased(MouseEvent e, GameState state) {
	//This will show the help overlay.
        if (isIn(help, e)) {
	    if (help.getMousePressed() && state == GameState.ADDITION) {  
	        a.setGameHelp(true);
                a.getGame().getAudioPlayer().setLevelSong(a.getLevelManager().getCount());
		}else if(help.getMousePressed() && state == GameState.SUBTRACTION){
                    s.setGameHelp(true);
                    s.getGame().getAudioPlayer().setLevelSong(s.getLevelManager().getCount());
                }else if(help.getMousePressed() && state == GameState.MULTIPLICATION){
                    m.setGameHelp(true);
                    m.getGame().getAudioPlayer().setLevelSong(m.getLevelManager().getCount());
                }
	} 
                
	help.resetBtns();
    }

    /**
     *
     * @param e: the action of the mouse
     */
    public void mousePressed(MouseEvent e) {
        //This will check if the mouse is clicked on the button and will perform the mouse pressed effect.
	if (isIn(help, e)){
            help.setMousePressed(true);
            }
    }
 
}

