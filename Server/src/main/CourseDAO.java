/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class CourseDAO {
    
    boolean addCourse(Course course) {
        String query = "INSERT INTO COURSE (COURSE_ID, COURSE_NAME, INSTRUCTOR, SEMESTER) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, course.getCourseId());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getInstructor());
            ps.setString(4, course.getSemester());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    boolean deleteCourse(Course course) {
        String query = "DELETE FROM COURSE WHERE COURSE_ID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, course.getCourseId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        public void sendCoursesData(DataOutputStream out) throws IOException {
        String query = "SELECT * FROM COURSE";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            List<List<String>> data = new ArrayList<>();

            while (rs.next()) {
                List<String> studentData = new ArrayList<>();
                studentData.add(rs.getString("COURSE_ID"));
                studentData.add(rs.getString("COURSE_NAME"));
                studentData.add(rs.getString("INSTRUCTOR"));
                studentData.add(rs.getString("SEMESTER"));
                data.add(studentData);
            }

            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(data);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void sendStudentEnrolmentInCoursesData(Course course, DataOutputStream out) throws IOException {
        String query = "SELECT p.student_id, p.first_name, p.last_name, p.email FROM STUDENT p JOIN enrolment e ON p.student_id = e.student_id WHERE e.Course = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, course.getCourseId());
            
            ResultSet rs = ps.executeQuery();
            List<List<String>> data = new ArrayList<>();

            while (rs.next()) {
                List<String> studentData = new ArrayList<>();
                studentData.add(rs.getString("ENROLMENT_ID"));
                studentData.add(rs.getString("STUDENT_ID"));
                studentData.add(rs.getString("FIRST_NAME"));
                studentData.add(rs.getString("LAST_NAME"));
                studentData.add(rs.getString("EMAIL"));
                data.add(studentData);
            }

            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(data);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
