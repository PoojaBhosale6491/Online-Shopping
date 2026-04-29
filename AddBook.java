package LibraryMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddBook extends JFrame implements ActionListener {

    JTextField tfBookId, tfName, tfAuthor, tfQty;
    JButton addBtn, clearBtn;

    AddBook() {

        setTitle("Add Book");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Book ID:");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        tfBookId = new JTextField();
        tfBookId.setBounds(150, 50, 150, 30);
        add(tfBookId);

        JLabel l2 = new JLabel("Name:");
        l2.setBounds(50, 90, 100, 30);
        add(l2);

        tfName = new JTextField();
        tfName.setBounds(150, 90, 150, 30);
        add(tfName);

        JLabel l3 = new JLabel("Author:");
        l3.setBounds(50, 130, 100, 30);
        add(l3);

        tfAuthor = new JTextField();
        tfAuthor.setBounds(150, 130, 150, 30);
        add(tfAuthor);

        JLabel l4 = new JLabel("Quantity:");
        l4.setBounds(50, 170, 100, 30);
        add(l4);

        tfQty = new JTextField();
        tfQty.setBounds(150, 170, 150, 30);
        add(tfQty);

        addBtn = new JButton("Add Book");
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

            int bookId = Integer.parseInt(tfBookId.getText());
            String name = tfName.getText();
            String author = tfAuthor.getText();
            int qty = Integer.parseInt(tfQty.getText());

            try {

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO books (book_id, name, author, quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, bookId);
                pst.setString(2, name);
                pst.setString(3, author);
                pst.setInt(4, qty);

                int i = pst.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Book Added Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error adding book!");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == clearBtn) {
            tfBookId.setText("");
            tfName.setText("");
            tfAuthor.setText("");
            tfQty.setText("");
        }
    }

    public static void main(String[] args) {
        new AddBook();
    }
}