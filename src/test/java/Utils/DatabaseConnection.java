package Utils;

import org.testng.annotations.Test;
@Test
public class DatabaseConnection {

    public String externalid;
    public String  St_name;
    public DatabaseConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM stations WHERE id = 1")) {

            rs.next();

            externalid = rs.getString("external_id");
            St_name = rs.getString("name");
                System.out.println("external Id: " + rs.getString("external_id") +
                                   " Name: " + rs.getString("St_name"));

        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }


}
