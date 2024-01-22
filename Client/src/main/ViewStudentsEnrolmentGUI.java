/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Imtiyaaz Waggie
 */
public class ViewStudentsEnrolmentGUI {
          private JFrame jFrame;
    
    private JTable studentsTable;
    
    private JButton backButton;
    private Runnable onBack;

    public ViewStudentsEnrolmentGUI(Runnable onBack) {
        this.onBack = onBack;

        jFrame = new JFrame("View All Students Enrolled");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);

        List<List<String>> data = getCoursesData();
        List<String> columnNames = new ArrayList<>();
        columnNames.add("Enrolment ID");
        columnNames.add("Student ID");
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Email");
        columnNames.add("Course ID");
        columnNames.add("Course Name");
        
        studentsTable = new JTable(convertListTo2DArray(data), columnNames.toArray(new String[0]));

        studentsTable.setBounds(60, 60, 200, 400);
        
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

    private List<List<String>> getCoursesData() {
        List<List<String>> data = new ArrayList<>();

        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF("getStudentsEnrolment");

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
