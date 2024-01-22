/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class StudentDashboardGUI {
   private JFrame jFrame;
    private JButton viewCourseButton;
    private JButton logoutButton;
    private Runnable onLogout;

    public StudentDashboardGUI(Runnable onLogout) {
        this.onLogout = onLogout;
        
        jFrame = new JFrame("Student Dashboard");
        viewCourseButton = new JButton("Available Courses");
        logoutButton = new JButton("Logout");
        
        // Admin Button
        viewCourseButton.setBounds(20,50,150, 30);
       
        // Student Button
        logoutButton.setBounds(180,50,80, 30);
        
        
        jFrame.add(viewCourseButton);
        jFrame.add(logoutButton);
        
        viewCourseButton.addActionListener(e -> showViewCourseGUI());
        logoutButton.addActionListener(e -> logoutAction());
        
        jFrame.setSize(300,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);   

    }

    private void showViewCourseGUI() {
        new ViewAllCourseGUI(() -> jFrame.setVisible(true));
        jFrame.setVisible(false);
    }



    private void logoutAction() {
        jFrame.dispose();
        onLogout.run();
    }

    public void dispose() {
        jFrame.dispose();
    }
}
