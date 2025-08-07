package Utils;

import org.testng.annotations.Test;

public class DatabaseConnection {

    @Test
    public void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM station WHERE id = 10")) {

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("ID") +
                                   "'\n, Station: " + rs.getString("Station") +
                                   "'\n, Latitude: " + rs.getString("Latitude") +
                                   "'\n, Longitude: " + rs.getString("Longitude") +
                                   "'\n, Altitude: " + rs.getString("Altitude"));

            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    @Test
    public void addStation() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";
        String insertSql = "INSERT INTO stations (id, external_id,name, latitude, longitude, altitude) " +
                           "VALUES (11,'Gauteng',' Midrand station',38.2, -123.42, 150)";
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted rows: " + rowsAffected);
        } catch (java.sql.SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    @Test
    public void updateStation() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";
        String updateSql = "UPDATE stations SET latitude = 38.6, longitude = -128.51, altitude = 160 WHERE id = 10";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(updateSql)) {

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Updated rows: " + rowsAffected);
        } catch (java.sql.SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }


}
