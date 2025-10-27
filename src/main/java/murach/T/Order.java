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