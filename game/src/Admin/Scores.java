/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import GameStates.GameState;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Scores {
    
    private String level;
    private int points, num1, num2;
    private LocalDate date;
    private Connector db = new Connector("DioTreasure.accdb");
    private BackEnd be = new BackEnd();
    
    /**
     *Saves the current date and level type of the game
     * @param state : The level of the game
     */
    public Scores(GameState state){
        setLvlType(state);
        date = LocalDate.now();
        points = 0;
        
    }
    
    //@param state : The level of the game
    private void setLvlType(GameState state){//sets the level to the level the user is playing
       
        switch(state){
            case ADDITION:
                level = "Addition";
                break;
            case SUBTRACTION:
                level = "Subtraction";
                break;
            case MULTIPLICATION:
                level = "Multiplication";
                break;
        }
        
    }
    
    private void rndNums(){//makes the numbers for the questions randomised
        switch(level){
            case "Addition":
                num1 = (int)(Math.random()*101) + 0;
                num2 = (int)(Math.random()* 101) + 0;
                break;
            case "Subtraction":
                num1 = (int)(Math.random()*101) + 0;
                num2 = (int)(Math.random()*100) + 0;
            case "Multiplication":
                num1 = (int)(Math.random()*13) + 0;
                num2 = (int)(Math.random()*13) + 0;
                break;
        }
    }
    
    /**
     *
     */
    public void question(){//asks the specific question correlating to the level being played by the user
        String input = "";
        
        try{
            int answer;
            
            switch(level){
                
            case "Addition":
                rndNums();
                input = JOptionPane.showInputDialog("" + num1 + " + " + num2);
                
                answer = num1 + num2;
                int userAns = Integer.parseInt(input);
                
                if(userAns == answer){
                setPoints(20);
                }else{
                    setPoints(-10);
                }
                
                break;
                
            case "Subtraction":
                rndNums();
                input = JOptionPane.showInputDialog("" + num1 + " - " + num2);
                
                answer = num1 - num2;
                userAns = Integer.parseInt(input);
                if(userAns == answer){
                setPoints(20);
                }else{
                    setPoints(-10);
                }
                break;
                
            case "Multiplication":
                rndNums();
                input = JOptionPane.showInputDialog("" + num1 + " x " + num2);
                
                answer = num1 * num2;
                userAns = Integer.parseInt(input);
                
                if(userAns == answer){
                setPoints(20);
                }else{
                    setPoints(-10);
                }
                break;
                
        }
      
      }catch(Exception e){
            switch(level){
                case "Addition":
                    input = JOptionPane.showInputDialog("" + num1 + " + " + num2 + "\nPlease enter a numerical value \nOR press 'Enter' to skip question (10 points will be deducted)");
                    if(input.equals("")){
                        setPoints(-10);
                    }
                    break;
                case "Subtraction":
                    input = JOptionPane.showInputDialog("" + num1 + " - " + num2 + "\nPlease enter a numerical value \nOR press 'Enter' to skip question (10 points will be deducted)");
                    if(input.equals("")){
                        setPoints(-10);
                    }
                    break;
                case "Multiplication":
                    input = JOptionPane.showInputDialog("" + num1 + " x " + num2 + "\nPlease enter a numerical value \nOR press 'Enter' to skip question (10 points will be deducted)");
                    if(input.equals("")){
                        setPoints(-10);
                    }
                    break;
            }
        }
        
        
        
    }
    
    /**
     *Inserts the scores into tblScores
     */
    public void insertScores(){
        try{
            
        String qry = "INSERT INTO tblScores(DatePlayed, LevelType, Score, UserID)\nVALUES(#" + date + "#, '" + level + "', " + points + ","+ be.getUserID()+")" ;  
        db.executeUpdate(qry);
            
        }
        catch(Exception e){
            //System.out.println("ERROR:" + e);
        }
    }

    //sets the points to the number
    //num: the amount of points obtained by the player (decrease or increase)
    private void setPoints(int num){
        points += num;
        if(points <= 0){
            points = 0;
        }
    }
    
    /**
     *
     * @return: the level of the game
     */
    public String getLevel(){
        return level;
    }
    
    /**
     *
     * @return : the points of the user
     */
    public int getPoints(){
        return points;
    }
    
}
