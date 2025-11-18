/**
 * Tour.java - DahlstromTours
 * Represents a tour offered in the DahlstromTours web application.
 * Contains details about a specific tour, including ID, name, country, 
 * price, descriptions, image, and quantity for cart operations.
 * 
 * Constructor:
 *   - Tour(String id, String name, String country, double price, String description, 
 *          String fullDescription, String image):
 *       Initializes a new Tour object with the provided details. Default quantity is set to 1.
 * 
 * Getters:
 *   - getId(): Returns the unique ID of the tour.
 *   - getName(): Returns the name of the tour.
 *   - getCountry(): Returns the country where the tour takes place.
 *   - getPrice(): Returns the price of the tour.
 *   - getDescription(): Returns a short description of the tour.
 *   - getFullDescription(): Returns the detailed description of the tour.
 *   - getImage(): Returns the filename or path of the tour image.
 *   - getQuantity(): Returns the quantity of this tour in the cart.
 * 
 * Setter:
 *   - setQuantity(int quantity): Sets the quantity of the tour, must be greater than 0.
 * 
 * This class is primarily used for displaying tours, adding them to the cart, 
 * and handling cart operations in the web application.
 */

package murach.T;

public class Tour {
    private String id;
    private String name;
    private String country;
    private double price;
    private String description;
    private String fullDescription;
    private String image;
    private int quantity; 

    public Tour(String id, String name, String country, double price, String description, String fullDescription, String image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.price = price;
        this.description = description;
        this.fullDescription = fullDescription;
        this.image = image;
        this.quantity = 1; 
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getFullDescription() { return fullDescription; } 
    public String getImage() { return image; }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
}