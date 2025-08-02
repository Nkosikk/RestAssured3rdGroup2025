package Utils;


import org.testng.annotations.Test;

public class DatabaseConnection {

    public static String  stationName = "First station";
    public static String extid;
    public static String wstationId;

    @Test
    public void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";


        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = 5")) {

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username") +
                                   ", Password: " + rs.getString("password"));

                extid=rs.getString("username");
                wstationId = rs.getString("password");
            }

            System.out.println("Extenal Id :"+wstationId+" Weather :"+extid);

        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

    }


}
