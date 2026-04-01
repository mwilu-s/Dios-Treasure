/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import Admin.Scores;
import GameStates.Addition;
import GameStates.GameState;
import GameStates.Multiplication;
import GameStates.Subtraction;
import Levels.Levels;
import entities.Player;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import static objects.Cannon.*;
import static objects.Coin.*;
import static objects.GameContainer.*;
import static objects.Potion.*;
import static objects.Projectile.*;
import static objects.Spikes.*;
import static utilities.HelpMethods.canSeePlayer;
import utilities.LoadAndSave;
import static utilities.HelpMethods.getProjectileHitWall;

/**
 *
 * @author User
 */
public class ObjectManager {
    
    private Addition a;
    private Subtraction s;
    private Multiplication m;
    private Scores score;
    
    private BufferedImage [][] potionImgs, containersImgs;
    private BufferedImage spikeImg, cBallImg;
    private BufferedImage [] cannonImg, coinImg;
    
    private ArrayList<Potion> potions;
    private ArrayList<Coin> coin;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spikes> spikes;
    private ArrayList<Cannon> cannons;
    private ArrayList<Projectile> projectile = new ArrayList<>();
    
    /**
     *
     * @param a : An addition object for the level addition
     */
    public ObjectManager(Addition a){
        this.a = a;
        score = new Scores(GameState.ADDITION);
        loadImgs();
    }
    
    /**
     *
     * @param s : A subtraction object for the level subtraction
     */
    public ObjectManager(Subtraction s){
        this.s = s;
        score = new Scores(GameState.SUBTRACTION);
        loadImgs();
    }
    
    /**
     *
     * @param m : A multiplication object for the level multiplication
     */
    public ObjectManager(Multiplication m){
        this.m = m;
        score = new Scores(GameState.MULTIPLICATION);
        loadImgs();
    }
    
    /**
     *
     * @param collision : The collision detection box of the player
     * @param state : The level the object is in
     */
    public void checkObjectTouched(Rectangle2D.Float collision, GameState state){
        for(Potion p : potions){
            if(p.getActive()){
                if(collision.intersects(p.getCollision())){
                    p.setActive(false);
                    applyEffectToPlayer(p, state);
                }
            }
        }
        
        for(Coin c : coin){
            if(c.getActive()){
                if(collision.intersects(c.getCollision())){
                    score.question();
                    c.setActive(false);
                }
            }
        }
    }
    
    //This gives the player the value when they touch the potion
    private void applyEffectToPlayer(Potion p, GameState state){
        if(p.getObjType() == PPOTION && state == GameState.ADDITION){
            a.getPlayer().setHealth(PPOTIONVALUE);
        }else if(p.getObjType() == PPOTION && state == GameState.SUBTRACTION){
            s.getPlayer().setHealth(PPOTIONVALUE);
        }else if(p.getObjType() == PPOTION && state == GameState.MULTIPLICATION){
            m.getPlayer().setHealth(PPOTIONVALUE);
        }
    }
    
