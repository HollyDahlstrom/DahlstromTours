/**
 * TourDB.java - DahlstromTours
 * This class handles database operations related to tours in the DahlstromTours web application.
 * It provides methods for fetching all tours or a single tour by its ID from the database.
 *
 * Methods:
 *   - selectAllTours():
 *       Retrieves all tours from the database. Each tour includes its ID, name, country, price,
 *       description, and image URL. Returns a List of Tour objects.
 *
 *   - selectTour(String tourId):
 *       Retrieves a single tour based on the provided tour ID. The returned Tour object includes
 *       the tour's ID, name, country, price, short and full description, and image URL. Returns null
 *       if no tour is found.
 *
 * Database Handling:
 *   Uses DBUtil.getConnection() for establishing a connection to the MySQL database.
 *   Employs try-with-resources to ensure all database resources (Connection, PreparedStatement, ResultSet) are properly closed.
 *
 * Error Handling:
 *   Prints error messages to standard error if any SQLException occurs.
 */

package murach.DB;

import murach.T.Tour;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDB {

    // Fetch all tours
    public static List<Tour> selectAllTours() {
        String sql = "SELECT tour_id, name, country, price, description, image_url FROM tour";
        List<Tour> tours = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("tour_id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String image = rs.getString("image_url");

                tours.add(new Tour(id, name, country, price, description, null, image));
            }

        } catch (SQLException e) {
            System.err.println("Database error fetching all tours: " + e.getMessage());
        }

        return tours;
    }

    // Fetch single tour by ID
    public static Tour selectTour(String tourId) {
        String sql = "SELECT tour_id, name, country, price, description, full_description, image_url FROM tour WHERE tour_id = ?";
        Tour tour = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("tour_id");
                    String name = rs.getString("name");
                    String country = rs.getString("country");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String fullDescription = rs.getString("full_description");
                    String image = rs.getString("image_url");

                    tour = new Tour(id, name, country, price, description, fullDescription, image);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error fetching tour by ID: " + e.getMessage());
        }

        return tour;
    }
}