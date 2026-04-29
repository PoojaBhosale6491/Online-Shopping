package LibraryMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReturnBook extends JFrame implements ActionListener {

    JTextField tfStudentId, tfBookId;
    JButton returnBtn;

    ReturnBook() {

        setTitle("Return Book");
        setSize(400, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Student ID:");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        tfStudentId = new JTextField();
        tfStudentId.setBounds(150, 50, 150, 30);
        add(tfStudentId);

        JLabel l2 = new JLabel("Book ID:");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        tfBookId = new JTextField();
        tfBookId.setBounds(150, 100, 150, 30);
        add(tfBookId);

        returnBtn = new JButton("Return Book");
        returnBtn.setBounds(120, 150, 140, 30);
        returnBtn.addActionListener(this);
        add(returnBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int studentId = Integer.parseInt(tfStudentId.getText());
        int bookId = Integer.parseInt(tfBookId.getText());

        try {

            Connection con = DBConnection.getConnection();

            // 1️⃣ Check if book was issued
            String checkQuery = "SELECT * FROM issue_books WHERE student_id=? AND book_id=?";
            PreparedStatement pst1 = con.prepareStatement(checkQuery);
            pst1.setInt(1, studentId);
            pst1.setInt(2, bookId);
            ResultSet rs = pst1.executeQuery();

            if (rs.next()) {

                // 2️⃣ Insert into return_books
                String returnQuery = "INSERT INTO return_books (student_id, book_id, return_date) VALUES (?, ?, CURDATE())";
                PreparedStatement pst2 = con.prepareStatement(returnQuery);
                pst2.setInt(1, studentId);
                pst2.setInt(2, bookId);
                pst2.executeUpdate();

                // 3️⃣ Increase quantity
                String updateQuery = "UPDATE books SET quantity = quantity + 1 WHERE book_id=?";
                PreparedStatement pst3 = con.prepareStatement(updateQuery);
                pst3.setInt(1, bookId);
                pst3.executeUpdate();

                // 4️⃣ Delete from issue_books
                String deleteQuery = "DELETE FROM issue_books WHERE student_id=? AND book_id=?";
                PreparedStatement pst4 = con.prepareStatement(deleteQuery);
                pst4.setInt(1, studentId);
                pst4.setInt(2, bookId);
                pst4.executeUpdate();

                JOptionPane.showMessageDialog(this, "Book Returned Successfully!");

            } else {
                JOptionPane.showMessageDialog(this, "No Record Found!");
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReturnBook();
    }
}