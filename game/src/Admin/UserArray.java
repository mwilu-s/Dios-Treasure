/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


/**
 *
 * @author User
 */
public class UserArray {
    
   private User [] userArr = new User [1000];
   private int count = 0;
   private Connector db = new Connector("DioTreasure.accdb");
  
    /**
     * This retrieves all the information of each user
     */
    public UserArray(){
        //It will read all the records from tblUserInfo into the array.
        String sql = "Select * from tblUserInfo";
        ResultSet rs = db.executeQuery(sql);
        
        try{
        while(rs.next()){
            
            
            String name = rs.getString("FirstName");
            String surname = rs.getString("Surname");
            int grade = rs.getInt("Grade");
            String sDate = rs.getString("DOB");
            LocalDate dob = LocalDate.parse(sDate.substring(0,10));
            
            userArr[count] = new User(name, surname, grade, dob);
            count++;
        }
            
            }catch(SQLException e){
                System.out.println(e);
            }
    }
    
    /**
     *
     * @param name: the name of the user
     * @param surname: the surname of the user
     * @param grade: the grade of the user
     * @param dob: the date of birth for the user
     * @return: Whether the user's information already exists in the database table or not
     */
    public boolean existingUser(String name, String surname, int grade, LocalDate dob){
        //This checks to see if the user is not trying to create an account with identical information that was used to make a previous account. It accepts the parameters of the name, surname, grade and date of birth that is entered by the user in the sign up page. 
        for(int loop = 0; loop < count; loop++){
            if(userArr[loop].getName().equalsIgnoreCase(name) && userArr[loop].getSurname().equalsIgnoreCase(surname) && userArr[loop].getGrade() == grade && userArr[loop].getDOB().equals(dob)){
                return true;
            }
        }
        return false;
    }
    
    
    
}
