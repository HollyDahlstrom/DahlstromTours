package murach.DB;

import murach.T.Tour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Handles inserting customer orders into the database.
 */
public class OrderDB {

    /**
     * Inserts a new order with its items into the database.
     *
     * @param userId the ID of the user placing the order
     * @param totalPrice the total amount of the order
     * @param cart the cart containing selected tours
     * @return the generated order ID if successful, or -1 if insertion failed
     */
    public static int insertOrder(int userId, double totalPrice, Map<String, Tour> cart, Map<String, Integer> quantities) {
        int orderId = -1;

        String insertOrderSQL = "INSERT INTO orders (user_id, total_price) VALUES (?, ?)";
        String insertItemSQL = "INSERT INTO order_items (order_id, tour_id, price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // 1️⃣ Insert into 'orders' table
            try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psOrder.setInt(1, userId);
                psOrder.setDouble(2, totalPrice);

                int rows = psOrder.executeUpdate();
                if (rows == 0) {
                    conn.rollback();
                    System.err.println("Order insertion failed: no rows affected.");
                    return -1;
                }

                // Get generated order_id
                try (ResultSet rs = psOrder.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        System.err.println("Failed to retrieve generated order ID.");
                        return -1;
                    }
                }
            }

            // 2️⃣ Insert each order item
            try (PreparedStatement psItem = conn.prepareStatement     (insertItemSQL)) {
                for (Map.Entry<String, Tour> entry : cart.entrySet()) {
                    Tour tour = entry.getValue();
                    int quantity = 1; // default
                    if (quantities != null) {
                        quantity = quantities.getOrDefault(tour.getId(), 1);
                    }

                    psItem.setInt(1, orderId);
                    psItem.setString(2, tour.getId()); // tour_id is VARCHAR in DB
                    psItem.setDouble(3, tour.getPrice());
                    psItem.setInt(4, quantity); // new column in DB
                    psItem.addBatch();
                }
                psItem.executeBatch();
            }

            conn.commit(); // ✅ Commit transaction
            System.out.println("Order inserted successfully with ID: " + orderId);

        } catch (SQLException e) {
            System.err.println("❌ Database error inserting order: " + e.getMessage());
            orderId = -1;
        }

        return orderId;
    }
}