/**
 * ContactDB.java - DahlstromTours
 * Handles database operations related to contact messages submitted
 * by users through the "Contact Us" form.
 *
 * Functionality:
 *   - insertMessage(String name, String email, String message)
 *     saves a new contact message into the contact_message table.
 *
 * Database Connection:
 *   - Uses DBUtil.getConnection() to obtain a database connection
 *     from the connection pool.
 *
 * Error Handling:
 *   - Prints an error message if an SQLException occurs during insertion.
 *   - Returns true if insertion succeeds, false otherwise.
 *
 * Usage:
 *   Called by ContactServlet when a user submits the contact form.
 */

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