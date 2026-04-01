/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author User
 */
public class DataValidation {
    
    private String name, surname;
    private int grade;
    private LocalDate dob;
    
    /**
     *
     */
    public DataValidation(){
       // Instantiates a data validation object.
        name = null;
        surname = null;
        grade = 0;
        dob = null;
        
    }
    
    /**
     *
     * @param txtName : the text field for the name to be inputted by the user
     * @param lblNameError : the label that will show error messages
     * @return : whether there is anything entered in the text field
     */
    public boolean presenceCheckName(javax.swing.JTextField txtName, javax.swing.JLabel lblNameError)
    {
        //This checks whether the user has entered anything into the text field for the name. 
        //If not, the label will display an error message telling the user to enter something
        name = txtName.getText();
        if (name.length() < 1)
        {
            lblNameError.setText("Name not entered");
            lblNameError.setVisible(true);
            return true;
        }
        
        return false;    
    }
    
    /**
     *
     * @param txtName : the text field for the name to be inputted by the user
     * @param lblNameError : the label that will show error messages
     * @return
     */
    public boolean typeCheckName(javax.swing.JTextField txtName, javax.swing.JLabel lblNameError){
        //This checks to see if the user has entered characters that are letters instead of any other abnormal characters that are not found in a name. An appropriate message 
        //will be displayed through the error label.
        name = txtName.getText();
        
            for (int loop = 0; loop < name.length(); loop++){
            if(Character.isLetter(name.charAt(loop)) && (name.contains("-") || Character.isLetter(name.charAt(loop))) && !(name.contains("_")) && !(name.contains("" + Character.isDigit(name.charAt(loop)))))
            {
                lblNameError.setText("");
                lblNameError.setVisible(true);
                return false;
            }
            else{
                lblNameError.setText("Invalid input - Special characters have been inputted");
                lblNameError.setVisible(true);
                return true;
            }
            }
            return false;
        
    }
    
    /**
     *
     * @param txtName : the text field for the name to be inputted by the user
     * @param lblNameError : the label that will show error messages
     * @return
     */
    public boolean lengthCheckName(javax.swing.JTextField txtName, javax.swing.JLabel lblNameError){
      // This checks that the user has inputted a name that is longer than 2 characters. If not, an error message will be displayed in the label. 
        name = txtName.getText();
        
        if(name.length() < 2 && presenceCheckName(txtName, lblNameError) == false){
            
             lblNameError.setText("Name cannot be one letter");
             lblNameError.setVisible(true);
             return true;
        }
        return false;
    }
    
    /**
     *
     * @param txtSurname : the text field for the surname
     * @param lblSurnameError : the error label for the surname
     * @return
     */
    public boolean presenceCheckSurname(javax.swing.JTextField txtSurname, javax.swing.JLabel lblSurnameError)
    {
        //This checks whether the user has selected a grade. If not, an appropriate error message will be displayed.
        surname = txtSurname.getText();
        if (surname.length() < 1)
        {
            lblSurnameError.setText("Surname not entered");
            lblSurnameError.setVisible(true);
            return true;
        }
        
        return false;    
    }
    
    /**
     *
     * @param txtSurname : the text field for the surname
     * @param lblSurnameError : the error label for the surname
     * @return
     */
    public boolean typeCheckSurname(javax.swing.JTextField txtSurname, javax.swing.JLabel lblSurnameError){
        //This checks to see if the user has entered characters that are letters instead of any other abnormal characters that are not found in a name. An appropriate message will be displayed through the error label.
        surname = txtSurname.getText();
        
            for (int loop = 0; loop < surname.length(); loop++){
            if(Character.isLetter(surname.charAt(loop)) && (surname.contains("-") || Character.isLetter(surname.charAt(loop))) && !(surname.contains("_")) && !(surname.contains("" + Character.isDigit(surname.charAt(loop)))))
            {
                lblSurnameError.setText("");
                lblSurnameError.setVisible(true);
                return false;
            }
            else{
                lblSurnameError.setText("Invalid input - Special characters have been inputted");
                lblSurnameError.setVisible(true);
                return true;
            }
            }
            
        return false; 
        
    }
    
    /**
     *
     * @param txtSurname : the text field for the surname
     * @param lblSurnameError : the error label for the surname
     * @return
     */
    public boolean lengthCheckSurname(javax.swing.JTextField txtSurname, javax.swing.JLabel lblSurnameError){
        //This checks that the user has inputted a surname that is longer than 2 characters. If not, an error message will be displayed in the label.
        surname = txtSurname.getText();
        int count = surname.length();
        if(count < 2 && !(surname.equals(""))){
            
             lblSurnameError.setText("Surname cannot be one letter");
             lblSurnameError.setVisible(true);
             return true;
        }
        
        return false;
    }
    
