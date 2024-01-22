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
import javax.swing.JTextField;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class DeleteCourseGUI {
    
    private JFrame jFrame;
    private JLabel lblCourseId;
    private JTextField txtCourseId;
    private JButton deleteButton;
    private JButton backButton;
    private Runnable onBack;

    public DeleteCourseGUI(Runnable onBack) {
         this.onBack = onBack;
         
         
        jFrame = new JFrame("Delete Course");
        
        lblCourseId = new JLabel("Course Id:");
        txtCourseId = new JTextField();
        
        
        deleteButton = new JButton("Delete Course");
//        deleteButton.addActionListener(e -> deleteStudentAction());
        
        backButton = new JButton("Back to Dashboard");
//        backButton.addActionListener(e -> deleteStudentAction());
        
        lblCourseId.setBounds(30,15, 100,30);
        txtCourseId.setBounds(110, 15, 200, 30);
        
        deleteButton.setBounds(110,80,130,25);
        backButton.setBounds(250,80,80,25);
        
        jFrame.add(lblCourseId);
        jFrame.add(txtCourseId);
        jFrame.add(deleteButton);
        jFrame.add(backButton);
        
        deleteButton.addActionListener(e -> deleteCourseAction());
        backButton.addActionListener(e -> backAction());
        
        jFrame.setSize(400,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    private void deleteCourseAction() {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF("deleteCourse");
            out.writeUTF(txtCourseId.getText());

            boolean studentAdded = new DataInputStream(socket.getInputStream()).readBoolean();

            if (studentAdded) {
                JOptionPane.showMessageDialog(jFrame, "Course deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to delete Course!");
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
