package Utils;

import org.testng.annotations.Test;

public class DatabaseConnection {

    private String name;
    private String externalId;

    public String getName() {
        return name;
    }

    public String getExternalId() {
        return externalId;
    }

    @Test
    public void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM stations WHERE id = 6")) {

            while (rs.next()) {
                System.out.println("Station name: " + rs.getString("name") +
                                   ", external_id: " + rs.getString("external_id")
                                    + ", latitude: " + rs.getDouble("latitude") +
                                   ", longitude: " + rs.getDouble("longitude") +
                                   ", altitude: " + rs.getDouble("altitude"));
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }


    }


}
