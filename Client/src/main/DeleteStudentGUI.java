/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

/**
 *
 * @author Imtiyaaz Waggie
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteStudentGUI {

    private JFrame jFrame;
    private JLabel lblStudentId;
    private JTextField txtStudentId;
    private JButton deleteButton;
    private JButton backButton;
    private Runnable onBack;

    public DeleteStudentGUI(Runnable onBack) {
         this.onBack = onBack;
         
         
        jFrame = new JFrame("Delete Student");
        
        lblStudentId = new JLabel("Student Id:");
        txtStudentId = new JTextField();
        
        
        deleteButton = new JButton("Delete Student");
//        deleteButton.addActionListener(e -> deleteStudentAction());
        
        backButton = new JButton("Back to Dashboard");
//        backButton.addActionListener(e -> deleteStudentAction());
        
        lblStudentId.setBounds(30,15, 100,30);
        txtStudentId.setBounds(110, 15, 200, 30);
        
        deleteButton.setBounds(110,80,130,25);
        backButton.setBounds(250,80,80,25);
        
        jFrame.add(lblStudentId);
        jFrame.add(txtStudentId);
        jFrame.add(deleteButton);
        jFrame.add(backButton);
        
        deleteButton.addActionListener(e -> deleteStudentAction());
        backButton.addActionListener(e -> backAction());
        
        jFrame.setSize(400,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    private void deleteStudentAction() {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF("deleteStudent");
            out.writeUTF(txtStudentId.getText());

            boolean studentAdded = new DataInputStream(socket.getInputStream()).readBoolean();

            if (studentAdded) {
                JOptionPane.showMessageDialog(jFrame, "Student deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to delete Student!");
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
