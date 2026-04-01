/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputs;

/**
 *
 * @author User
 */
import GameStates.GameState;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

/**
 *
 * @author User
 */
public class Keyboard implements KeyListener {
    
    private GamePanel gamePanel;

    /**
     *
     * @param gamePanel: the panel the user is currently on
     */
    public Keyboard(GamePanel gamePanel){
        //It accepts the gamePanel so that the inputs work on whichever objects use the keyboard inputs
        this.gamePanel = gamePanel;
    }
 @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
             //e : the action of the keyboard (which key is being interacted with)
            public void keyReleased(KeyEvent e) {
                //For any object that uses keyReleased then it will perform that action.
                 switch(GameState.state){
           case MENU:
               gamePanel.getGame().getMenu().keyReleased(e);
               break;
           case ADDITION:
               gamePanel.getGame().getAddition().keyReleased(e);
               break;
           case SUBTRACTION:
               gamePanel.getGame().getSubtraction().keyReleased(e);
               break;
           case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().keyReleased(e);
               break;
           default:
               break;
       }
            }

            @Override
             //e : the action of the keyboard (which key is being interacted with)
            public void keyPressed(KeyEvent e) {
                //For any object that uses keyPressed then it will perform that action.
                switch(GameState.state){
           case MENU:
               gamePanel.getGame().getMenu().keyPressed(e);
               break;
           case LEVELMENU:
               gamePanel.getGame().getLevelMenu().keyPressed(e);
               break;
           case ADDITION:
               gamePanel.getGame().getAddition().keyPressed(e);
               break;
           case SUBTRACTION:
               gamePanel.getGame().getSubtraction().keyPressed(e);
               break;
           case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().keyPressed(e);
               break;
           case OPTIONS:
               gamePanel.getGame().getGameOptions().keyPressed(e);
               break;
           default:
               break;
       }
                
            }
    
}
