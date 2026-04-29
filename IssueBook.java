package LibraryMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class IssueBook extends JFrame implements ActionListener {

    JTextField tfStudentId, tfBookId;
    JButton issueBtn;

    IssueBook() {

        setTitle("Issue Book");
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

        issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(120, 150, 120, 30);
        issueBtn.addActionListener(this);
        add(issueBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int studentId = Integer.parseInt(tfStudentId.getText());
        int bookId = Integer.parseInt(tfBookId.getText());

        try {

            Connection con = DBConnection.getConnection();

            // 1️⃣ Check book quantity
            String checkQuery = "SELECT quantity FROM books WHERE book_id=?";
            PreparedStatement pst1 = con.prepareStatement(checkQuery);
            pst1.setInt(1, bookId);
            ResultSet rs = pst1.executeQuery();

            if (rs.next()) {

                int qty = rs.getInt("quantity");

                if (qty > 0) {

                    // 2️⃣ Insert into issue_books
                    String issueQuery = "INSERT INTO issue_books (student_id, book_id, issue_date) VALUES (?, ?, CURDATE())";
                    PreparedStatement pst2 = con.prepareStatement(issueQuery);
                    pst2.setInt(1, studentId);
                    pst2.setInt(2, bookId);
                    pst2.executeUpdate();

                    // 3️⃣ Reduce quantity
                    String updateQuery = "UPDATE books SET quantity = quantity - 1 WHERE book_id=?";
                    PreparedStatement pst3 = con.prepareStatement(updateQuery);
                    pst3.setInt(1, bookId);
                    pst3.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Book Issued Successfully!");

                } else {
                    JOptionPane.showMessageDialog(this, "Book Out of Stock!");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Book Not Found!");
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new IssueBook();
    }
}
