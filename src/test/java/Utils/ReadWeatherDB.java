package Utils;

import org.testng.annotations.Test;

public class ReadWeatherDB {

    @Test
    public void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM stations WHERE id = 4")) {

            while (rs.next()) {
                System.out.println("external_id: " + rs.getString("ID") +
                        "'\n, Station Name: " + rs.getString("name") +
                        "'\n, Latitude: " + rs.getString("latitude") +
                        "'\n, Longitude: " + rs.getString("longitude") +
                        "'\n, Altitude: " + rs.getString("altitude"));

            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

    }

    @Test
//update the database connection details and query as needed
    public void updateStation() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";
        String updateSql = "UPDATE stations SET latitude = 38.6, longitude =-128.51 , altitude =160  WHERE id = 4";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(updateSql)) {

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Updated rows: " + rowsAffected);
        } catch (java.sql.SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }
    @Test
    public void deleteStation() {
        int id = 4; // Assuming you want to delete the station with ID 4
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";
        String deleteSql = "DELETE FROM stations WHERE id = 4";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {

//            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Deleted rows: " + rowsAffected);
        } catch (java.sql.SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }
@Test
    public void insertStation() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";
        String insertSql = "INSERT INTO stations (id, external_id,name, latitude, longitude, altitude) VALUES (4,'Ex_SETEST001','Boksburg Weather station',38.2, -123.42, 150)";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("Inserted rows: " + rowsInserted);
        } catch (java.sql.SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }






}


