package Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Connector {
    
    private Connection conn = null;
    private Statement stmt = null;
    private String [] loginArr = new String [10000];
    private int count = 0;
  
    /**
     *
     * @param dbName : the name of the database being connected
     */
    public Connector (String dbName) {
//Connects database to java application
//dbName = name of my database (DioTreasure.accdb)
        
String driver = "jdbc:ucanaccess://" + dbName;

try {

conn = DriverManager.getConnection(driver);


}

catch (SQLException e) {

    JOptionPane.showMessageDialog(null, "Did not connect");

 }


    }
            
    /**
     *
     * @param qry : the query that is to be performed
     * @return 
     */
    public ResultSet executeQuery(String qry){
        //Performs normal queries for select, order by, where etc.
        //qry = the query the i want to perform.
        ResultSet resSet;
        try {
            stmt = conn.createStatement();
            resSet = stmt.executeQuery(qry);
            return resSet;
           
        }
        catch (SQLException e){
            System.out.println(e);
            resSet = null;
            return resSet;
        }
    }
    
    /**
     *
     * @param qry : the query to be performed
     */
    public void executeUpdate(String qry){
        //Performs update, delet, insertion queries
        //qry = the query I want to perform.
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(qry);
        }
        catch (SQLException e){
            System.out.println(e); 
        }
        
        
        
    }
    
    
}  
    
   
    
