/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;


import UI.LMButton;
import UI.MenuButton;
import audio.AudioPlayer;
import java.awt.event.MouseEvent;
import main.Game;

/**
 *
 * @author User
 */
public class State {
    
    /**
     * Game object
     */
    protected Game game;
    
    /**
     *Instantiates the game object for the interfaces
     * @param game : the game object
     */
    public State(Game game){
        this.game = game;
        
    }
    
    /**
     *
     * @param e : The action of the mouse
     * @param mb : the menu button object
     * @return : the box of the button
     */
    public boolean pressed(MouseEvent e, MenuButton mb){
        return mb.getBox().contains(e.getX(), e.getY());
    }
    
    /**
     *
     * @param e : The action of the mouse
     * @param lmb : the menu button object
     * @return : the box of the button
     */
    public boolean pressed(MouseEvent e, LMButton lmb){
        return lmb.getBox().contains(e.getX(), e.getY());
    }
    
    /**
     *
     * @return : game object
     */
    public Game getGame(){
        return game;
    }
    
    /**
     *changes the state of the game
     * @param state : interface of the game
     */
    public void setGameState(GameState state){
        switch(state){
            case MENU: 
                game.getAudioPlayer().playSong(AudioPlayer.menu1);
                break;
            case ADDITION:
                game.getAudioPlayer().setLevelSong(game.getAddition().getLevelManager().getCount());
                break;
            case SUBTRACTION:
                game.getAudioPlayer().setLevelSong(game.getSubtraction().getLevelManager().getCount());
                break;
            case MULTIPLICATION:
                game.getAudioPlayer().setLevelSong(game.getMultiplication().getLevelManager().getCount());
                break;
            case LEVELMENU:
                game.getAudioPlayer().playSong(AudioPlayer.menu1);
                break;
        }
        GameState.state = state;
    }
}
