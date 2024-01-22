/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.server;

/**
 *
 * @author Imtiyaaz.Waggie
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/StudentCourseEnrolment";
    private static final String USERNAME = "Imtiyaaz";
    private static final String PASSWWORD = "admin";
    
    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load Derby JDBC driver.", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWWORD);
    }
}
