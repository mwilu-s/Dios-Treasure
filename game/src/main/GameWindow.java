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

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class GameWindow {
    
    private JFrame jframe;

    /**
     *
     * @param gamePanel : The panel the user is currently on
     */
    public GameWindow(GamePanel gamePanel){ // This constructor initialises the jframe that will be used for all the different panels and sets the size as well as the type of jframe (exit, minimse or make it smaller/bigger)
        
        jframe = new JFrame();
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener(){
           @Override
            public void windowGainedFocus(WindowEvent e) {
               
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();                       
            }
           
          
        });
    } 
}

