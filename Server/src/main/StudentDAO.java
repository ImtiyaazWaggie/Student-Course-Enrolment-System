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
public class StudentDAO {
    boolean studentAuthentication(Student student) {
        String query = "SELECT COUNT(*) FROM STUDENT WHERE STUDENT_ID = ? AND PASSWORD = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, student.getStudentId());
            ps.setString(2, student.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
        
    boolean addStudent(Student student) {
        String query = "INSERT INTO STUDENT (STUDENT_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, student.getStudentId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPassword());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    boolean deleteStudent(Student student) {
        String query = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, student.getStudentId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void sendStudentsData(DataOutputStream out) throws IOException {
        String query = "SELECT STUDENT_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM STUDENT";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            List<List<String>> data = new ArrayList<>();

            while (rs.next()) {
                List<String> studentData = new ArrayList<>();
                studentData.add(rs.getString("STUDENT_ID"));
                studentData.add(rs.getString("FIRST_NAME"));
                studentData.add(rs.getString("LAST_NAME"));
                studentData.add(rs.getString("EMAIL"));
                studentData.add(rs.getString("PASSWORD"));
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
    
    public void sendEnrolmentData(DataOutputStream out) throws IOException {
        String query = "SELECT e.enrolment_id, p.student_id, p.first_name,p.last_name,p.email, d.course_id, d.course_name FROM enrolment e JOIN STUDENT p ON e.student_id = p.student_id JOIN COURSE d ON e.course_id = d.course_id";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            List<List<String>> data = new ArrayList<>();

            while (rs.next()) {
                List<String> studentData = new ArrayList<>();
                studentData.add(rs.getString("ENROLMENT_ID"));
                studentData.add(rs.getString("STUDENT_ID"));
                studentData.add(rs.getString("FIRST_NAME"));
                studentData.add(rs.getString("LAST_NAME"));
                studentData.add(rs.getString("EMAIL"));
                studentData.add(rs.getString("COURSE_ID"));
                studentData.add(rs.getString("COURSE_NAME"));
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
