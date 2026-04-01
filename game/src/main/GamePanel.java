/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author User
 */
import inputs.Keyboard;
import inputs.Mouse;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import static main.Game.GHEIGHT;
import static main.Game.GWIDTH;

/**
 *
 * @author User
 */
public class GamePanel extends JPanel {
    
    private Mouse mInputs;
    private Game game;
    
    /**
     *
     * @param game : The actual game object that will help the running of the game and which panels will be displayed
     */
    public GamePanel(Game game){//Constructor initialises the panel and its size as well as the mouse interactions that will be used for the specific panel
        this.game = game;
       //random = new Random();
       mInputs = new Mouse(this);
       
       setPanelSize();
       addKeyListener(new Keyboard(this));
       addMouseListener(mInputs);
       addMouseMotionListener(mInputs);
    }

    /**
     *
     * @return : the game object
     */
    public Game getGame(){
        return game;
    }
    
    /**
     *
     */
    public void updateGame(){
       
      
   }
    public void paintComponent(Graphics g){//draws everything onto the panel
        super.paintComponent(g);
        game.render(g);
       
    }

    private void setPanelSize() { //sets the size of the panel
        Dimension size = new Dimension(GWIDTH, GHEIGHT);
        setPreferredSize(size);
    }
   
}
