package Utils;

import org.testng.annotations.Test;

public class DatabaseConnection {
    public static String retrievedEmail;
    public static String retrievedPassword;

    public static void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_teaching";
        String username = "ndosian6b8b7_teaching";
        String password = "^{SF0a=#~[~p)@l1";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM login_user WHERE id = 1")) {

            while (rs.next()) {
                retrievedEmail = rs.getString("email");
                retrievedPassword = rs.getString("password");
                System.out.println("Email: " + rs.getString("email") +
                                   ", Password: " + rs.getString("password"));
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }


}
