/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

/**
 *
 * @author User
 */
public enum GameState {

    /**
     * the level addition (interface)
     */
    ADDITION,

    /**
     * the main menu (interface)
     */
    MENU,

    /**
     * the options menu (interface)
     */
    OPTIONS,

    /**
     *exits the game
     */
    QUIT,

    /**
     *the level menu (interface)
     */
    LEVELMENU,

    /**
     *the level subtraction (interface)
     */
    SUBTRACTION,

    /**
     *the level multiplication (interface)
     */
    MULTIPLICATION;
   
    /**
     *The interface that is first seen by the user when the user signs in/ signs up
     */
    public static GameState state = MENU;
}