    /**
     *
     * @param attackCollision: The collision detection box of the player's sword
     */
    public void checkObjectHit(Rectangle2D.Float attackCollision){//this checks whteher the player is hitting a barrel or a box
        for(GameContainer c : containers){
            if(c.getActive() && !c.animation){
                if(c.getCollision().intersects(attackCollision)){
                    c.setAnimation(true);
                    
                    if(c.getObjType() == BARREL){
                        coin.add(new Coin((int)(c.getCollision().x + c.getCollision().width/2), (int)(c.getCollision().y), COIN));
                        return;
                    }else{
                        if(c.getObjType() == BOX){
                            potions.add(new Potion((int) (c.getCollision().x + c.getCollision().width/2), (int)(c.getCollision().y - c.getCollision().height/2), PPOTION));
                            return;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * This resets all objects to its previous properties that it was at the beginning of the game
     */
    public void resetAllObjects(){
        switch (GameState.state){
            case ADDITION:
                score = new Scores(GameState.ADDITION);
                loadObjects(a.getLevelManager().getCurrentLevel());
                break;
            case SUBTRACTION:
                loadObjects(s.getLevelManager().getCurrentLevel());
                score = new Scores(GameState.SUBTRACTION);
                break;
            case MULTIPLICATION:
                loadObjects(m.getLevelManager().getCurrentLevel());
                score = new Scores(GameState.MULTIPLICATION);
                break;
        }
        
        
        for(Coin c: coin){
            c.reset();
        }
        for(Potion p : potions){
            p.reset();
        }
        for(GameContainer c : containers){
            c.reset();
        }
        for(Cannon c : cannons){
            c.reset();
        }
        
        
    }
    
    /**
     * This resets the containers specifically
     */
    public void reset(){
        for(GameContainer c : containers){
            c.reset();
        }
        
    }
    
    /**
     *Checks if the player has fallen onto or touched the traps
     * @param p: The player object
     */
    public void checkTrapTouched(Player p){
        for(Spikes s: spikes){
            if(s.getCollision().intersects(p.getCollision())){
                p.death();
            }
        }
    }
        
    /**
     *This loads the objects into the game
     * @param level : the level of the game
     */
    public void loadObjects(Levels level){
        spikes = level.getSpikes();
        potions = new ArrayList<>(level.getPotions());
        coin = new ArrayList<>(level.getCoins());
        cannons = level.getCannon();
        containers = new ArrayList<>(level.getGameContainers());
        projectile.clear();
    }
    
    //This loads the images into their arrays or buffered image objects for the purpose of animation and drawing it to the level
    private void loadImgs(){
        BufferedImage potionSprite = LoadAndSave.getSprites(LoadAndSave.POTIONS);
        potionImgs = new BufferedImage[2][7];
        
        for(int loop = 0; loop  < potionImgs.length; loop++){
            for(int loop1 = 0; loop1 < potionImgs[loop].length; loop1++){
                potionImgs[loop][loop1] = potionSprite.getSubimage(12 * loop1, 16 * loop, 12, 16);
            }
        }
        
        BufferedImage containerSprite = LoadAndSave.getSprites(LoadAndSave.CONTAINERS);
        containersImgs = new BufferedImage[2][8];
        
        for(int loop = 0; loop  < containersImgs.length; loop++){
            for(int loop1 = 0; loop1 < containersImgs[loop].length; loop1++){
                containersImgs[loop][loop1] = containerSprite.getSubimage(40 * loop1, 30 * loop, 40, 30);
            }
        }
        
        spikeImg = LoadAndSave.getSprites(LoadAndSave.TRAP);
        
        cannonImg = new BufferedImage [7];
        BufferedImage temp = LoadAndSave.getSprites(LoadAndSave.CANNON);
        
        for(int loop = 0; loop < cannonImg.length; loop++){
            cannonImg[loop] = temp.getSubimage(loop * 40, 0, 40, 26);
        }
        
        cBallImg = LoadAndSave.getSprites(LoadAndSave.CANNONBALL);
        
        coinImg = new BufferedImage[4];
        BufferedImage coinTemp = LoadAndSave.getSprites(LoadAndSave.COINS);
        
        for(int loop = 0; loop <coinImg.length; loop++){
            coinImg[loop] = coinTemp.getSubimage(loop * 16, 0, 16, 16);
        }
        
	
    }
    
    /**
     *This updates all objects animations while they are present in the game
     * @param lvlData : The data of the level to determine the tile the object is on
     * @param player : The player object
     */
    public void update(int [][] lvlData, Player player){
        boolean activeObjs = false;
        for(Potion p : potions){
            if(p.getActive()){
                p.update();
            }
        }
        
        for(GameContainer c : containers){
            if(c.getActive()){
                c.update();
                activeObjs = true;
            }
        }
        
        for(Coin c : coin){
            if(c.getActive()){
                c.update();
                activeObjs = true;
            }
        }
        
        if(!activeObjs){
            reset();
        }
        
        updateCannons(lvlData, player);
        updateProjectiles(lvlData, player);
    }
    
    //This shoots the cannon balls
    private void updateProjectiles(int [][] lvlData, Player player){
        for(Projectile p : projectile){
            if(p.getActive()){
                p.updatePos();
                
                if(p.getCollision().intersects(player.getCollision())){
                    player.setHealth(-30);
                    p.setActive(false);
                }else{
                    if(getProjectileHitWall(p, lvlData)){
                        p.setActive(false);
                    }
                }
            }
        }
    }
    
    //This animates the cannons
    private void updateCannons(int [][] lvlData, Player p){
        for(Cannon c : cannons){
            if(!c.animation){
                if(c.getYTile() == p.getYTile()){
                    if(inRange(c,p)){
                        if(inFrontOfCannon(c,p))
                            if(canSeePlayer(lvlData, p.getCollision(), c.getCollision(), c.getYTile())){
                                c.setAnimation(true);
                            }
                        
                    }
                }
            }
            
            c.update();
            if(c.getAniIndex() == 4 && c.getAniTick() == 0){
                shootCannon(c);
            }
        }

        
        //Checking to see if player is in the same yTile and if it is in a certain range
        //Check to see if player is in front of the cannon
        //check line of sight
    }
    
    //This shoots the cannon balls
    private void shootCannon(Cannon c){
        int direction = 1;
        if(c.getObjType() == LCANNON){
            direction = -1;
        }
        projectile.add(new Projectile((int) c.getCollision().x, (int) c.getCollision().y, direction));
    }
    
    //This determines if the player is in front of the cannon
    private boolean inFrontOfCannon(Cannon c, Player p){
        if(c.objType == LCANNON){
            if(c.getCollision().x > p.getCollision().x){//player is on the left side of the cannon for left cannon
                return true;
            }
        }else{
            if(c.getCollision().x < p.getCollision().x){
                return true;
            }
        }
        
        return false;
    }
    
    //This determines the distance between the cannon and the player
    private boolean inRange(Cannon c, Player p){
        int absValue = (int) Math.abs(p.getCollision().x - c.getCollision().x );
        return absValue <= Game.STILES * 5;//5 tile range
    }
    
    /**
     *
     * @param g : The image to be drawn
     * @param xLvlOffset : the offset of the level in the horizontal position
     */
    public void draw(Graphics g, int xLvlOffset){
        drawPotions(g, xLvlOffset);
        drawCoins(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
        drawTraps(g, xLvlOffset);
        drawCannons(g, xLvlOffset);
        drawProjectiles(g, xLvlOffset);
    }
    
    //This draws the cannon balls
    private void drawProjectiles(Graphics g, int xLvlOffset){
        for(Projectile p : projectile){
            if(p.getActive()){
                g.drawImage(cBallImg,(int) (p.getCollision().x - xLvlOffset), (int) (p.getCollision().y), CBWIDTH, CBHEIGHT,null);
            }
        }
    }
    
    //This draws the cannons and determines its direction to face
    private void drawCannons(Graphics g, int xLvlOffset){
        for(Cannon c : cannons){
            int x = (int) (c.getCollision().x - xLvlOffset);
            int width = CAWIDTH;
            if(c.getObjType() == RCANNON){
                x += width;
                width *= -1;
                
            }
            g.drawImage(cannonImg[c.getAniIndex()],x ,(int) (c.getCollision().y),width, CAHEIGHT,null);
        }
    }
    
    //This draws the traps
    private void drawTraps(Graphics g, int xLvlOffset){
        for(Spikes s: spikes){
            g.drawImage(spikeImg, (int) (s.getCollision().x - xLvlOffset), (int) (s.getCollision().y - s.getYDrawOffset()),SWIDTH, SHEIGHT, null );
        }
    }
    
    //This draws the containers
    private void drawContainers(Graphics g, int xLvlOffset){
        for(GameContainer c : containers){
            if(c.getActive()){
                int type = 0;
                if(c.getObjType() == BARREL){
                    type = 1;
                }
            g.drawImage(containersImgs[type][c.getAniIndex()],(int) (c.getCollision().x - c.getXDrawOffset() - xLvlOffset),(int) (c.getCollision().y - c.getYDrawOffset()), CWIDTH, CHEIGHT, null);

            }
        }
    }
    
    //This draws the potions
    private void drawPotions(Graphics g,int xLvlOffset){
        for(Potion p : potions){
            if(p.getActive()){
                int type = 0;
                if(p.getObjType() == PPOTION){
                    type = 1;
                g.drawImage(potionImgs[type][p.getAniIndex()],(int) (p.getCollision().x - p.getXDrawOffset() - xLvlOffset),(int) (p.getCollision().y - p.getYDrawOffset()), PWIDTH, PHEIGHT, null);

                }
            }
        }
    }
    
    //This draws the coins
    private void drawCoins(Graphics g, int xLvlOffset){
        for(Coin c : coin){
            if(c.getActive()){
                
                g.drawImage(coinImg[0], (int)(c.getCollision().x - c.getXDrawOffset() - xLvlOffset), (int)(c.getCollision().y - c.getYDrawOffset()), COINWIDTH, COINHEIGHT, null);
                
            }
        }
    }
    
    /**
     *
     * @return : The points obtained by the player
     */
    public int getPoints(){
        return score.getPoints();
    }
    
    /**
     *
     * @return : The level the player is playing
     */
    public String getLvl(){
        return score.getLevel();
    }
    
    /**
     * This will insert the scores of the player when they die
     */
    public void saveScore(){
        score.insertScores();
    }
    
}
