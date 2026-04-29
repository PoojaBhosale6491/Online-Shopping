package LibraryMangement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {
            // Load MySQL Driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library_db",
                    "root",
                    "poojabhosale"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}