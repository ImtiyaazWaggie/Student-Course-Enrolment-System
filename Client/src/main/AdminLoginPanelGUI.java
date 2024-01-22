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
public class AdminLoginPanelGUI {
    private JFrame jFrame;
    private JLabel lblAdminId;
    private JTextField txtAdminId;
    private JLabel lblAdminPassword;
    private JPasswordField fldAdminPassword;
    private JButton loginButton;
    private Runnable onSuccessfulLogin;

    public AdminLoginPanelGUI(Runnable onSuccessfulLogin) {
        this.onSuccessfulLogin = onSuccessfulLogin;
        

        jFrame = new JFrame("Admin Login");
        
        lblAdminId = new JLabel("Admin Id:");
        txtAdminId = new JTextField();
        
        lblAdminPassword = new JLabel("Passowrd:");
        fldAdminPassword = new JPasswordField();
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginAction());
        
        lblAdminId.setBounds(30,15, 100,30);
        txtAdminId.setBounds(110, 15, 200, 30);
        
        lblAdminPassword.setBounds(30,50, 100,30);
        fldAdminPassword.setBounds(110, 50, 200, 30);
        
        loginButton.setBounds(150,100,80,25);
        
        jFrame.add(lblAdminId);
        jFrame.add(txtAdminId);
        jFrame.add(lblAdminPassword);
        jFrame.add(fldAdminPassword);
        jFrame.add(loginButton);
        
        jFrame.setSize(400,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
    
    private void loginAction() {

        // Check if the fields are empty
        if (txtAdminId.getText().trim().isEmpty() || new String(fldAdminPassword.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(jFrame, "Please enter both AdminId and Password!");
            return;
        }

        try (Socket socket = new Socket("localhost", 8080);
                
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream())
            ) 
        
        {
            out.writeUTF("adminAuthentication");
            out.writeUTF(txtAdminId.getText());
            out.writeUTF(new String(fldAdminPassword.getPassword()));

            boolean validAdminAuthentication = in.readBoolean();

            if (validAdminAuthentication) {
                jFrame.dispose();
                onSuccessfulLogin.run();
            } else {
                JOptionPane.showMessageDialog(jFrame, "Invalid Admin Credentials!");
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
