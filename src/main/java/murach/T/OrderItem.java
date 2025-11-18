/**
 * OrderItem.java - DahlstromTours
 * Represents a single item within an order in the DahlstromTours web application.
 * Contains details about the tour purchased, including its ID, price, and the unique 
 * order item ID assigned when saved in the database.
 *
 * Constructors:
 *   - OrderItem(String tourId, double price):
 *       Used when creating a new order item to insert into the database (orderItemId not assigned yet).
 *   - OrderItem(int orderItemId, String tourId, double price):
 *       Used when fetching an existing order item from the database.
 *
 * Getters:
 *   - getOrderItemId(): Returns the unique database ID for the order item.
 *   - getTourId(): Returns the ID of the tour associated with this order item.
 *   - getPrice(): Returns the price of the tour at the time of purchase.
 *
 * This class is used in checkout and order management functionality to track individual 
 * tours purchased within a single order.
 */

package murach.T;

public class OrderItem {
    private int orderItemId;
    private String tourId;
    private double price;

    // Constructor for inserting items (without orderItemId yet)
    public OrderItem(String tourId, double price) {
        this.tourId = tourId;
        this.price = price;
    }

    // Constructor for fetching items
    public OrderItem(int orderItemId, String tourId, double price) {
        this.orderItemId = orderItemId;
        this.tourId = tourId;
        this.price = price;
    }

    // Getters and setters
    public int getOrderItemId() { return orderItemId; }
    public String getTourId() { return tourId; }
    public double getPrice() { return price; }
}