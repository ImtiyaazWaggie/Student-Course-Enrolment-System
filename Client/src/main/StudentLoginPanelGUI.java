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
public class StudentLoginPanelGUI {
    private JFrame jFrame;
    private JLabel lblStudentId;
    private JTextField txtStudentId;
    private JLabel lblStudentPassword;
    private JPasswordField fldStudentPassword;
    private JButton loginButton;
    private Runnable onSuccessfulLogin;

    public StudentLoginPanelGUI(Runnable onSuccessfulLogin) {
        this.onSuccessfulLogin = onSuccessfulLogin;

        jFrame = new JFrame("Student Login");
        
        lblStudentId = new JLabel("Student Id:");
        txtStudentId = new JTextField();
        
        lblStudentPassword = new JLabel("Passowrd:");
        fldStudentPassword = new JPasswordField();
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginAction());
        
        lblStudentId.setBounds(30,15, 100,30);
        txtStudentId.setBounds(110, 15, 200, 30);
        
        lblStudentPassword.setBounds(30,50, 100,30);
        fldStudentPassword.setBounds(110, 50, 200, 30);
        
        loginButton.setBounds(150,100,80,25);
        
        jFrame.add(lblStudentId);
        jFrame.add(txtStudentId);
        jFrame.add(lblStudentPassword);
        jFrame.add(fldStudentPassword);
        jFrame.add(loginButton);
        
        jFrame.setSize(400,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
    
    private void loginAction() {

        // Check if the fields are empty
        if (txtStudentId.getText().trim().isEmpty() || new String(fldStudentPassword.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(jFrame, "Please enter both StudentId and Password!");
            return;
        }

        try (Socket socket = new Socket("localhost", 8080);
                
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream())
            ) 
        
        {
            out.writeUTF("studentAuthentication");
            out.writeUTF(txtStudentId.getText());
            out.writeUTF(new String(fldStudentPassword.getPassword()));

            boolean validAdminAuthentication = in.readBoolean();

            if (validAdminAuthentication) {
                jFrame.dispose();
                onSuccessfulLogin.run();
            } else {
                JOptionPane.showMessageDialog(jFrame, "Invalid Student Credentials!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(jFrame, "Error connecting to server. Please try again later.");
        }
    }
    
    public void dispose() {
    jFrame.dispose();
    }
}
