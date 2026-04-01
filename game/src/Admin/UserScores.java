/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class UserScores {
    
    private String [] userAddScore = new String [5];
    private String [] userSubScore = new String [5];
    private String [] userMultiScore = new String [5];
    private String [] overallAddScores = new String [5];
    private String [] overallSubScores = new String [5];
    private String [] overallMultiScores = new String [5];
    private int count = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0;
    private Connector db = new Connector("DioTreasure.accdb");
    private BackEnd be = new BackEnd();
    
    /**
     *The constructor retrieves all the scores of each level for the user and the overall scores
     */
    public UserScores(){
        
        overallAddScores();
        overallSubScores();
        overallMultiScores();
        userAddScores();
        userSubScores();
        userMultiScores();
    }
    
    //retrieves top 5 overall scores for the level addition
    private void overallAddScores(){
        
        String qry = "select TOP 5 username, datePlayed, levelType, score from tblScores inner join tblUsers on tblScores.userid = tblUsers.userid where levelType = 'Addition' order by score desc";
        ResultSet rs = db.executeQuery(qry);
        try{
            
            while(rs.next()){
                String username = rs.getString("Username");
                String date = rs.getString("DatePlayed");
                String level = rs.getString("LevelType");
                int score = rs.getInt("Score");
                LocalDate datePlayed = LocalDate.parse(date.substring(0,10));
                overallAddScores[count4] = username + "#" + datePlayed +"#" + level + "#" + score;
                count4++;
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("OverallAddScores.txt"));
        for(int loop = 0; loop < count4; loop++){
            outFile.print(overallAddScores[loop] + "\n");
        }
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
        
    }
    
    //retrieves top 5 overall scores for the level subtraction
    private void overallSubScores(){
        //This reads all the scores overall for the subtraction level and saves it in a text file.
        String qry = "select TOP 5 username, datePlayed, levelType, score from tblScores inner join tblUsers on tblScores.userid = tblUsers.userid where levelType = 'Subtraction' order by score desc";
        ResultSet rs = db.executeQuery(qry);
        try{
            while(rs.next()){
                String username = rs.getString("Username");
                String date = rs.getString("DatePlayed");
                String level = rs.getString("LevelType");
                int score = rs.getInt("Score");
                LocalDate datePlayed = LocalDate.parse(date.substring(0,10));
                overallSubScores[count5] = username + "#" + datePlayed +"#" + level + "#" + score;
                count5++;
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("OverallSubScores.txt"));
        for(int loop = 0; loop < count5; loop++){
            outFile.print(overallSubScores[loop] + "\n");
        }
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    //retrieves top 5 overall scores for the level multiplication
    private void overallMultiScores(){
        //This reads all the scores overall for the multiplication level and saves it in a text file.
        String qry = "select TOP 5 username, datePlayed, levelType, score from tblScores inner join tblUsers on tblScores.userid = tblUsers.userid where levelType = 'Multiplication' order by score desc";
        ResultSet rs = db.executeQuery(qry);
        try{
            while(rs.next()){
                String username = rs.getString("Username");
                String date = rs.getString("DatePlayed");
                String level = rs.getString("LevelType");
                int score = rs.getInt("Score");
                LocalDate datePlayed = LocalDate.parse(date.substring(0,10));
                overallMultiScores[count6] = username + "#" + datePlayed +"#" + level + "#" + score;
                count6++;
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("OverallMultiScores.txt"));
        for(int loop = 0; loop < count6; loop++){
            outFile.print(overallMultiScores[loop] + "\n");
        }
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    private void userMultiScores(){
        //This reads all the scores of the user for the multiplication level and saves it in a text file.
        String line = be.getOnlineUser();
        Scanner scLine = new Scanner(line).useDelimiter("#");
        String username = scLine.next();
        
        String sql = "SELECT TOP 5 username, dateplayed, leveltype, score FROM tblScores INNER JOIN tblUsers ON tblScores.userid = tblUsers.userid WHERE levelType = 'Multiplication' AND username = '"+ username +"' order by score desc";
        ResultSet rs = db.executeQuery(sql);
        try{
        while(rs.next()){
            
            String uName = rs.getString("Username");
            String date = rs.getString("DatePlayed");
            String level = rs.getString("LevelType");
            int score = rs.getInt("Score");
            LocalDate datePlayed = LocalDate.parse(date.substring(0,10));
            
            userMultiScore[count3] = uName + "#" + datePlayed +"#" + level + "#" + score;
            count3++;
        }
            
            }catch(SQLException e){
                System.out.println(e);
            }
        
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("UserMultiScores.txt"));
        for(int loop = 0; loop < count3; loop++){
            outFile.print(userMultiScore[loop] + "\n");
        }
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    private void userAddScores(){
        //This reads all the scores of the user for the addition level and saves it in a text file.
        String line = be.getOnlineUser();
        Scanner scLine = new Scanner(line).useDelimiter("#");
        String username = scLine.next();
        
        String sql = "SELECT TOP 5 username, dateplayed, leveltype, score FROM tblScores INNER JOIN tblUsers ON tblScores.userid = tblUsers.userid WHERE levelType = 'Addition' AND username = '"+ username +"' order by score desc";
        ResultSet rs = db.executeQuery(sql);
        try{
        while(rs.next()){
            
            String uName = rs.getString("Username");
            String date = rs.getString("DatePlayed");
            String level = rs.getString("LevelType");
            int score = rs.getInt("Score");
            LocalDate datePlayed = LocalDate.parse(date.substring(0,10));
            
            userAddScore[count] = uName + "#" + datePlayed +"#" + level + "#" + score;
            count++;
        }
            
            }catch(SQLException e){
                System.out.println(e);
            }
        
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("UserAddScores.txt"));
        for(int loop = 0; loop < count; loop++){
            outFile.print(userAddScore[loop] + "\n");
        }
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    private void userSubScores(){
        //This reads all the scores of the user for the subtraction level and saves it in a text file.
        String line = be.getOnlineUser();
        Scanner scLine = new Scanner(line).useDelimiter("#");
        String username = scLine.next();
        
        String sql = "SELECT Top 5 username, dateplayed, leveltype, score FROM tblScores INNER JOIN tblUsers ON tblScores.userid = tblUsers.userid WHERE levelType = 'Subtraction' AND username = '"+ username +"' order by score desc";
        ResultSet rs = db.executeQuery(sql);
        try{
        while(rs.next()){
            
            String uName = rs.getString("Username");
            String date = rs.getString("DatePlayed");
            String level = rs.getString("LevelType");
            int score = rs.getInt("Score");
            LocalDate datePlayed = LocalDate.parse(date.substring(0,10));
            
            userSubScore[count2] = uName + "#" + datePlayed +"#" + level + "#" + score;
            count2++;
        }
            
            }catch(SQLException e){
                System.out.println(e);
            }
        
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("UserSubScores.txt"));
        for(int loop = 0; loop < count2; loop++){
            outFile.print(userSubScore[loop] + "\n");
        }
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    /**
     *
     * @param jtbl: the JTable for the user's scores
     * @param jtbl2: the JTable for the overall high scores
     */
    public void insertAdditionScores(javax.swing.JTable jtbl, javax.swing.JTable jtbl2){
        //This inserts the data from the text file into the JTable for the user’s scores that corresponds with the level selection of the button chosen
        jtbl.setModel(new DefaultTableModel(null, new String[]{"Username", "DatePlayed", "LevelType", "Score"}));
        jtbl2.setModel(new DefaultTableModel(null, new String[]{"Username", "DatePlayed", "LevelType", "Score"}));
        
        try{
        Scanner scFile = new Scanner(new File("UserAddScores.txt"));
        DefaultTableModel model = (DefaultTableModel) jtbl.getModel();
        while(scFile.hasNextLine()){
            
            for(int loop = 0; loop < count; loop++){
               String [] row = scFile.nextLine().split("#");
               model.addRow(row);
            }
        }
        
        Scanner scFile2 = new Scanner(new File("OverallAddScores.txt"));
        DefaultTableModel model2 = (DefaultTableModel) jtbl2.getModel();
        while(scFile2.hasNextLine()){
            
            for(int loop = 0; loop < count4; loop++){
               String [] row = scFile2.nextLine().split("#");
               model2.addRow(row);
            }
        }
        
        }catch(FileNotFoundException e){
            System.out.println("Error: File Not Found");
        }
    }
    
    /**
     *
     * @param jtbl: the JTable for the user's scores
     * @param jtbl2: the JTable for the overall high scores
     */
    public void insertSubtractionScores(javax.swing.JTable jtbl, javax.swing.JTable jtbl2){
        //This inserts the data from the text file into the JTable for the user’s scores that corresponds with the level selection of the button chosen
        jtbl.setModel(new DefaultTableModel(null, new String[]{"Username", "DatePlayed", "LevelType", "Score"}));
        jtbl2.setModel(new DefaultTableModel(null, new String[]{"Username", "DatePlayed", "LevelType", "Score"}));
        
        try{
        Scanner scFile = new Scanner(new File("UserSubScores.txt"));
        DefaultTableModel model = (DefaultTableModel) jtbl.getModel();
        while(scFile.hasNextLine()){
            
            for(int loop = 0; loop < count2; loop++){
               String [] row = scFile.nextLine().split("#");
               model.addRow(row);
            }
        }
        
        Scanner scFile2 = new Scanner(new File("OverallSubScores.txt"));
        DefaultTableModel model2 = (DefaultTableModel) jtbl2.getModel();
        while(scFile2.hasNextLine()){
            
            for(int loop = 0; loop < count5; loop++){
               String [] row = scFile2.nextLine().split("#");
               model2.addRow(row);
            }
        }
        
        }catch(FileNotFoundException e){
            System.out.println("Error: File Not Found");
        }
    }
    
    /**
     *
     * @param jtbl: the JTable for the user's scores
     * @param jtbl2: the JTable for the overall high scores
     */
    public void insertMultiplicationScores(javax.swing.JTable jtbl, javax.swing.JTable jtbl2){
        //This inserts the data from the text file into the JTable for the user’s scores that corresponds with the level selection of the button chosen
        jtbl.setModel(new DefaultTableModel(null, new String[]{"Username", "DatePlayed", "LevelType", "Score"}));
        jtbl2.setModel(new DefaultTableModel(null, new String[]{"Username", "DatePlayed", "LevelType", "Score"}));
        
        try{
        Scanner scFile = new Scanner(new File("UserMultiScores.txt"));
        DefaultTableModel model = (DefaultTableModel) jtbl.getModel();
        while(scFile.hasNextLine()){
            
            for(int loop = 0; loop < count3; loop++){
               String [] row = scFile.nextLine().split("#");
               model.addRow(row);
               
            }
        }
        
        Scanner scFile2 = new Scanner(new File("OverallMultiScores.txt"));
        DefaultTableModel model2 = (DefaultTableModel) jtbl2.getModel();
        while(scFile2.hasNextLine()){
            
            for(int loop = 0; loop < count6; loop++){
               String [] row = scFile2.nextLine().split("#");
               model2.addRow(row);
            }
        }
        
        }catch(FileNotFoundException e){
            System.out.println("Error: File Not Found");
        }
    }
    
}
    
