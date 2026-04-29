package LibraryMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField tfUser;
    JPasswordField pfPass;
    JButton loginBtn, cancelBtn;

    LoginPage() {

        setTitle("Library Management System - Login");
        setSize(400, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        tfUser = new JTextField();
        tfUser.setBounds(150, 50, 150, 30);
        add(tfUser);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        pfPass = new JPasswordField();
        pfPass.setBounds(150, 100, 150, 30);
        add(pfPass);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(80, 160, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(200, 160, 100, 30);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String username = tfUser.getText();
            String password = new String(pfPass.getPassword());

            try {

                Connection con = DBConnection.getConnection();

                String query = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");

                    this.setVisible(false);

                    // Open Dashboard
                    new Dashboard();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == cancelBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
