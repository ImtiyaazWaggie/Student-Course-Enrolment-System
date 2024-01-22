/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.server;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class Course {
    
    private String course_id;
    private String course_name;
    private String instructor;
    private String semester;
    
    
    public Course(){
    
    }
    
    public Course(String course_id){
        this.course_id = course_id;    
    }
  
    // Constructor
    public Course(String course_id, String course_name, String instructor, String semester) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.instructor = instructor;
        this.semester = semester;
    }

    // Getters and Setters for username
    public String getCourseId() {
        return course_id;
    }

    public void setCourseId(String course_id) {
        this.course_id = course_id;
    }

    // Getters and Setters for firstName
    public String getCourseName() {
        return course_name;
    }

    public void setCourseName(String course_name) {
        this.course_name = course_name;
    }
    
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    


    @Override
    public String toString() {
        return "";
    }    
}
