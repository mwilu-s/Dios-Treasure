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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;

/**
 *
 * @author User
 */
public class Mouse implements MouseListener, MouseMotionListener {
    
    private GamePanel gamePanel;

    /**
     *
     * @param gamePanel : The panel the user is currently on
     */
    public Mouse(GamePanel gamePanel){
        //It accepts the gamePanel so that the inputs work on whichever objects use the keyboard inputs.
        this.gamePanel = gamePanel;
    }
    @Override
    //e : the action of the mouse
    public void mouseClicked(MouseEvent e) {
        //For any object that uses mouseClicked then it will perform that action.
       switch(GameState.state){
           case ADDITION:
               gamePanel.getGame().getAddition().mouseClicked(e);
               break;
           case SUBTRACTION:
               gamePanel.getGame().getSubtraction().mouseClicked(e);
               break;
           case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().mouseClicked(e);
               break;
           default:
               break;
       }
        
    }

    @Override
     //e : the action of the mouse
    public void mousePressed(MouseEvent e) {
        //For any object that uses mousePressed then it will perform that action.
        switch(GameState.state){
           case MENU:
               gamePanel.getGame().getMenu().mousePressed(e);
               break;
           case LEVELMENU:
               gamePanel.getGame().getLevelMenu().mousePressed(e);
               break;
           case ADDITION:
               gamePanel.getGame().getAddition().mousePressed(e);
               break;
           case SUBTRACTION:
               gamePanel.getGame().getSubtraction().mousePressed(e);
               break;
           case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().mousePressed(e);
               break;
           case OPTIONS:
               gamePanel.getGame().getGameOptions().mousePressed(e);
               break;
           default:
               break;
       }
       
    }

    @Override
     //e : the action of the mouse
    public void mouseReleased(MouseEvent e) {
        //For any object that uses mouseReleased then it will perform that action.
        switch(GameState.state){
           case MENU:
               gamePanel.getGame().getMenu().mouseReleased(e);
               break;
           case LEVELMENU:
               gamePanel.getGame().getLevelMenu().mouseReleased(e);
               break;
           case ADDITION:
               gamePanel.getGame().getAddition().mouseReleased(e);
               break;
           case SUBTRACTION:
               gamePanel.getGame().getSubtraction().mouseReleased(e);
               break;
           case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().mouseReleased(e);
               break;
           case OPTIONS:
               gamePanel.getGame().getGameOptions().mouseReleased(e);
               break;
           default:
               break;
       }
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
     //e : the action of the mouse
    public void mouseDragged(MouseEvent e) {
        //For any object that uses mouseDragged then it will perform that action.
        switch(GameState.state){
           case ADDITION:
               gamePanel.getGame().getAddition().mouseDragged(e);
               break;
           case SUBTRACTION:
               gamePanel.getGame().getSubtraction().mouseDragged(e);
               break;
           case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().mouseDragged(e);
               break;
           case OPTIONS:
               gamePanel.getGame().getGameOptions().mouseDragged(e);
               break;
           default:
               break;
       }
    }

    @Override
     //e : the action of the mouse
    public void mouseMoved(MouseEvent e) {
        //For any object that uses mouseMoved then it will perform that action.
     //   gamePanel.setRectPosition(e.getX(),e.getY());
     switch(GameState.state){
           case MENU:
               gamePanel.getGame().getMenu().mouseMoved(e);
               break;
           case LEVELMENU:
               gamePanel.getGame().getLevelMenu().mouseMoved(e);
           case ADDITION:
               gamePanel.getGame().getAddition().mouseMoved(e);
               break;
            case SUBTRACTION:
               gamePanel.getGame().getSubtraction().mouseMoved(e);
               break;
            case MULTIPLICATION:
               gamePanel.getGame().getMultiplication().mouseMoved(e);
               break;
            case OPTIONS:
               gamePanel.getGame().getGameOptions().mouseMoved(e);
               break;
           default:
               break;
       }
    }
    
}
