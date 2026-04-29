package LibraryMangement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBooks extends JFrame {

    JTable table;
    DefaultTableModel model;

    ViewBooks() {

        setTitle("View Books");
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        // Columns
        model.addColumn("Book ID");
        model.addColumn("Name");
        model.addColumn("Author");
        model.addColumn("Quantity");

        JScrollPane sp = new JScrollPane(table);
        add(sp);

        loadData();

        setVisible(true);
    }

    // Load data from database
    public void loadData() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM books";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int qty = rs.getInt("quantity");

                model.addRow(new Object[]{id, name, author, qty});
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewBooks();
    }
}