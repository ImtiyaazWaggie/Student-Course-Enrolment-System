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
import java.awt.FlowLayout;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class ViewAllStudentsGUI {
    
    private JFrame jFrame;
    
    private JTable studentsTable;
    
    private JButton backButton;
    private Runnable onBack;

    public ViewAllStudentsGUI(Runnable onBack) {
        this.onBack = onBack;

        jFrame = new JFrame("View Students");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);

        List<List<String>> data = getStudentsData();
        List<String> columnNames = new ArrayList<>();
        columnNames.add("Student ID");
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Email");
        columnNames.add("Password");
        
        studentsTable = new JTable(convertListTo2DArray(data), columnNames.toArray(new String[0]));

        JScrollPane scrollPane = new JScrollPane(studentsTable);

        backButton = new JButton("Back to Dashboard");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.WHITE);
        panel.add(scrollPane);
        panel.add(backButton);

        jFrame.add(panel);

        backButton.addActionListener(e -> backAction());
        
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
}
    
    
    private String[][] convertListTo2DArray(List<List<String>> list) {
        String[][] array = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).toArray(new String[0]);
        }
        return array;
    }

    private List<List<String>> getStudentsData() {
        List<List<String>> data = new ArrayList<>();

        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF("getStudents");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            data = (List<List<String>>) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }

    private void backAction() {
        jFrame.dispose();
        onBack.run();
    }

    public void dispose() {
        jFrame.dispose();
    }
}
