package Utils;

import org.testng.annotations.Test;

public class DbConnection {
    @Test
    public void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";


        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM stations")) {

            while (rs.next()) {
                System.out.println("ID  : "+rs.getString("id")+"\n"+
                        "External ID : " + rs.getString("external_id") +"\n"+
                        "Name  : " + rs.getString("name") +"\n"+
                        "Longitude : " + rs.getString("longitude") +"\n"+
                        "Altitude  : " + rs.getString("altitude") +"\n"+
                        "Latitude  : " + rs.getString("latitude")+"\n");
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
