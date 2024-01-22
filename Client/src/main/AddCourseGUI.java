/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class AddCourseGUI {
    
    private JFrame jFrame;
    private JLabel lblCourseId;
    private JTextField txtCourseId;
    private JLabel lblCourseName;
    private JTextField txtCourseName;
    private JLabel lblInstructor;
    private JTextField txtInstructor;
    private JLabel lblSemester;
    private JTextField txtSemester;
    
    private JButton addButton;
    private JButton backButton;
    private Runnable onBack;
 
    public AddCourseGUI(Runnable onBack) {
        this.onBack = onBack;

        jFrame = new JFrame("Add Course");
        
        lblCourseId = new JLabel("Course Id:");
        txtCourseId = new JTextField();
        
        lblCourseName = new JLabel("Course Name:");
        txtCourseName = new JTextField();
        
        lblInstructor = new JLabel("Instructor:");
        txtInstructor = new JTextField();
       
        lblSemester = new JLabel("Semester:");
        txtSemester = new JTextField();
       
        
        addButton = new JButton("Add Student");
        backButton = new JButton("Back to Dashboard");
        
        lblCourseId.setBounds(10, 10, 90, 25);
        txtCourseId.setBounds(100, 10, 150, 25);
        
        lblCourseName.setBounds(10, 50, 90, 25);
        txtCourseName.setBounds(100, 50, 150, 25);
        
        lblInstructor.setBounds(10, 90, 90, 25);
        txtInstructor.setBounds(100, 90, 150, 25);
        
        lblSemester.setBounds(10, 130, 90, 25);
        txtSemester.setBounds(100, 130, 150, 25);
        
        addButton.setBounds(100,220,120,25);
        backButton.setBounds(250,220,80,25);
        
        jFrame.add(lblCourseId);
        jFrame.add(txtCourseId);
        jFrame.add(lblCourseName);
        jFrame.add(txtCourseName);
        jFrame.add(lblInstructor);
        jFrame.add(txtInstructor);
        jFrame.add(lblSemester);
        jFrame.add(txtSemester);
        jFrame.add(addButton);
        jFrame.add(backButton);
        
        addButton.addActionListener(e -> addCourseAction());
        backButton.addActionListener(e -> backAction());
        
        jFrame.setSize(500, 300);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        
    }

    private void addCourseAction() {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF("addCourse");
            out.writeUTF(txtCourseId.getText());
            out.writeUTF(txtCourseName.getText());
            out.writeUTF(txtInstructor.getText());
            out.writeUTF(txtSemester.getText());
            boolean studentAdded = new DataInputStream(socket.getInputStream()).readBoolean();

            if (studentAdded) {
                JOptionPane.showMessageDialog(jFrame, "Course Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to Add Course!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void backAction() {
        jFrame.dispose();
        onBack.run();
    }

    public void dispose() {
        jFrame.dispose();
    } 
}
