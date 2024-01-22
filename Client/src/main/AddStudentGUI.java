/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

/**
 *
 * @author Imtiyaaz Waggie
 */

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.security.SecureRandom;

public class AddStudentGUI {
    
    private JFrame jFrame;
    private JLabel lblStudentId;
    private JTextField txtStudentId;
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JLabel lblLastName;
    private JTextField txtLastName;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
       private JButton generateButton;
    private JButton addButton;
    private JButton backButton;
    private Runnable onBack;
 
    public AddStudentGUI(Runnable onBack) {
        this.onBack = onBack;

        jFrame = new JFrame("Add Student");
        
        lblStudentId = new JLabel("Student Id:");
        txtStudentId = new JTextField();
        
        lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField();
        
        lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField();
       
        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        
        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(e -> generatePasswordAction());        
        
        addButton = new JButton("Add Student");
        backButton = new JButton("Back to Dashboard");
        
        lblStudentId.setBounds(10, 10, 90, 25);
        txtStudentId.setBounds(100, 10, 150, 25);
        
        lblFirstName.setBounds(10, 50, 90, 25);
        txtFirstName.setBounds(100, 50, 150, 25);
        
        lblLastName.setBounds(10, 90, 90, 25);
        txtLastName.setBounds(100, 90, 150, 25);
        
        lblEmail.setBounds(10, 130, 90, 25);
        txtEmail.setBounds(100, 130, 150, 25);
        
        lblPassword.setBounds(10, 170, 90, 25);
        txtPassword.setBounds(100, 170, 150, 30);
        
        
        generateButton.setBounds(270, 170, 170, 30);
        addButton.setBounds(100,220,120,25);
        backButton.setBounds(250,220,80,25);
        
        jFrame.add(lblStudentId);
        jFrame.add(txtStudentId);
        jFrame.add(lblFirstName);
        jFrame.add(txtFirstName);
        jFrame.add(lblLastName);
        jFrame.add(txtLastName);
        jFrame.add(lblEmail);
        jFrame.add(txtEmail);
        jFrame.add(lblPassword);
        jFrame.add(txtPassword);
        jFrame.add(generateButton);
        jFrame.add(addButton);
        jFrame.add(backButton);
        
        addButton.addActionListener(e -> addStudentAction());
        backButton.addActionListener(e -> backAction());
        
        jFrame.setSize(500, 300);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        
    }

    private void addStudentAction() {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF("addStudent");
            out.writeUTF(txtStudentId.getText());
            out.writeUTF(txtFirstName.getText());
            out.writeUTF(txtLastName.getText());
            out.writeUTF(txtEmail.getText());
            out.writeUTF(new String(txtPassword.getPassword()));

            boolean studentAdded = new DataInputStream(socket.getInputStream()).readBoolean();

            if (studentAdded) {
                JOptionPane.showMessageDialog(jFrame, "Student Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to Add Student!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
   
    private void generatePasswordAction() {
        String password = generatePassword(8);
        txtPassword.setText(password);
    }
    
    private static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";

        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            password.append(randomChar);
        }

        return password.toString();
    }

    private void backAction() {
        jFrame.dispose();
        onBack.run();
    }

    public void dispose() {
        jFrame.dispose();
    }   
}
