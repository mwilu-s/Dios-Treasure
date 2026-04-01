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

/**
 *
 * @author User
 */
public class BackEnd {
    private String [] loginArr = new String [10000];
    private int count = 0;
    Connector db = new Connector("DioTreasure.accdb");
    
    /**
     *
     * @return
     */
    public String newUserLogin(){
        //This is used to display the username and passowrd of the new user that creates an account.
        //It returns it as a string so that a scanner can be used to set them in the labels of the GUI
        String temp = "";
        String qry = "Select TOP 1 * from tblUsers order by userID desc";
        
        try{
        ResultSet res = db.executeQuery(qry);
        while(res.next()){
            String username = res.getString("Username");
            String password = res.getString("Password");
            temp+= username + "#" + password;
            setOnlineUser(temp);
            
        }
        }catch(SQLException e){
            
        }
        return temp;
    }
    
    /**
     *
     */
    public void loginArray(){
        //Gets all the users'usernames and passwords and stores them in the array 'loginArr'.
        //the data will be used to determine if login details are correct
        String sql = "Select * from tblUsers";
        ResultSet rs = db.executeQuery(sql);
        
        try{
        while(rs.next()){
            
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            
            loginArr[count] = username + "#" + password;
            count++;
        }
            
            }catch(SQLException e){
                System.out.println(e);
            }
        
    }
   
    /**
     *
     * @param u : username from text field
     * @param p : the password from the password text field
     * @return
     */
    public boolean checkLogin(String u, String p){
        //This checks if the login details the user inputs are correct and will give the user access to the program.
        //u = username from text field, p = password from password text field.
        for(int loop = 0; loop < count; loop++){
            if(loginArr[loop].equals(u + "#" + p)){
                setOnlineUser(loginArr[loop]);
                return true;
            }
        }
        return false;
    }
    
    private String profileInfo(){
        //This is used to get the currently logged in user's personal information for their profile
        //it returns it as a string so that a scanner can be used and individual labels can display certain information
        String temp = "";
        Scanner scLine = new Scanner (getOnlineUser()).useDelimiter("#");
        String u = scLine.next();
        String p = scLine.next();
        
        String sql = "Select firstname, surname, grade, dob from tblUserInfo inner join tblUsers on tblUserInfo.UserID = tblUsers.UserID where username = '" + u + "' AND password = '" + p +"'";
        ResultSet rs = db.executeQuery(sql);
        
        try{
        while(rs.next()){
            
            
            String name = rs.getString("FirstName");
            String surname = rs.getString("Surname");
            int grade = rs.getInt("Grade");
            String sDate = rs.getString("DOB");
            LocalDate dob = LocalDate.parse(sDate.substring(0,10));
            
            temp+= name +"#"+surname+"#"+grade+"#"+dob;
            
        }
            
            }catch(SQLException e){
                System.out.println(e);
            }
        return temp;
    }
    
    /**
     *
     * @param lbl1 : label to display the user's name
     * @param lbl2 : label to display the user's surname
     * @param lbl3 : label to display the user's grade
     * @param lbl4 : label to display the user's date of birth
     */
    public void profileLbls(javax.swing.JLabel lbl1, javax.swing.JLabel lbl2, javax.swing.JLabel lbl3, javax.swing.JLabel lbl4){
        //Displays the personal information of the user in profile gui
        //all the labels will deiplay a certain part of the user's personal information
        Scanner scLine = new Scanner (profileInfo()).useDelimiter("#");
        lbl1.setText(scLine.next());
        lbl2.setText(scLine.next());
        lbl3.setText(scLine.next());
        lbl4.setText(scLine.next());
    }
    
    private void setOnlineUser(String user){
        //This sets the currently online user to the user that just logged in. this will be written to a text file
        //user = String of the recently signed in user. 
        try{
        PrintWriter outFile = new PrintWriter(new FileWriter("OnlineUsers.txt"));
        outFile.println(user);
        outFile.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    /**
     *
     * @return
     */
    public String getOnlineUser(){
        //This gets the online user and returns the data from the textt file as a string
        String user = "";
        try{
        Scanner scFile = new Scanner (new File("OnlineUsers.txt"));
        user += scFile.nextLine();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        return user;
    }
    
    /**
     *
     * @return
     */
    public int getUserID(){
        //returns the number where the login is equal to any item in the array
        loginArray();
        int num = -1;
        for(int loop = 0; loop < count; loop++){
            if(loginArr[loop].equals(getOnlineUser())){
                num = loop + 1;
            }
        }
        return num;
    }
    
        
    
}
