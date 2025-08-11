package Utils;

public class DatabaseConnection {

    public static String externalId;
    public static String stationName;
    public static  double latitude;
    public static  double longitude;
    static int altitude;

    public static void testConnection() {
        String url = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_ndosiautomation";
        String username = "ndosian6b8b7_admin";
        String password = "Aod~J2EGkNY,-C[0";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, username, password);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM stations WHERE id = 2")) {

            while (rs.next()) {
                externalId = rs.getString("external_id");
                stationName = rs.getString("name");
                latitude = Double.parseDouble(rs.getString("latitude"));
                longitude = Double.parseDouble(rs.getString("longitude"));
                altitude = Integer.parseInt(rs.getString("altitude"));

            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }


}
