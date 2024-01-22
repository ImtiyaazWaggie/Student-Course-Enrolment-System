/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class Client {
    private JFrame jFrame;
    private JButton adminloginButton;
    private JButton studentLoginButton;
    
    public Client(){
        jFrame = new JFrame("Login");
        adminloginButton = new JButton("Admin");
        studentLoginButton = new JButton("Student");
        
        // Admin Button
        adminloginButton.setBounds(50,50,80, 30);
       
        // Student Button
        studentLoginButton.setBounds(150,50,80, 30);
        
        
        jFrame.add(adminloginButton);
        jFrame.add(studentLoginButton);
        
        adminloginButton.addActionListener(e -> showAdminLoginGUI());
        studentLoginButton.addActionListener(e -> showStudentLoginGUI());
        
        jFrame.setSize(300,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);   
    }
    
    private void showAdminLoginGUI(){
        new AdminLoginPanelGUI(() -> new AdminDashboardGUI(() -> new Client()));
        jFrame.dispose();
    }
    
    private void showStudentLoginGUI(){
        new StudentLoginPanelGUI(() -> new StudentDashboardGUI(() -> new Client()));
        jFrame.dispose();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client());
    }
}
