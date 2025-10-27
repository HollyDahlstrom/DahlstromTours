package murach.DB;

import java.sql.*;

public class ContactDB {


    // Insert a contact message
    public static boolean insertMessage(String name, String email, String message) {
        String sql = "INSERT INTO contact_message (name, email, message) VALUES (?, ?, ?)";

        // Use DBUtil.getConnection()
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, message);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Database error inserting contact message: " + e.getMessage());
            return false;
        }
    }
}