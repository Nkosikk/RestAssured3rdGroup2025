package Utils;

import org.testng.annotations.Test;

public class DatabaseConnection {


    @Test
    public static String[] testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = 24")) {

            if (rs.next()) {
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password");
                return new String[]{dbUsername, dbPassword};
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // or throw exception if needed
    }

}