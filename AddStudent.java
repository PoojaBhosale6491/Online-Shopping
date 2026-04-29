package LibraryMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddStudent extends JFrame implements ActionListener {

    JTextField tfId, tfName, tfCourse, tfContact;
    JButton addBtn, clearBtn;

    AddStudent() {

        setTitle("Add Student");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Student ID:");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        tfId = new JTextField();
        tfId.setBounds(150, 50, 150, 30);
        add(tfId);

        JLabel l2 = new JLabel("Name:");
        l2.setBounds(50, 90, 100, 30);
        add(l2);

        tfName = new JTextField();
        tfName.setBounds(150, 90, 150, 30);
        add(tfName);

        JLabel l3 = new JLabel("Course:");
        l3.setBounds(50, 130, 100, 30);
        add(l3);

        tfCourse = new JTextField();
        tfCourse.setBounds(150, 130, 150, 30);
        add(tfCourse);

        JLabel l4 = new JLabel("Contact:");
        l4.setBounds(50, 170, 100, 30);
        add(l4);

        tfContact = new JTextField();
        tfContact.setBounds(150, 170, 150, 30);
        add(tfContact);

        addBtn = new JButton("Add Student");
        addBtn.setBounds(70, 230, 120, 30);
        addBtn.addActionListener(this);
        add(addBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(200, 230, 100, 30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {

            int id = Integer.parseInt(tfId.getText());
            String name = tfName.getText();
            String course = tfCourse.getText();
            String contact = tfContact.getText();

            try {

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO students (student_id, name, course, contact) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setString(3, course);
                pst.setString(4, contact);

                int i = pst.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Student Added Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error adding student!");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == clearBtn) {

            tfId.setText("");
            tfName.setText("");
            tfCourse.setText("");
            tfContact.setText("");
        }
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}