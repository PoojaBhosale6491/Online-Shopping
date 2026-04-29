package LibraryMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {

    JButton addBookBtn, viewBookBtn, issueBookBtn, returnBookBtn, addStudentBtn, logoutBtn;

    Dashboard() {

        setTitle("Library Management System - Dashboard");
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("LIBRARY DASHBOARD");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(200, 20, 300, 30);
        add(heading);

        // Buttons
        addBookBtn = new JButton("Add Book");
        addBookBtn.setBounds(50, 80, 200, 40);
        addBookBtn.addActionListener(this);
        add(addBookBtn);

        viewBookBtn = new JButton("View Books");
        viewBookBtn.setBounds(300, 80, 200, 40);
        viewBookBtn.addActionListener(this);
        add(viewBookBtn);

        addStudentBtn = new JButton("Add Student");
        addStudentBtn.setBounds(50, 140, 200, 40);
        addStudentBtn.addActionListener(this);
        add(addStudentBtn);

        issueBookBtn = new JButton("Issue Book");
        issueBookBtn.setBounds(300, 140, 200, 40);
        issueBookBtn.addActionListener(this);
        add(issueBookBtn);

        returnBookBtn = new JButton("Return Book");
        returnBookBtn.setBounds(50, 200, 200, 40);
        returnBookBtn.addActionListener(this);
        add(returnBookBtn);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(300, 200, 200, 40);
        logoutBtn.addActionListener(this);
        add(logoutBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    	if (e.getSource() == addBookBtn) {
    	    new AddBook();
    	}
    	
    	
        else if (e.getSource() == viewBookBtn) {
            new ViewBooks();
        }

        else if (e.getSource() == addStudentBtn) {
           new AddStudent();
            // new AddStudent();
        }

        else if (e.getSource() == issueBookBtn) {
          new IssueBook();
            // new IssueBook();
        }

        else if (e.getSource() == returnBookBtn) {
            new IssueBook();
            // new ReturnBook();
        }

        else if (e.getSource() == logoutBtn) {
            this.setVisible(false);
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
