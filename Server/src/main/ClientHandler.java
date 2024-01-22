/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.server;


import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class ClientHandler extends Thread{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    
    public void run(){
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            
            Student student = new Student();
            Course course = new Course();
            StudentDAO studDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();

            String operation= in.readUTF();

            switch(operation){
                case "adminAuthentication":
                    String adminIdInput = in.readUTF();
                    String adminPasswordInput = in.readUTF();

                    if (adminAuthentication(adminIdInput, adminPasswordInput)) {
                        out.writeBoolean(true);
                    } else {
                        out.writeBoolean(false);
                    }
                break;
                case "studentAuthentication":
                    String studentId = in.readUTF();
                    String studentPassword = in.readUTF();
                    
                    student.setStudentId(studentId);
                    student.setPassword(studentPassword);
                    
                    if (studDAO.studentAuthentication(student)) {
                        out.writeBoolean(true);
                    } else {
                        out.writeBoolean(false);
                    }
                break;
                
                case "addStudent":
                    String addStudentId = in.readUTF();
                    String firstName = in.readUTF();
                    String lastName = in.readUTF();
                    String studentEmail = in.readUTF();
                    String addStudentPassword = in.readUTF();
                    
                    student.setStudentId(addStudentId);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setEmail(studentEmail);
                    student.setPassword(addStudentPassword);
                    
                    if (studDAO.addStudent(student)) {
                        out.writeBoolean(true);
                    } else {
                        out.writeBoolean(false);
                    }
                break;
                
                case "deleteStudent":
                  
                    String removeStudentRecord = in.readUTF();
                    student.setStudentId(removeStudentRecord);
                    
                    if (studDAO.deleteStudent(student)) {
                        out.writeBoolean(true);
                    } else {
                        out.writeBoolean(false);
                    }
                    break; 
                    
                case "getStudents":
                    studDAO.sendStudentsData(out);
                    
                case "getStudentsEnrolment":
                    studDAO.sendEnrolmentData(out);
                    
                case "addCourse":
                  
                    String addCourseId = in.readUTF();
                    String addCourseName = in.readUTF();
                    String addInstructor = in.readUTF();
                    String addSemester = in.readUTF();
                    
                    course.setCourseId(addCourseId);
                    course.setCourseName(addCourseName);
                    course.setInstructor(addInstructor);
                    course.setSemester(addSemester);
                    
                    if (courseDAO.addCourse(course)) {
                        out.writeBoolean(true);
                    } else {
                        out.writeBoolean(false);
                    }
                    break; 
                    
                case "deleteCourse":
                  
                    String removeCourseRecord = in.readUTF();
                    course.setCourseId(removeCourseRecord);
                    
                    if (courseDAO.deleteCourse(course)) {
                        out.writeBoolean(true);
                    } else {
                        out.writeBoolean(false);
                    }
                    break;
                    
                case "getCourses":
                    courseDAO.sendCoursesData(out);
            }                
        } 
        
        catch(Exception e){
        
        }
    }
    
    boolean adminAuthentication(String adminId, String adminPassword) {
        String query = "SELECT COUNT(*) FROM ADMIN WHERE ADMIN_ID = ? AND PASSWORD = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, adminId);
            ps.setString(2, adminPassword);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
