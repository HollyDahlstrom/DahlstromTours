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