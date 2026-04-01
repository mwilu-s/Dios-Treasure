package Admin;


import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class User {
    
    private String name, surname;
    private int grade;
    private LocalDate dob;
    private Connector db = new Connector("DioTreasure.accdb");
    
    /**
     *
     * @param n : name of user
     * @param s: surname of user
     * @param g: grade of user
     * @param d: date of birth for user
     */
    public User(String n, String s, int g, LocalDate d){//Creates a user object for each user that creates an account
        name = n;
        surname = s;
        grade = g;
        dob = d;
        
        
    }
    
    /**
     *
     */
    public void insertUserInfoRS(){
        //Inserts the user's infor int tblUserInfo
        try{
        
        
        String qry = "INSERT INTO tblUserInfo(FirstName, Surname, Grade, DOB)\nVALUES('" + name + "', '" + surname + "', " + grade + ", #" + dob + "#)" ;  
        db.executeUpdate(qry);
          
            
        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
        }
        
    }
    
    /**
     *
     */
    public void insertLoginDetailsRS(){
        //It accepts no parameters and enters the data of the username and password, created from the values from User object, and inserts them into tblUser.
        String qry = "INSERT INTO tblUsers(Username, Password)\nVALUES('" + createUsername() + "', '" + createPassword() + "')" ;
        db.executeUpdate(qry);
    }
    
    private String createPassword(){
        //It accepts no parameters and uses the information of the user to create a new password for the user.
        String password = "";
        int num = grade + dob.getYear();
        password += name.charAt(0) +""+ surname.charAt(0) + num +"" + dob.getDayOfMonth();
        return password;
    }
    
    private String createUsername(){
        //It accepts no parameters and uses the information of the user to create a new username for the user.
        String username = "";
        
        username += name.substring(0,3).toUpperCase() + surname.substring(surname.length()-3, surname.length()).toUpperCase() + dob.getDayOfYear();
        return username;
    }
    
    /**
     *
     * @return : name of the user
     */
    public String getName(){
        return name;
    }
    
    /**
     *
     * @return : surname of the user
     */
    public String getSurname(){
        return surname;
    }
    
    /**
     *
     * @return : the grade of the user
     */
    public int getGrade(){
        return grade;
    }
    
    /**
     *
     * @return : the date of birth for the user
     */
    public LocalDate getDOB(){
        return dob;
    }
    
    
    
}
