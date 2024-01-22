/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.server;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class Student {


    private String student_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    
    
    public Student(){
    
    }
    
    public Student(String student_id){
        this.student_id = student_id;    
    }
    
    public Student(String student_id,String password){
        this.student_id = student_id;
        this.password = password;
    }

    // Constructor
    public Student(String student_id, String first_name, String last_name, String email, String password) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters for username
    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    // Getters and Setters for firstName
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
        // Getters and Setters for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "";
    }    
}

