/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

/**
 *
 * @author Imtiyaaz Waggie
 */

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class AdminDashboardGUI {
   private JFrame frame;
    private JButton addStudentButton;
    private JButton viewStudentsButton;
    private JButton deleteStudentsButton;
    private JButton addCourseButton;
    private JButton viewCourseButton;
    private JButton deleteCourseButton;
    private JButton studentEnrolmentButton;
    private JButton logoutButton;
    private Runnable onLogout;

    public AdminDashboardGUI(Runnable onLogout) {
        this.onLogout = onLogout;

        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

       JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE); 

        addStudentButton = createColorfulButton("Add Student", new Color(255, 215, 0)); 
        viewStudentsButton = createColorfulButton("View Students", new Color(255, 215, 0)); 
        deleteStudentsButton = createColorfulButton("Delete Students", new Color(255, 215, 0)); 
        addCourseButton = createColorfulButton("Add Course", new Color(255, 215, 0)); 
        viewCourseButton = createColorfulButton("View Course", new Color(255, 215, 0)); 
        deleteCourseButton = createColorfulButton("Delete Course", new Color(255, 215, 0));
        studentEnrolmentButton = createColorfulButton("View Students Enrolled Course", new Color(255, 215, 0)); 
        logoutButton = createColorfulButton("Logout", new Color(255, 215, 0)); 

       
        panel.add(addStudentButton);
        panel.add(deleteStudentsButton);
        panel.add(viewStudentsButton);
        panel.add(addCourseButton);
        panel.add(viewCourseButton);
        panel.add(deleteCourseButton);
        panel.add(studentEnrolmentButton);
        panel.add(logoutButton);

        frame.add(panel);
        

        addStudentButton.addActionListener(e -> showAddStudentGUI());
        viewStudentsButton.addActionListener(e -> showViewStudentsGUI());
        deleteStudentsButton.addActionListener(e -> deleteStudentsGUI());
        addCourseButton.addActionListener(e -> showAddCourseGUI());
        viewCourseButton.addActionListener(e -> showViewCourseGUI());
        deleteCourseButton.addActionListener(e -> deleteCourseGUI());
        studentEnrolmentButton.addActionListener(e -> studentEnrolmentGUI());
        logoutButton.addActionListener(e -> logoutAction());

        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }
    private JButton createColorfulButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false); 
        return button;
    }

    private void showAddStudentGUI() {
        new AddStudentGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }
//
    private void showViewStudentsGUI() {
        new ViewAllStudentsGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }
//    
    private void deleteStudentsGUI() {
        new DeleteStudentGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }
    private void showAddCourseGUI() {
        new AddCourseGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }
//
    private void showViewCourseGUI() {
        new ViewAllCourseGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }
    
    private void deleteCourseGUI() {
        new DeleteCourseGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }
    private void studentEnrolmentGUI() {
        new ViewStudentsEnrolmentGUI(() -> frame.setVisible(true));
        frame.setVisible(false);
    }


    private void logoutAction() {
        frame.dispose();
        onLogout.run();
    }

    public void dispose() {
        frame.dispose();
    }
}