    /**
     *
     * @param cmbGrade : The combo box for the grade
     * @param lblGradeError : the error label for the grade
     * @return
     */
    public boolean presenceCheckGrade(javax.swing.JComboBox cmbGrade, javax.swing.JLabel lblGradeError){
      //This checks whether the user has selected a grade. If not, an appropriate error message will be displayed.  
        String gradeCheck = (String) cmbGrade.getSelectedItem();
        if(gradeCheck.equals("Select")){
            lblGradeError.setText("Grade not chosen");
            lblGradeError.setVisible(true);
            return true;
        }else{
            lblGradeError.setText("");
            lblGradeError.setVisible(false);
            grade = Integer.parseInt(gradeCheck);
        }
        return false;
    }
    
    /**
     *
     * @param lblDOBError : error label for the date of birth
     * @param cmbGrade : the combo box for the grade
     * @param dtcDOB : the date time chooser for the date of birth
     * @return
     */
    public boolean checkDOB(javax.swing.JLabel lblDOBError, javax.swing.JComboBox cmbGrade, com.toedter.calendar.JDateChooser dtcDOB){
        //This checks to see if the user has chosen a date of birth and whether it corresponds to the grade chosen. Appropriate error 
        //messages will be displayed where necessary.
        try{//LOGICAL CHECK
            Date date = dtcDOB.getDate();
            dob = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String gradeCheck = (String) cmbGrade.getSelectedItem();
            grade = Integer.parseInt(gradeCheck);
            
            LocalDate upperGR6 = LocalDate.of(2012, Month.DECEMBER, 31);
            LocalDate lowerGR6 = LocalDate.of(2010, Month.DECEMBER, 31);
            
            LocalDate upperGR7 = LocalDate.of(2011, Month.DECEMBER, 31);
            LocalDate lowerGR7 = LocalDate.of(2009, Month.DECEMBER, 31);
            
            LocalDate upperGR8 = LocalDate.of(2010, Month.DECEMBER, 31);
            LocalDate lowerGR8 = LocalDate.of(2008, Month.DECEMBER, 31);
            
            LocalDate upperGR9 = LocalDate.of(2009, Month.DECEMBER, 31);
            LocalDate lowerGR9 = LocalDate.of(2007, Month.DECEMBER, 31);
            
            //LocalDate dateNow = LocalDate.now();
            
            if(gradeCheck.equals("6") && (dob.isAfter(upperGR6) == true || dob.isBefore(lowerGR6) == true)){
                lblDOBError.setText("Child cannot be in grade 6");
                lblDOBError.setVisible(true);
                return true;
            }
            else if(gradeCheck.equals("7") && (dob.isAfter(upperGR7) == true ||dob.isBefore(lowerGR7) == true)){
                lblDOBError.setText("Child cannot be in grade 7");
                lblDOBError.setVisible(true);
                return true;
            }
            else if(gradeCheck.equals("8") && (dob.isAfter(upperGR8) == true || dob.isBefore(lowerGR8) == true)){
                lblDOBError.setText("Child cannot be in grade 8");
                lblDOBError.setVisible(true);
                return true;
            }
            else if(gradeCheck.equals("9") && (dob.isAfter(upperGR9) == true || dob.isBefore(lowerGR9) == true)){
                lblDOBError.setText("Child cannot be in grade 9");
                lblDOBError.setVisible(true);
                return true;
            }else{
                lblDOBError.setText("");
                lblDOBError.setVisible(false);
            }
            
        }
        catch(NullPointerException e){//PRESENCE CHECK
            lblDOBError.setText("Enter Date of Birth");
            lblDOBError.setVisible(true);
            return true;
        }
        return false;
    }
    
    /**
     *
     * @return
     */
    public String getName(){//This accessor method returns the name of the user.
        return name;
    }
    
    /**
     *
     * @return
     */
    public String getSurname(){//This accessor method returns the name of the user.
        return surname;
    }
    
    /**
     *
     * @return
     */
    public int getGrade(){//This accessor method returns the grade of the user.
        return grade;
    }
    
    /**
     *
     * @return
     */
    public LocalDate getDOB(){//This accessor method returns the date of birth for the user.
        return dob;
    }
   
    /**
     *
     * @return 
     */
    public String existingUserLbl(){//This returns a string with an error message for any existing users, if a user tries to create an account with identical existing data
        return "User already exists, please sign in";
    }
    
    /**
     *
     * @param lbl1 : name error label
     * @param lbl2 : surname error label
     * @param lbl3 : grade error label
     * @param lbl4 : date of birth error label
     * @param lbl5 : existing user label
     * @return : whether the user can create an account or not if everything is correctly inputted
     */
    public boolean validate(javax.swing.JLabel lbl1, javax.swing.JLabel lbl2, javax.swing.JLabel lbl3, javax.swing.JLabel lbl4, javax.swing.JLabel lbl5){
        //This validates whether all the information inputted is correct and that no error messages are displayed so that the user can create an account.
        if(lbl1.getText().equals("") && lbl2.getText().equals("") && lbl3.getText().equals("") && lbl4.getText().equals("") && lbl5.getText().equals("")){
            return true;
        }
        
        return false;
     
    }
    
    
    
}
