/**
 * Order.java - DahlstromTours
 * Represents a single order placed by a user in the DahlstromTours web application.
 * Each order contains information about the user who placed it, the total price,
 * the date the order was created, and the list of items included in the order.
 *
 * Constructors:
 *   - Order(int orderId, int userId, double totalPrice, Timestamp orderDate):
 *       Used when fetching an existing order from the database.
 *   - Order(int userId, double totalPrice):
 *       Used when creating a new order before inserting it into the database 
 *       (orderId and orderDate will be assigned by the database).
 *
 * Fields:
 *   - orderId: Unique ID for the order in the database.
 *   - userId: ID of the user who placed the order.
 *   - totalPrice: Total cost of all items in the order.
 *   - orderDate: Timestamp of when the order was created.
 *   - items: List of OrderItem objects included in the order (optional, may be null).
 *
 * Getters and Setters:
 *   Standard getters for all fields and a setter for the items list.
 *
 * This class is used in checkout and order management functionality to track
 * orders placed by users and their associated order items.
 */

package murach.T;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private double totalPrice;
    private Timestamp orderDate;
    private List<OrderItem> items; // optional, can be null or empty

    // Constructor for fetching orders
    public Order(int orderId, int userId, double totalPrice, Timestamp orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Constructor for creating new orders (without orderId yet)
    public Order(int userId, double totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public double getTotalPrice() { return totalPrice; }
    public Timestamp getOrderDate() { return orderDate; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}