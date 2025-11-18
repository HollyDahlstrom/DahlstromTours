/**
 * UserDB.java - DahlstromTours
 * This class handles database operations related to users in the DahlstromTours web application.
 * It provides methods for validating user login credentials and inserting new users into the database.
 *
 * Methods:
 *   - validateUser(String email, String password):
 *       Queries the database for a user with the provided email. 
 *       If a matching record is found and the password matches, returns a User object. 
 *       Otherwise, returns null.
 *
 *   - insertUser(User user):
 *       Inserts a new user record into the database with the user's first name, last name, email, and password hash.
 *       Returns true if the insertion was successful, false otherwise.
 *
 * Database Handling:
 *   Uses DBUtil.getConnection() for establishing a connection to the MySQL database.
 *   Employs try-with-resources to ensure all database resources are properly closed.
 *
 * Error Handling:
 *   Prints error messages to standard error if any SQLException occurs.
 *
 * Note:
 *   Passwords are stored and compared using password hashes.
 */

package murach.DB;

import murach.T.User;
import java.sql.*;

public class UserDB {

    // Validate user login and return User object
    public static User validateUser(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ?";
        User user = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    if (storedHash.equals(password)) {
                        int userId = rs.getInt("user_id"); 
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        user = new User(userId, email, storedHash, firstName, lastName);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error validating user: " + e.getMessage());
        }

        return user;
    }

    // Insert a new user
    public static boolean insertUser(User user) {
        String sql = "INSERT INTO user (first_name, last_name, email, password_hash) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPasswordHash());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Database error inserting user: " + e.getMessage());
            return false;
        }
    }
}